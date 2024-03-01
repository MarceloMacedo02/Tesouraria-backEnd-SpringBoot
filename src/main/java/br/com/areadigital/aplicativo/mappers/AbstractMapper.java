package br.com.areadigital.aplicativo.mappers;

import java.util.List;

import br.com.areadigital.aplicativo.entities.BaseEntity;

public interface AbstractMapper<T extends BaseEntity<K>, D, K> {

    T toEntity(D dto);

    D toDTO(T entity);

    List<T> toEntityList(List<D> dtoList);

    List<D> toDTOList(List<T> entityList);

}