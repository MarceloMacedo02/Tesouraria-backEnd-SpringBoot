package br.com.areadigital.aplicativo.dtos.socio;

import java.util.Date;

import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.entities.enums.TipoEntradaFicha;
import br.com.areadigital.aplicativo.entities.socio.EnderecoPrincipal;
import br.com.areadigital.aplicativo.entities.socio.Socio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocioDto implements BaseEntity<String> {
    private String id;
    private String email;
    private String username;
    private String nome;
    private String grauSocio;
    private boolean isAtivo;
    private String cargoDirtetoria;
    private Date dataInicial;
    private boolean isDiretoria;
    private EnderecoPrincipal enderecoPrincipal;
    private SocioTesourariaDto socioTesouraria;

    public SocioDto(Socio socio) {
        this.id = socio.getId();
        this.email = socio.getEmail();
        this.username = socio.getUsername();
        this.nome = socio.getNome();
        this.grauSocio = socio.getGrauSocio();
        this.dataInicial = socio.getDataInicial();
        this.isAtivo = socio.isAtivo();
        this.cargoDirtetoria = socio.getCargoDirtetoria();
        this.isDiretoria = socio.getIsDiretoria();
        this.enderecoPrincipal = socio.getEnderecoPrincipal();
        this.socioTesouraria = new SocioTesourariaDto(socio.getSocioTesouraria());
    }

    public double somaValorDependentes(Socio socio) {
        this.socioTesouraria = new SocioTesourariaDto(socio.getSocioTesouraria(), true);
        double valorTotal = 0.0;
        double soma = 0.0;
        for (FichaContaDto fichaConta : socioTesouraria.getFichaContas()) {
            if (fichaConta != null
                    && TipoEntradaFicha.MENSALIDADE.getName().equals(fichaConta.getDescricao())) {
                soma += fichaConta.getValor();
            }
        }

        valorTotal += soma;

        soma = 0.0;
        for (FichaContaDto fichaConta : socioTesouraria.getFichaContas()) {
            if (fichaConta != null
                    && !TipoEntradaFicha.MENSALIDADE.getName().equals(fichaConta.getDescricao())) {
                soma += fichaConta.getValor();
            }
        }
        valorTotal += soma;
        return valorTotal;

    }
}
