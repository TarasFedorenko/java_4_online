package ua.com.alevel.controller;

import ua.com.alevel.persistence.entity.BaseEntity;
import java.io.BufferedReader;
import java.io.IOException;

public interface BaseController<E extends BaseEntity> {
    void create(BufferedReader buff) throws IOException;

    void getById(BufferedReader buff) throws IOException;

    void getAll();

    void update(BufferedReader buff) throws IOException;

    void delete(BufferedReader buff) throws IOException;
}
