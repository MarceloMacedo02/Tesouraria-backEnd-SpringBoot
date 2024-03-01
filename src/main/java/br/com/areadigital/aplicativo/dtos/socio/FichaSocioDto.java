package br.com.areadigital.aplicativo.dtos.socio;

import java.util.Date;
import java.util.List;

import br.com.areadigital.aplicativo.entities.socio.FichaSocio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaSocioDto {
    private String id;
    private FichaContaDto fichaConta;
    private Date dataVencimento;
    private Date dataPagamento;
    private String statusPagamento;
    private String origemPagamento;
    private String observacao;
    private List<MovimentoDto> movimento;

    public FichaSocioDto(FichaSocio fichaSocio) {
        if (fichaSocio == null) {
            return;

        }
        this.id = fichaSocio.getId();
        this.fichaConta = new FichaContaDto(fichaSocio.getFichaConta());
        this.dataVencimento = fichaSocio.getDataVencimento();
    }
}
