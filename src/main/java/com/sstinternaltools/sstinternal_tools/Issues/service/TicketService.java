package com.sstinternaltools.sstinternal_tools.Issues.service;

import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketCreateDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketResponseDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketSummaryDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketUpdateDto;
import com.sstinternaltools.sstinternal_tools.Issues.entity.CampusType;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketPriority;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketStatus;

import java.util.List;

public interface TicketService {
    TicketResponseDto createTicket(TicketCreateDto ticketCreateDto, Long userId);
    TicketResponseDto getTicketById(Long id);
    List<TicketSummaryDto> getAllTickets();
    List<TicketSummaryDto> getTicketsByUserId(Long userId);
    List<TicketSummaryDto> getTicketsByStatus(TicketStatus status);
    List<TicketSummaryDto> getTicketsByPriority(TicketPriority priority);
    List<TicketSummaryDto> getTicketsByCampus(CampusType campus);
    TicketResponseDto updateTicket(Long id, TicketUpdateDto ticketUpdateDto);
    TicketResponseDto updateTicketStatus(Long id, TicketStatus status);
    void deleteTicket(Long id);
    TicketResponseDto upvoteTicket(Long id);
}
