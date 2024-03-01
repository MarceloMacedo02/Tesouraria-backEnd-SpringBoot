package br.com.areadigital.aplicativo.entities.socio;

import java.io.Serializable;
import java.util.Date;

import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.entities.convertrs.StatusMovimentoConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_ficha_socio")
public class FichaSocio implements Serializable, BaseEntity<String> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne
    private FichaConta fichaConta;

    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    @Convert(converter = StatusMovimentoConverter.class)
    private String statusPagamento;

    private String origemPagamento;

    private String observacao;

    // @ManyToMany(fetch = FetchType.EAGER)
    // @JoinTable(name = "tb_fsocio_Moviemnto", joinColumns = @JoinColumn(name =
    // "fichasocio_id"), inverseJoinColumns = @JoinColumn(name = "movimeneto_id"))
    // private List<Movimento> movimento;

}
