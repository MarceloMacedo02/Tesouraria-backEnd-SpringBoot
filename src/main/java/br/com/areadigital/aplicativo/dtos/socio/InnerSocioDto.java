package br.com.areadigital.aplicativo.dtos.socio;

import br.com.areadigital.aplicativo.entities.socio.Socio;
import lombok.Data;

@Data
public class InnerSocioDto {
    private String id;
    private String nome;

    public InnerSocioDto(Socio socio) {
        this.id = socio.getId();
        this.nome = socio.getNome();
    }

    public InnerSocioDto(SocioDto socioDto) {
        this.id = socioDto.getId();
        this.nome = socioDto.getNome();

    }
}
