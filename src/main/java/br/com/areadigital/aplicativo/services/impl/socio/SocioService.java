package br.com.areadigital.aplicativo.services.impl.socio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.areadigital.aplicativo.dtos.CampoPesquisaDto;
import br.com.areadigital.aplicativo.dtos.ConsultaPaginadaDto;
import br.com.areadigital.aplicativo.dtos.socio.SocioDto;
import br.com.areadigital.aplicativo.entities.socio.Socio;
import br.com.areadigital.aplicativo.mappers.impl.socio.SocioMapper;
import br.com.areadigital.aplicativo.repositories.SocioRepository;
import br.com.areadigital.aplicativo.services.AbstractService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class SocioService extends AbstractService<String, Socio, SocioDto> {

    private final SocioRepository repository;
    private final SocioMapper mapper;

    @Override
    public Page<SocioDto> findAllWithPaginationAndSorting(ConsultaPaginadaDto consultaPaginadaDTO) {
        CampoPesquisaDto[] campoPesquisa = consultaPaginadaDTO.getCampoPesquisa();
        int page = consultaPaginadaDTO.getPage();
        int tamanho = consultaPaginadaDTO.getTamanho();
        String ordem = consultaPaginadaDTO.getOrdem();
        String ordenarPor = consultaPaginadaDTO.getOrdenarPor();

        PageRequest pageRequest = PageRequest.of(page, tamanho, Sort.Direction.fromString(ordem), ordenarPor);
        return repository.findAllByNomeContainingIgnoreCase(campoPesquisa[0].getValorPesquisado(), pageRequest)
                .map(mapper::toDTO);
    }
}
