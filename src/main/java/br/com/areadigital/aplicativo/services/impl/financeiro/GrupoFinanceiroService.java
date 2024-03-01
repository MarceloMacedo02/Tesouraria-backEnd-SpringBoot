package br.com.areadigital.aplicativo.services.impl.financeiro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.areadigital.aplicativo.dtos.CampoPesquisaDto;
import br.com.areadigital.aplicativo.dtos.ConsultaPaginadaDto;
import br.com.areadigital.aplicativo.dtos.financeiro.GrupoFinanceiroDto;
import br.com.areadigital.aplicativo.entities.Financeiro.GrupoFinanceiro;
import br.com.areadigital.aplicativo.mappers.impl.financeiro.GrupoFinanceiroMapper;
import br.com.areadigital.aplicativo.repositories.GrupoFinanceiroRepository;
import br.com.areadigital.aplicativo.services.AbstractService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Getter
public class GrupoFinanceiroService extends AbstractService<String, GrupoFinanceiro, GrupoFinanceiroDto> {

    private final GrupoFinanceiroRepository repository;
    private final GrupoFinanceiroMapper mapper;

    @Override
    public Page<GrupoFinanceiroDto> findAllWithPaginationAndSorting(ConsultaPaginadaDto consultaPaginadaDTO) {
        CampoPesquisaDto[] campoPesquisa = consultaPaginadaDTO.getCampoPesquisa();
        int page = consultaPaginadaDTO.getPage();
        int tamanho = consultaPaginadaDTO.getTamanho();
        String ordem = consultaPaginadaDTO.getOrdem();
        String ordenarPor = consultaPaginadaDTO.getOrdenarPor();

        PageRequest pageRequest = PageRequest.of(page, tamanho, Sort.Direction.fromString(ordem), ordenarPor);
        Page<GrupoFinanceiro> result = repository.findAllByTipoAndDescricaoContainingIgnoreCase(
                campoPesquisa[0].getValorPesquisado(), campoPesquisa[1].getValorPesquisado(), pageRequest);

        Page<GrupoFinanceiroDto> gruposDto = result.map(getMapper()::toDTO);
        return gruposDto;
    }
}
