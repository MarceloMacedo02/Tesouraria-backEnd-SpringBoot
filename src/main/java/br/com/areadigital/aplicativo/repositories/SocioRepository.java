package br.com.areadigital.aplicativo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.areadigital.aplicativo.entities.socio.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, String> {
    Page<Socio> findAllByNomeContainingIgnoreCase(String nome, PageRequest pageable);

}
