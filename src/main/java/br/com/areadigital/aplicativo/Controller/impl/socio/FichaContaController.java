package br.com.areadigital.aplicativo.Controller.impl.socio;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.areadigital.aplicativo.Controller.AbstractController;
import br.com.areadigital.aplicativo.dtos.socio.FichaContaDto;
import br.com.areadigital.aplicativo.entities.socio.FichaConta;
import br.com.areadigital.aplicativo.services.impl.socio.FichaContaService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ficha-conta")
public class FichaContaController extends AbstractController<String, FichaConta, FichaContaDto> {
    private final FichaContaService service;
}
