package com.sstinternaltools.sstinternal_tools.transport.mapper.interfaces;

import java.time.DayOfWeek;

public interface DtoMapper <Entity, ResponseDto, CreateDto, UpdateDto, SummaryDto> {

    Entity fromCreateDto(CreateDto createDto, DayOfWeek dayOfWeek);
    Entity fromUpdateDto(UpdateDto updateDto, Entity entity);
    ResponseDto toResponseDto(Entity entity);
    SummaryDto toSummaryDto(Entity entity);
}
