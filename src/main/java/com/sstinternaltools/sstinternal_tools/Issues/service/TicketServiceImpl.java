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
import com.sstinternaltools.sstinternal_tools.Issues.mapper.Interfaces.TicketDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketDtoMapper ticketDtoMapper;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, TicketDtoMapper ticketDtoMapper) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.ticketDtoMapper = ticketDtoMapper;
    }

    @Override
    @Transactional
    public TicketResponseDto createTicket(TicketCreateDto ticketCreateDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));


        Ticket ticket = ticketDtoMapper.fromCreateDto(ticketCreateDto);
        if (ticket.getPriority() == null) {
            ticket.setPriority(TicketPriority.MEDIUM);
        }
        if (ticket.getStatus() == null) {
            ticket.setStatus(TicketStatus.OPEN);
        }

        ticket.setImageUrl(new ArrayList<>()); // creating new mpty list
        ticket.setUpvote(0);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());
        ticket.setCreatedby(user);

        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketDtoMapper.toResponseDto(savedTicket);
    }

    @Override
    public TicketResponseDto getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + id));
        return ticketDtoMapper.toResponseDto(ticket);
    }

    @Override
    public List<TicketSummaryDto> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketDtoMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketSummaryDto> getTicketsByUserId(Long userId) {
        return ticketRepository.findByCreatedbyId(userId).stream()
                .map(ticketDtoMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketSummaryDto> getTicketsByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status).stream()
                .map(ticketDtoMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketSummaryDto> getTicketsByPriority(TicketPriority priority) {
        return ticketRepository.findByPriority(priority).stream()
                .map(ticketDtoMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketSummaryDto> getTicketsByCampus(CampusType campus) {
        return ticketRepository.findByCampus(campus).stream()
                .map(ticketDtoMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TicketResponseDto updateTicket(Long id, TicketUpdateDto ticketUpdateDto) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + id));


        ticket = ticketDtoMapper.fromUpdateDto(ticketUpdateDto, ticket);

        Ticket updatedTicket = ticketRepository.save(ticket);
        return ticketDtoMapper.toResponseDto(updatedTicket);
    }

    @Override
    @Transactional
    public TicketResponseDto updateTicketStatus(Long id, TicketStatus status) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + id));

        ticket.setStatus(status);
        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket updatedTicket = ticketRepository.save(ticket);
        return ticketDtoMapper.toResponseDto(updatedTicket);
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
        return ticketDtoMapper.toResponseDto(updatedTicket);
    }
}
