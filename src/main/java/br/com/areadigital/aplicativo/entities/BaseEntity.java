package br.com.areadigital.aplicativo.entities;

public interface BaseEntity<K> {
    K getId();

    void setId(K id);
}
