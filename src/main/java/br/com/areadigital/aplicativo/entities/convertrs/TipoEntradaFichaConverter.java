package br.com.areadigital.aplicativo.entities.convertrs;

import br.com.areadigital.aplicativo.entities.enums.TipoEntradaFicha;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class TipoEntradaFichaConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        if (attribute == null) {
            return null;
        }
        return TipoEntradaFicha.returnId(attribute);
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return TipoEntradaFicha.returnName(dbData);
    }

}
