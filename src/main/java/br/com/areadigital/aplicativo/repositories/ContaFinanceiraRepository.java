package br.com.areadigital.aplicativo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.areadigital.aplicativo.entities.Financeiro.ContaFinanceira;

@Repository
public interface ContaFinanceiraRepository extends JpaRepository<ContaFinanceira, String> {
    List<ContaFinanceira> findByTipo(String tipo);

    List<ContaFinanceira> findAllByTipo(String tipo);

}