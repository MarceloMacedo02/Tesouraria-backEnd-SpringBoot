package br.com.areadigital.aplicativo.dtos.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.entities.Financeiro.GrupoFinanceiro;
import lombok.Data;

@Data
public class GrupoFinanceiroDto implements Serializable, BaseEntity<String> {
    private String id;
    private String descricao;
    private String tipo;

    private List<ContaFinanceiraDto> contaFinanceira = new ArrayList<>();

    public GrupoFinanceiroDto(GrupoFinanceiro grupoFinanceiro) {

        this.id = grupoFinanceiro.getId();
        this.descricao = grupoFinanceiro.getDescricao();
        this.tipo = grupoFinanceiro.getTipo();
        this.contaFinanceira = grupoFinanceiro.getContaFinanceira().stream().map(ContaFinanceiraDto::new).toList();

    }

}