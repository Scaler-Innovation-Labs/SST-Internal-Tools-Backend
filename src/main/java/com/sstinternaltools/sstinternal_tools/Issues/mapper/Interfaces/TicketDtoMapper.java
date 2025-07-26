package com.sstinternaltools.sstinternal_tools.Issues.mapper.Interfaces;

import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketCreateDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketResponseDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketSummaryDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketUpdateDto;
import com.sstinternaltools.sstinternal_tools.Issues.entity.Ticket;

public interface TicketDtoMapper {

    Ticket fromCreateDto(TicketCreateDto ticketCreateDto);

    TicketResponseDto toResponseDto(Ticket ticket);

    TicketSummaryDto toSummaryDto(Ticket ticket);

    Ticket fromUpdateDto(TicketUpdateDto ticketUpdateDto, Ticket ticket);
}
