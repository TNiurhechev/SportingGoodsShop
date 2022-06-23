package db;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    //READ
    T get(long id);

    //READ
    List<T> getAll();

    //CREATE
    void save(T t) throws SQLException;

    //UPDATE
    void update(T t, String[] params);

    //DELETE
    void delete(T t) throws SQLException;
}
