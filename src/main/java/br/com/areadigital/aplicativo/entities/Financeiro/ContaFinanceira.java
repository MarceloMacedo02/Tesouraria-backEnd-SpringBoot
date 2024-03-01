package br.com.areadigital.aplicativo.entities.Financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.entities.Financeiro.movimento.Movimento;
import br.com.areadigital.aplicativo.entities.convertrs.TipoMovimentoConverter;
import br.com.areadigital.aplicativo.entities.socio.FichaConta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_conta_financeira")
public class ContaFinanceira implements BaseEntity<String>, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    private String descricao;

    @Convert(converter = TipoMovimentoConverter.class)
    private String tipo;
    @ManyToOne(cascade = CascadeType.ALL)
    private GrupoFinanceiro grupoFinanceiro = new GrupoFinanceiro();

    @OneToMany
    private List<FichaConta> fichaContas = new ArrayList<>();

    @Temporal(TemporalType.DATE)
    private Date dataCriacao = new Date();

    private double saldoInicial = 0.0;

    @OneToMany
    private List<Movimento> movimentos = new ArrayList<>();
}
