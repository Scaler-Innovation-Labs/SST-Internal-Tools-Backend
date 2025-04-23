package com.sstinternaltools.sstinternal_tools.mess.mapper.interfaces;

public interface DtoMapper <Entity,CreateDto, UpdateDto, ResponseDto,  SummaryDto>{
    Entity fromCreateDto(CreateDto createDto);
    Entity fromUpdateDto(UpdateDto updateDto, Entity entity);
    ResponseDto toResponseDto(Entity entity);
    SummaryDto toSummaryDto(Entity entity);
}
