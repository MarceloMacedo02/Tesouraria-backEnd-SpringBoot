package br.com.areadigital.aplicativo.Controller.impl.financeiro;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.areadigital.aplicativo.Controller.AbstractController;
import br.com.areadigital.aplicativo.dtos.financeiro.GrupoFinanceiroDto;
import br.com.areadigital.aplicativo.entities.Financeiro.GrupoFinanceiro;
import br.com.areadigital.aplicativo.services.AbstractService;
import br.com.areadigital.aplicativo.services.impl.financeiro.GrupoFinanceiroService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/grupo-financeiro")
@CrossOrigin
@RequiredArgsConstructor
public class GrupoFinanceiroController extends AbstractController<String, GrupoFinanceiro, GrupoFinanceiroDto> {

    private final GrupoFinanceiroService service;

    @Override
    public AbstractService<String, GrupoFinanceiro, GrupoFinanceiroDto> getService() {
        return service;
    }

}
