package com.sstinternaltools.sstinternal_tools.Issues.mapper.Implementation;

import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketCreateDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketResponseDto;
import com.sstinternaltools.sstinternal_tools.Issues.entity.Ticket;
import com.sstinternaltools.sstinternal_tools.Issues.mapper.Interfaces.TicketDtoMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketUpdateDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketSummaryDto;



@Component
public class TicketMapper implements TicketDtoMapper {

     @Override
     public Ticket fromCreateDto(TicketCreateDto ticketCreateDto) {
            Ticket ticket = new Ticket();
            ticket.setTitle(ticketCreateDto.getTitle());
            ticket.setDescription(ticketCreateDto.getDescription());
            ticket.setStatus(ticketCreateDto.getStatus());
            ticket.setPriority(ticketCreateDto.getPriority());
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
            return ticketResponseDto;

     }
     @Override
     public TicketSummaryDto toSummaryDto(Ticket ticket) {
                TicketSummaryDto ticketSummaryDto = new TicketSummaryDto();
                ticketSummaryDto.setId(ticket.getId());
                ticketSummaryDto.setTitle(ticket.getTitle());
                ticketSummaryDto.setStatus(ticket.getStatus());
                ticketSummaryDto.setPriority(ticket.getPriority());
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
                return ticket;
        }





}
