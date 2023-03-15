package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.Collection;


public interface BaseService  <E extends BaseEntity> {
    void create(E e);

    E getById(Long id);

    Collection<E> getAll();

    void update(E e);

    void delete(E e);
}
