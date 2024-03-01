package br.com.areadigital.aplicativo.entities.socio;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.entities.convertrs.CargoDirtetoriaConverter;
import br.com.areadigital.aplicativo.entities.convertrs.GrauSocioConverter;
import br.com.areadigital.aplicativo.entities.enums.GrauSocio;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tb_socio")
public class Socio extends Pessoa implements BaseEntity<String> {

    private static final long serialVersionUID = 1L;

    private String email;

    private String password = "$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG";

    @Column(unique = true)
    private String username = RandomStringUtils.randomAlphanumeric(7);

    private String nome;

    @Convert(converter = GrauSocioConverter.class)
    private String grauSocio = GrauSocio.DISCIPULO.name();

    private boolean isAtivo = true;

    @Temporal(TemporalType.DATE)
    private Date dataInicial = new Date();

    @Convert(converter = CargoDirtetoriaConverter.class)
    private String cargoDirtetoria;

    private Boolean isDiretoria = false;

    @Embedded
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @Column(name = "id")
    private EnderecoPrincipal enderecoPrincipal;

    @JsonIgnore
    @OneToOne(mappedBy = "socio", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    private SocioTesouraria socioTesouraria;
}
