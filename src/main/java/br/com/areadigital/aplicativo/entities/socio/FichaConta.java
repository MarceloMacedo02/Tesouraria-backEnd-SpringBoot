package br.com.areadigital.aplicativo.entities.socio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.entities.Financeiro.ContaFinanceira;
import br.com.areadigital.aplicativo.entities.convertrs.TipoEntradaFichaConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tb_ficha_conta")
@NoArgsConstructor
@AllArgsConstructor
public class FichaConta implements Serializable, BaseEntity<String> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Convert(converter = TipoEntradaFichaConverter.class)
    private String descricao;

    @JoinColumn
    @ManyToOne
    private ContaFinanceira contaFinanceira = new ContaFinanceira();

    private double valor;
    @OneToMany
    private List<FichaSocio> fichaSocio = new ArrayList<>();
}
