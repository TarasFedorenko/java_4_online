package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.BaseEntity;
import java.util.Collection;
import java.util.Optional;

public interface BaseDao<E extends BaseEntity> {

    void create(E e);

    Optional<E> getById(Long id);

    Collection<E> getAll();

    void update(E e);

    void delete(E e);
}
