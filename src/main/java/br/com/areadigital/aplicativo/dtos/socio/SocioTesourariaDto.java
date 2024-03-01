package br.com.areadigital.aplicativo.dtos.socio;

import java.util.List;

import br.com.areadigital.aplicativo.entities.enums.TipoEntradaFicha;
import br.com.areadigital.aplicativo.entities.socio.SocioTesouraria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocioTesourariaDto {
    private String id;
    private List<FichaSocioDto> fichaSocios;
    private List<InnerSocioDto> dependentes;
    private List<FichaContaDto> fichaContas;
    private Double valorMensalidade = 0.0;
    private Double valorOutrasTaxas = 0.0;
    private Double valorDependente = 0.0;
    private Double valorTotal = 0.0;

    public SocioTesourariaDto(SocioTesouraria socioTesouraria) {
        if (socioTesouraria == null) {
            return;
        }
        this.id = socioTesouraria.getId();
        this.fichaSocios = socioTesouraria.getFichaSocios().stream().map(FichaSocioDto::new).toList();

        this.fichaContas = socioTesouraria.getFichaContas().stream().map(FichaContaDto::new).toList();

        for (FichaContaDto fichaConta : fichaContas) {
            if (fichaConta != null
                    && TipoEntradaFicha.MENSALIDADE.getName().equals(fichaConta.getDescricao())) {

                this.valorMensalidade += fichaConta.getValor();
            }
        }
        for (FichaContaDto fichaConta : fichaContas) {
            if (fichaConta != null
                    && !TipoEntradaFicha.MENSALIDADE.getName().equals(fichaConta.getDescricao())) {
                this.valorOutrasTaxas += fichaConta.getValor();
            }
        }

        somaValorDependentes(socioTesouraria);
        this.dependentes = socioTesouraria.getDependentes().stream().map(InnerSocioDto::new).toList();
        valorTotal = valorMensalidade + valorOutrasTaxas + valorDependente;
    }

    public SocioTesourariaDto(SocioTesouraria socioTesouraria, Boolean isDependente) {
        if (socioTesouraria == null) {
            return;
        }
        this.id = socioTesouraria.getId();
        this.fichaSocios = socioTesouraria.getFichaSocios().stream().map(FichaSocioDto::new).toList();

        this.fichaContas = socioTesouraria.getFichaContas().stream().map(FichaContaDto::new).toList();

        for (FichaContaDto fichaConta : fichaContas) {
            if (fichaConta != null
                    && TipoEntradaFicha.MENSALIDADE.getName().equals(fichaConta.getDescricao())) {
                this.valorMensalidade += fichaConta.getValor();
            }
        }
        for (FichaContaDto fichaConta : fichaContas) {
            if (fichaConta != null
                    && !TipoEntradaFicha.MENSALIDADE.getName().equals(fichaConta.getDescricao())) {
                this.valorOutrasTaxas += fichaConta.getValor();
            }
        }
        if (!isDependente)
            somaValorDependentes(socioTesouraria);

        valorTotal = valorMensalidade + valorOutrasTaxas + valorDependente;

    }

    private void somaValorDependentes(SocioTesouraria socioTesouraria) {
        socioTesouraria.getDependentes().stream().forEach(socio -> {
            if (socio != null) {
                SocioDto dto = new SocioDto();
                this.valorDependente += dto.somaValorDependentes(socioTesouraria.getSocio());
            }
        });
    }
}
