package br.com.areadigital.aplicativo.services;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.areadigital.aplicativo.dtos.CampoPesquisaDto;
import br.com.areadigital.aplicativo.dtos.ConsultaPaginadaDto;
import br.com.areadigital.aplicativo.entities.BaseEntity;
import br.com.areadigital.aplicativo.exceptions.EntityNotFoundException;
import br.com.areadigital.aplicativo.exceptions.ErrorProcessingException;
import br.com.areadigital.aplicativo.exceptions.UnsavedEntityException;
import br.com.areadigital.aplicativo.mappers.AbstractMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractService<K, T extends BaseEntity<K>, D extends BaseEntity<K>> {

  public abstract JpaRepository<T, K> getRepository();

  public abstract AbstractMapper<T, D, K> getMapper();

  //
  @SuppressWarnings("unchecked")
  public Class<T> getParameterClass() {
    Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
        .getActualTypeArguments()[1];
    return entityClass;
  }

  public D findById(final K id) throws ErrorProcessingException,
      EntityNotFoundException {
    try {
      T entity = this.getRepository().findById(id).orElseThrow(() -> new EntityNotFoundException());
      return this.getMapper().toDTO(entity);
    } catch (final EntityNotFoundException e) {
      throw e;
    } catch (final Exception e) {
      log.error(getParameterClass().getSimpleName() + "  findById(\"{}\"): {}", id, e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  public List<D> findAll() throws ErrorProcessingException {
    try {
      List<T> entities = this.getRepository().findAll();
      return this.getMapper().toDTOList(entities);

    } catch (final Exception e) {
      log.error(getParameterClass().getSimpleName() + " findAll(): {}", e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @SuppressWarnings("null")
  public D save(final T obj) throws UnsavedEntityException {
    try {
      T entity = getRepository().findById(obj.getId()).get();
      BeanUtils.copyProperties(obj, entity);
      entity = this.getRepository().save(entity);
      return this.getMapper().toDTO(entity);
    } catch (final Exception e) {
      log.error(getParameterClass().getSimpleName() + "save(\"{}\"): {}", obj.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  public D create(final T obj) throws UnsavedEntityException {
    try {

      obj.setId(null);
      return this.getMapper().toDTO(this.getRepository().save(obj));
    } catch (final Exception e) {
      log.error(getParameterClass().getSimpleName() + " create(\"{}\"): {}", obj.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  @SuppressWarnings("null")
  public D update(final T obj) throws UnsavedEntityException {
    try {
      T entity = getRepository().findById(obj.getId()).get();
      BeanUtils.copyProperties(obj, entity, getNullPropertyNames(obj));
      entity = this.getRepository().save(entity);
      return this.getMapper().toDTO(entity);
    } catch (final Exception e) {
      log.error(getParameterClass().getSimpleName() + " update(\"{}\"): {}", obj.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  public void delete(final K id) throws ErrorProcessingException {
    try {
      this.getRepository().deleteById(id);
    } catch (final Exception e) {
      log.error(getParameterClass().getSimpleName() + "  delete(\"{}\"): {}", id, e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  private static String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<String>();
    for (java.beans.PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null || srcValue.toString().isEmpty())
        emptyNames.add(pd.getName());
    }
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }

  public Page<D> findAllWithPaginationAndSorting(ConsultaPaginadaDto consultaPaginadaDTO) {
    CampoPesquisaDto[] campoPesquisa = consultaPaginadaDTO.getCampoPesquisa();
    int page = consultaPaginadaDTO.getPage();
    int tamanho = consultaPaginadaDTO.getTamanho();
    String ordem = consultaPaginadaDTO.getOrdem();
    String ordenarPor = consultaPaginadaDTO.getOrdenarPor();

    PageRequest pageRequest = PageRequest.of(page, tamanho, Sort.Direction.fromString(ordem), ordenarPor);

    ExampleMatcher matcher = ExampleMatcher.matching();
    if (campoPesquisa.length > 0) {
      for (CampoPesquisaDto campoPesquisaDto : campoPesquisa) {
        matcher = matcher.withMatcher(campoPesquisaDto.getCampoPesquisa(),
            ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
      }
    } else
      throw new IllegalArgumentException("Nenhum campo de pesquisa informado.");

    T obj;
    try {
      obj = getParameterClass().getDeclaredConstructor().newInstance();
      for (CampoPesquisaDto campoPesquisaDto : campoPesquisa) {
        String valorPesquisa = campoPesquisaDto.getValorPesquisado();
        String campoPesquisaString = campoPesquisaDto.getCampoPesquisa();

        setFieldValue(obj, campoPesquisaString, valorPesquisa);
      }

    } catch (Exception e) {
      throw new ErrorProcessingException(e.getMessage());
    }

    Example<T> exemplo = Example.of(obj, matcher);

    Page<T> result = getRepository().findAll(exemplo, pageRequest);
    return result.map(getMapper()::toDTO);
  }

  public void setFieldValue(Object object, String fieldName, Object value) {
    try {
      Class<?> clazz = object.getClass();
      Field field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      field.set(object, value);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      System.out.println(e.getMessage());
    }
  }
}
