package br.com.areadigital.aplicativo.entities.convertrs;

import br.com.areadigital.aplicativo.entities.enums.StatusMovimento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusMovimentoConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        if (attribute == null) {
            return null;
        }
        return StatusMovimento.returnId(attribute);
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return StatusMovimento.returnName(dbData);
    }

}
