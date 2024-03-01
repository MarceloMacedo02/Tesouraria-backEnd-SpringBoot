package br.com.areadigital.aplicativo.dtos.financeiro;

import java.io.Serializable;
import java.util.List;

import br.com.areadigital.aplicativo.dtos.socio.MovimentoDto;
import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.entities.Financeiro.ContaFinanceira;
import lombok.Data;

/**
 * A DTO for the
 * {@link br.com.areadigital.aplicativo.entities.Financeiro.ContaFinanceira}
 * entity
 */
@Data
public class ContaFinanceiraDto implements Serializable, BaseEntity<String> {
    private String id;
    private String descricao;
    private String tipo;
    private BaseDescricaoDto grupoFinanceiro;
    private List<MovimentoDto> movimentos;

    public ContaFinanceiraDto(ContaFinanceira contaFinanceira) {
        this.id = contaFinanceira.getId();
        this.descricao = contaFinanceira.getDescricao();
        this.tipo = contaFinanceira.getTipo();
        this.grupoFinanceiro = new BaseDescricaoDto(contaFinanceira.getGrupoFinanceiro().getId(),
                contaFinanceira.getGrupoFinanceiro().getDescricao());
        // this.movimentos =
        // contaFinanceira.getMovimentos().stream().map(MovimentoDto::new).toList();
    }
}