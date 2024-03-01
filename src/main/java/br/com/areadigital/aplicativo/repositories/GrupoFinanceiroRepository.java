package br.com.areadigital.aplicativo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.areadigital.aplicativo.entities.Financeiro.GrupoFinanceiro;

@Repository
public interface GrupoFinanceiroRepository extends JpaRepository<GrupoFinanceiro, String> {
    List<GrupoFinanceiro> findAll(Sort sort);

    Page<GrupoFinanceiro> findAllByTipoAndDescricaoContainingIgnoreCase(String tipo, String descricao,
            PageRequest pageable);

}