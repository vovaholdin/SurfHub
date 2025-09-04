package com.goldin.mapper;

import com.goldin.entity.Surf;
import com.goldin.mapper.dto.SurfTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SurfMapperTo {
    SurfTo toDto(Surf surf);
    Surf toEntity(SurfTo surfTo);
}
