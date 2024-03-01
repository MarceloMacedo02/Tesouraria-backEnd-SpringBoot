package br.com.areadigital.aplicativo.entities.convertrs;

import br.com.areadigital.aplicativo.entities.enums.TipoMovimento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class TipoMovimentoConverter implements AttributeConverter<String, Integer> {
    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        return TipoMovimento.toId(attribute);
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        return TipoMovimento.toName(dbData);
    }
}
