package com.sstinternaltools.sstinternal_tools.gallery.mapper.implementation;

import com.sstinternaltools.sstinternal_tools.gallery.dto.EventCreateDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventResponseDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventSummaryDto;
import com.sstinternaltools.sstinternal_tools.gallery.dto.EventUpdateDto;
import com.sstinternaltools.sstinternal_tools.gallery.entity.Event;
import com.sstinternaltools.sstinternal_tools.gallery.mapper.interfaces.EventDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class EventDtoMapperImpl implements EventDtoMapper<Event, EventResponseDto, EventCreateDto, EventUpdateDto, EventSummaryDto> {

    @Override
    public Event fromCreateDto(EventCreateDto createDto) {
        Event event = new Event();
        event.setName(createDto.getName());
        event.setDescription(createDto.getDescription());
        event.setDate(createDto.getDate());
        event.setDriveLink(createDto.getDriveLink());
        return event;
    }

    @Override
    public Event fromUpdateDto(EventUpdateDto eventUpdateDto, Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Existing event cannot be null for update ");
        }

        if(eventUpdateDto.getDate() != null) {
            event.setDate(eventUpdateDto.getDate());
        }
        if(eventUpdateDto.getDriveLink() != null) {
            event.setDriveLink(eventUpdateDto.getDriveLink());
        }
        if (eventUpdateDto.getName() != null) {
            event.setName(eventUpdateDto.getName());
        }
        if (eventUpdateDto.getDescription() != null) {
            event.setDescription(eventUpdateDto.getDescription());
        }
        return event;
    }

    @Override
    public EventResponseDto toResponseDto(Event event) {
       EventResponseDto eventResponseDto = new EventResponseDto();
       eventResponseDto.setId(event.getId());
       eventResponseDto.setName(event.getName());
       eventResponseDto.setDescription(event.getDescription());
       eventResponseDto.setDate(event.getDate());
       eventResponseDto.setDriveLink(event.getDriveLink());
       return eventResponseDto;
    }

    @Override
    public EventSummaryDto toSummaryDto(Event event) {
        EventSummaryDto eventSummaryDto = new EventSummaryDto();
        eventSummaryDto.setName(event.getName());
        eventSummaryDto.setDescription(event.getDescription());
        eventSummaryDto.setDate(event.getDate());
        eventSummaryDto.setDriveLink(event.getDriveLink());
        return eventSummaryDto;
    }
}
