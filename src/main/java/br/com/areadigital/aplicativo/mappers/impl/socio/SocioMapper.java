package br.com.areadigital.aplicativo.mappers.impl.socio;

import org.mapstruct.Mapper;

import br.com.areadigital.aplicativo.dtos.socio.SocioDto;
import br.com.areadigital.aplicativo.entities.socio.Socio;
import br.com.areadigital.aplicativo.mappers.AbstractMapper;

@Mapper(componentModel = "spring")
public interface SocioMapper extends AbstractMapper<Socio, SocioDto, String> {

    @Override
    default SocioDto toDTO(Socio entity) {
        return new SocioDto(entity);
    }
}
