package br.com.areadigital.aplicativo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.areadigital.aplicativo.entities.socio.FichaSocio;

@Repository
public interface FichaSocioRepository extends JpaRepository<FichaSocio, String> {

}
