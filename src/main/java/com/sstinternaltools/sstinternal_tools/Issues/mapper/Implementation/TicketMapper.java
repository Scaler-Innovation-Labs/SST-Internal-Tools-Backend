package com.sstinternaltools.sstinternal_tools.Issues.mapper.Implementation;

import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketCreateDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketResponseDto;
import com.sstinternaltools.sstinternal_tools.Issues.entity.Ticket;
import com.sstinternaltools.sstinternal_tools.Issues.mapper.Interfaces.TicketDtoMapper;
import org.springframework.stereotype.Component;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketUpdateDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketSummaryDto;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

@Component
public class TicketMapper implements TicketDtoMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

     @Override
     public Ticket fromCreateDto(TicketCreateDto ticketCreateDto) {
            Ticket ticket = new Ticket();
            ticket.setTitle(ticketCreateDto.getTitle());
            ticket.setDescription(ticketCreateDto.getDescription());
            if (ticketCreateDto.getCampus() != null) {
                ticket.setCampus(ticketCreateDto.getCampus());
            }
            ticket.setStatus(ticketCreateDto.getStatus());
            ticket.setPriority(ticketCreateDto.getPriority());
            ticket.setPrivate(ticketCreateDto.isPrivate());
            return ticket;
     }

     @Override
     public TicketResponseDto toResponseDto(@NotNull Ticket ticket) {
            TicketResponseDto ticketResponseDto = new TicketResponseDto();
            ticketResponseDto.setId(ticket.getId());
            ticketResponseDto.setTitle(ticket.getTitle());
            ticketResponseDto.setDescription(ticket.getDescription());
            ticketResponseDto.setStatus(ticket.getStatus());
            ticketResponseDto.setPriority(ticket.getPriority());
            if (ticket.getCampus() != null) {
                ticketResponseDto.setCampus(ticket.getCampus().toString());
            }
            ticketResponseDto.setUpvote(ticket.getUpvote());
            ticketResponseDto.setPrivate(ticket.isPrivate());

            if (ticket.getImageUrl() != null && !ticket.getImageUrl().isEmpty()) {
                ticketResponseDto.setImageUrl(String.join(",", ticket.getImageUrl()));
            } else {
                ticketResponseDto.setImageUrl(""); // nul set to empty string if no images
            }

            if (ticket.getCreatedby() != null) {
                ticketResponseDto.setCreatedBy(ticket.getCreatedby().getUsername());
            }

            if (ticket.getCreatedAt() != null) {
                ticketResponseDto.setCreatedAt(ticket.getCreatedAt().format(DATE_FORMATTER));
            }
            if (ticket.getUpdatedAt() != null) {
                ticketResponseDto.setUpdatedAt(ticket.getUpdatedAt().format(DATE_FORMATTER));
            }
            return ticketResponseDto;
     }

     @Override
     public TicketSummaryDto toSummaryDto(Ticket ticket) {
            TicketSummaryDto ticketSummaryDto = new TicketSummaryDto();
            ticketSummaryDto.setId(ticket.getId());
            ticketSummaryDto.setTitle(ticket.getTitle());
            ticketSummaryDto.setDescription(ticket.getDescription());
            ticketSummaryDto.setPriority(ticket.getPriority());
            ticketSummaryDto.setStatus(ticket.getStatus());
            ticketSummaryDto.setTicketStatus(ticket.getStatus());
            if (ticket.getCampus() != null) {
                ticketSummaryDto.setCampus(ticket.getCampus().toString());
            }
            ticketSummaryDto.setUpvote(ticket.getUpvote());
            ticketSummaryDto.setPrivate(ticket.isPrivate());
            return ticketSummaryDto;
    }

    @Override
    public Ticket fromUpdateDto(TicketUpdateDto ticketUpdateDto, Ticket ticket) {
            if (ticketUpdateDto.getTitle() != null) {
                ticket.setTitle(ticketUpdateDto.getTitle());
            }
            if (ticketUpdateDto.getDescription() != null) {
                ticket.setDescription(ticketUpdateDto.getDescription());
            }
            if (ticketUpdateDto.getStatus() != null) {
                ticket.setStatus(ticketUpdateDto.getStatus());
            }
            if (ticketUpdateDto.getPriority() != null) {
                ticket.setPriority(ticketUpdateDto.getPriority());
            }
            if (ticketUpdateDto.getCampus() != null) {
                ticket.setCampus(ticketUpdateDto.getCampus());
            }
            if (ticketUpdateDto.getImageUrl() != null) {
                // Assuming getImageUrl() from DTO returns List<String>
                // and setImageUrl() in Entity expects List<String>
                ticket.setImageUrl(ticketUpdateDto.getImageUrl());
            }
            if (ticketUpdateDto.isPrivate() != null) {
                ticket.setPrivate(ticketUpdateDto.isPrivate());
            }
            ticket.setUpdatedAt(LocalDateTime.now()); // Update timestamp here
            return ticket;
    }
}
