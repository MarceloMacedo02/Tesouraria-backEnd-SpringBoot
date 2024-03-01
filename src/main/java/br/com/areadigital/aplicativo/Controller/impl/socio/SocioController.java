package br.com.areadigital.aplicativo.Controller.impl.socio;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.areadigital.aplicativo.Controller.AbstractController;
import br.com.areadigital.aplicativo.dtos.ConsultaPaginadaDto;
import br.com.areadigital.aplicativo.dtos.socio.InnerSocioDto;
import br.com.areadigital.aplicativo.dtos.socio.SocioDto;
import br.com.areadigital.aplicativo.entities.socio.Socio;
import br.com.areadigital.aplicativo.exceptions.ErrorProcessingException;
import br.com.areadigital.aplicativo.services.impl.socio.SocioService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/socios")
public class SocioController extends AbstractController<String, Socio, SocioDto> {

    private final SocioService service;

    @Override
    @GetMapping(path = "/pagination")
    public ResponseEntity<Page<?>> findAllWithPaginationAndSorting(@Valid ConsultaPaginadaDto consulta)
            throws ErrorProcessingException {
        Page<InnerSocioDto> page = service.findAllWithPaginationAndSorting(consulta).map(InnerSocioDto::new);
        return ResponseEntity.ok(page);
    }

}
