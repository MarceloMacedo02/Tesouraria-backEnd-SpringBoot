package br.com.areadigital.aplicativo.services.impl.socio;

import org.springframework.stereotype.Service;

import br.com.areadigital.aplicativo.dtos.socio.FichaContaDto;
import br.com.areadigital.aplicativo.entities.socio.FichaConta;
import br.com.areadigital.aplicativo.mappers.impl.socio.FichaContaMapper;
import br.com.areadigital.aplicativo.repositories.FichaContaRepository;
import br.com.areadigital.aplicativo.services.AbstractService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class FichaContaService extends AbstractService<String, FichaConta, FichaContaDto> {

    private final FichaContaRepository repository;
    private final FichaContaMapper mapper;
}
