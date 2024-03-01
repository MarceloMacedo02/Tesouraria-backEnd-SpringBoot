package br.com.areadigital.aplicativo.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class CampoPesquisaDto {
    private String campoPesquisa;
    private String valorPesquisado;

    public CampoPesquisaDto(String campoPesquisa, String valorPesquisado) {
        this.campoPesquisa = campoPesquisa;
        this.valorPesquisado = valorPesquisado;
    }
}