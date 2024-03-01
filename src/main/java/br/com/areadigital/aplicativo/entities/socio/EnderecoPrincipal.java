package br.com.areadigital.aplicativo.entities.socio;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class EnderecoPrincipal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(columnDefinition = "VARCHAR(255)")
    private String rua;
    @Column(columnDefinition = "VARCHAR(255)")
    private String numero;
    @Column(columnDefinition = "VARCHAR(255)")
    private String bairro;
    @Column(columnDefinition = "VARCHAR(255)")
    private String cidade;
    @Column(columnDefinition = "VARCHAR(255)")
    private String estado;
    @Column(columnDefinition = "VARCHAR(255)")
    private String cep;
    @Column(columnDefinition = "VARCHAR(255)")
    private String complemento;
    @Column(columnDefinition = "VARCHAR(255)")
    private String pontoReferencia;
    @Column(columnDefinition = "VARCHAR(255)")
    private String nomePontoReferencia;

}