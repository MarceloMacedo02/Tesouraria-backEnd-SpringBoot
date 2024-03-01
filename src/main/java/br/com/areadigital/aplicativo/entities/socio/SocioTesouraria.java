package br.com.areadigital.aplicativo.entities.socio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.areadigital.aplicativo.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_socio_tesouraria")
public class SocioTesouraria implements Serializable, BaseEntity<String> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @OneToOne
    @JoinColumn(name = "socio")
    private Socio socio;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_socio_ficha_socio", joinColumns = @JoinColumn(name = "socio_tesouraria_id"), inverseJoinColumns = @JoinColumn(name = "ficha_socio_id"))
    private List<FichaSocio> fichaSocios = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_fsocio_dependente", joinColumns = @JoinColumn(name = "fichafinanceirasocio_id"), inverseJoinColumns = @JoinColumn(name = "socio_id"))
    private List<Socio> dependentes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_socio_tesuraria_ficha_conta", joinColumns = @JoinColumn(name = "fichafinanceirasocio_id"), inverseJoinColumns = @JoinColumn(name = "fichaConta_id"))
    private List<FichaConta> fichaContas = new ArrayList<>();
}
