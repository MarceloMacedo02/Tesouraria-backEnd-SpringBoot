package br.com.areadigital.aplicativo.dtos.socio;

import br.com.areadigital.aplicativo.dtos.financeiro.ContaFinanceiraDto;
import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.entities.socio.FichaConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FichaContaDto implements BaseEntity<String> {
    private String id;
    private String descricao;
    private ContaFinanceiraDto contaFinanceira;
    private double valor;

    public FichaContaDto(FichaConta fichaConta) {
        if (fichaConta == null) {
            return;
        }
        this.id = fichaConta.getId();
        this.descricao = fichaConta.getDescricao();
        this.contaFinanceira = new ContaFinanceiraDto(fichaConta.getContaFinanceira());
        this.valor = fichaConta.getValor();

    }
}
