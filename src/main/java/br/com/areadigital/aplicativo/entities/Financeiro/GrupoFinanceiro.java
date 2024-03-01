package br.com.areadigital.aplicativo.entities.Financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.entities.convertrs.TipoMovimentoConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tb_grupo_financeiro")
public class GrupoFinanceiro implements BaseEntity<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    private String descricao;

    @Convert(converter = TipoMovimentoConverter.class)
    private String tipo;

    @OneToMany(mappedBy = "grupoFinanceiro", cascade = CascadeType.ALL)
    private List<ContaFinanceira> contaFinanceira = new ArrayList<>();

}
