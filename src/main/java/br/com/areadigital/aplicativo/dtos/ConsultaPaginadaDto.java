package br.com.areadigital.aplicativo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaPaginadaDto {
    CampoPesquisaDto[] campoPesquisa;
    private int page;
    private int tamanho;
    private String ordem;
    private String ordenarPor;

}
