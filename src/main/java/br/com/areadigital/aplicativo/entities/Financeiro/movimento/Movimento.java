package br.com.areadigital.aplicativo.entities.Financeiro.movimento;

import java.io.Serializable;
import java.util.Date;

import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.entities.Financeiro.ContaFinanceira;
import br.com.areadigital.aplicativo.entities.convertrs.StatusMovimentoConverter;
import br.com.areadigital.aplicativo.entities.convertrs.TipoMovimentoConverter;
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
@Entity(name = "tb_movimento")
public class Movimento implements Serializable, BaseEntity<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private double valor;
    private double jurus;
    private double multa;
    private String numeroNf;
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    private String origemRecebimento;
    @Convert(converter = StatusMovimentoConverter.class)
    private String statusMovimento;
    @Convert(converter = TipoMovimentoConverter.class)
    private String tipoMovimento;

    @ManyToOne
    private ContaFinanceira contaFinanceira = new ContaFinanceira();
}
