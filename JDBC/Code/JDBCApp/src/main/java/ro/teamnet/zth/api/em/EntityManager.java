package ro.teamnet.zth.api.em;

import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by Daniel.Diaconu on 17/07/13.
 */
public interface EntityManager {

    <T> T findById(Class<T> entityClass, Long id);

        Long getNextIdVal(String tableName, String columnIdName);

    <T> Object insert(T entity);

    <T> List<T> findAll(Class<T> entityClass);

}
