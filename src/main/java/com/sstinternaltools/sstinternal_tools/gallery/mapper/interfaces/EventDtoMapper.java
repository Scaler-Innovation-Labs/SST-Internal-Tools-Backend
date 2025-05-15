package com.sstinternaltools.sstinternal_tools.gallery.mapper.interfaces;

public interface EventDtoMapper<Event, EventResponseDto, EventCreateDto, EventUpdateDto, EventSummaryDto> {

    Event fromCreateDto(EventCreateDto createDto);
    Event fromUpdateDto(EventUpdateDto updateDto, Event event);
    EventResponseDto toResponseDto(Event event);
    EventSummaryDto toSummaryDto(Event event);
}