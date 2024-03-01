package br.com.areadigital.aplicativo.dtos.financeiro;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class BaseDescricaoDto {
    private String id;
    private String descricao;

    public BaseDescricaoDto(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
