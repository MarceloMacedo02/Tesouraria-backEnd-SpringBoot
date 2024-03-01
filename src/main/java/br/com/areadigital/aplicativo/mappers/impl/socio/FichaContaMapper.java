package br.com.areadigital.aplicativo.mappers.impl.socio;

import org.mapstruct.Mapper;

import br.com.areadigital.aplicativo.dtos.socio.FichaContaDto;
import br.com.areadigital.aplicativo.entities.socio.FichaConta;
import br.com.areadigital.aplicativo.mappers.AbstractMapper;

@Mapper(componentModel = "spring")
public interface FichaContaMapper extends AbstractMapper<FichaConta, FichaContaDto, String> {

    @Override
    default FichaContaDto toDTO(FichaConta entity) {
        return new FichaContaDto(entity);
    }
}
