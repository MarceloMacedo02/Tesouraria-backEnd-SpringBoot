package br.com.areadigital.aplicativo.Controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.areadigital.aplicativo.dtos.ConsultaPaginadaDto;
import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.exceptions.ErrorProcessingException;
import br.com.areadigital.aplicativo.services.AbstractService;
import jakarta.validation.Valid;

public abstract class AbstractController<K, T extends BaseEntity<K>, D extends BaseEntity<K>> {

    public abstract AbstractService<K, T, D> getService();

    @GetMapping
    public ResponseEntity<List<D>> findAll() throws ErrorProcessingException {
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable("id") final K id) throws ErrorProcessingException {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PutMapping
    public ResponseEntity<D> update(@Valid @RequestBody final T obj) throws ErrorProcessingException {
        return ResponseEntity.ok(getService().update(obj));

    }

    @PostMapping
    public ResponseEntity<D> create(@Valid @RequestBody final T obj) throws ErrorProcessingException {
        return ResponseEntity.ok(getService().create(obj));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final K id) throws ErrorProcessingException {
        getService().delete(id);
        return ResponseEntity.ok().build();
    }

    // consulta paginada
    @GetMapping(path = "/pagination")
    public ResponseEntity<Page<?>> findAllWithPaginationAndSorting(@Valid @RequestBody ConsultaPaginadaDto consulta)
            throws ErrorProcessingException {
        return ResponseEntity
                .ok(getService().findAllWithPaginationAndSorting(consulta));
    }

}
