package com.sstinternaltools.sstinternal_tools.Issues.service;

import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketCreateDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketResponseDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketSummaryDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketUpdateDto;
import com.sstinternaltools.sstinternal_tools.Issues.entity.CampusType;
import com.sstinternaltools.sstinternal_tools.Issues.entity.Ticket;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketPriority;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketStatus;
import com.sstinternaltools.sstinternal_tools.Issues.exception.TicketNotFoundException;
import com.sstinternaltools.sstinternal_tools.Issues.repository.TicketRepository;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public TicketResponseDto createTicket(TicketCreateDto ticketCreateDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Ticket ticket = new Ticket();
        ticket.setTitle(ticketCreateDto.getTitle());
        ticket.setDescription(ticketCreateDto.getDescription());
        ticket.setPriority(ticketCreateDto.getPriority() != null ? ticketCreateDto.getPriority() : TicketPriority.MEDIUM);
        ticket.setStatus(ticketCreateDto.getStatus() != null ? ticketCreateDto.getStatus() : TicketStatus.OPEN);
        ticket.setCampus(ticketCreateDto.getCampus());
        ticket.setImageUrl(new ArrayList<>()); // Initialize with empty list
        ticket.setUpvote(0);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());
        ticket.setCreatedby(user);

        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToResponseDto(savedTicket);
    }

    @Override
    public TicketResponseDto getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + id));
        return convertToResponseDto(ticket);
    }

    @Override
    public List<TicketSummaryDto> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(this::convertToSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketSummaryDto> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId).stream()
                .map(this::convertToSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketSummaryDto> getTicketsByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status).stream()
                .map(this::convertToSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketSummaryDto> getTicketsByPriority(TicketPriority priority) {
        return ticketRepository.findByPriority(priority).stream()
                .map(this::convertToSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketSummaryDto> getTicketsByCampus(CampusType campus) {
        // Using category field from repository since there's no findByCampus method
        return ticketRepository.findByCategory(campus.toString()).stream()
                .map(this::convertToSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TicketResponseDto updateTicket(Long id, TicketUpdateDto ticketUpdateDto) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + id));


        if (ticketUpdateDto.getTitle() != null) {
            ticket.setTitle(ticketUpdateDto.getTitle());
        }
        if (ticketUpdateDto.getDescription() != null) {
            ticket.setDescription(ticketUpdateDto.getDescription());
        }
        if (ticketUpdateDto.getPriority() != null) {
            ticket.setPriority(ticketUpdateDto.getPriority());
        }
        if (ticketUpdateDto.getStatus() != null) {
            ticket.setStatus(ticketUpdateDto.getStatus());
        }
        if (ticketUpdateDto.getCampus() != null) {
            ticket.setCampus(ticketUpdateDto.getCampus());
        }
        if (ticketUpdateDto.getImageUrl() != null) {
            ticket.setImageUrl(ticketUpdateDto.getImageUrl());
        }

        ticket.setUpdatedAt(LocalDateTime.now());
        Ticket updatedTicket = ticketRepository.save(ticket);
        return convertToResponseDto(updatedTicket);
    }

    @Override
    @Transactional
    public TicketResponseDto updateTicketStatus(Long id, TicketStatus status) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + id));

        ticket.setStatus(status);
        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket updatedTicket = ticketRepository.save(ticket);
        return convertToResponseDto(updatedTicket);
    }

    @Override
    @Transactional
    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException("Ticket not found with ID: " + id);
        }
        ticketRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TicketResponseDto upvoteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + id));

        ticket.setUpvote(ticket.getUpvote() + 1);
        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket updatedTicket = ticketRepository.save(ticket);
        return convertToResponseDto(updatedTicket);
    }

    //  convert Ticket entity --> TicketResponseDto
    private TicketResponseDto convertToResponseDto(Ticket ticket) {
        TicketResponseDto responseDto = new TicketResponseDto();
        responseDto.setId(ticket.getId());
        responseDto.setTitle(ticket.getTitle());
        responseDto.setDescription(ticket.getDescription());
        responseDto.setPriority(ticket.getPriority());
        responseDto.setStatus(ticket.getStatus());
        responseDto.setCampus(ticket.getCampus().toString());
        responseDto.setUpvote(ticket.getUpvote());


        if (ticket.getImageUrl() != null && !ticket.getImageUrl().isEmpty()) {
            responseDto.setImageUrl(String.join(",", ticket.getImageUrl()));
        } else {
            responseDto.setImageUrl("");
        }


        if (ticket.getCreatedby() != null) {
            responseDto.setCreatedBy(ticket.getCreatedby().getUsername());
        }


        responseDto.setCreatedAt(ticket.getCreatedAt().format(DATE_FORMATTER));
        responseDto.setUpdatedAt(ticket.getUpdatedAt().format(DATE_FORMATTER));

        return responseDto;
    }

    //  convert Ticket entity --> TicketSummaryDto
    private TicketSummaryDto convertToSummaryDto(Ticket ticket) {
        TicketSummaryDto summaryDto = new TicketSummaryDto();
        summaryDto.setId(ticket.getId());
        summaryDto.setTitle(ticket.getTitle());
        summaryDto.setDescription(ticket.getDescription());
        summaryDto.setPriority(ticket.getPriority());
        summaryDto.setStatus(ticket.getStatus());
        summaryDto.setTicketStatus(ticket.getStatus());
        summaryDto.setCampus(ticket.getCampus().toString());
        summaryDto.setUpvote(ticket.getUpvote());
        return summaryDto;
    }
}
