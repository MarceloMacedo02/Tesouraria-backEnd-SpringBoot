package br.com.areadigital.aplicativo.mappers.impl.financeiro;

import org.mapstruct.Mapper;

import br.com.areadigital.aplicativo.dtos.financeiro.GrupoFinanceiroDto;
import br.com.areadigital.aplicativo.entities.Financeiro.GrupoFinanceiro;
import br.com.areadigital.aplicativo.mappers.AbstractMapper;

@Mapper(componentModel = "spring")
public interface GrupoFinanceiroMapper extends AbstractMapper<GrupoFinanceiro, GrupoFinanceiroDto, String> {
    @Override
    default GrupoFinanceiroDto toDTO(GrupoFinanceiro entity) {
        return new GrupoFinanceiroDto(entity);
    }
}
