package ro.teamnet.zth.api.em;

import javafx.util.Builder;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.rmi.activation.ActivationGroup_Stub;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel.Diaconu on 17/07/13.
 */
public class EntityManagerImpl implements EntityManager {

    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        if (entityClass == null) return null;
        if (id == null) return null;
        QueryBuilder builder = new QueryBuilder();
        Connection conn = DBManager.getConnection();

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);

        Condition condition = new Condition();

        for (ColumnInfo columnInfo : columns) {
            if (columnInfo.isId()) {
                condition.setColumnName(columnInfo.getDbColumnName());
                condition.setValue(id);
            }
        }
        builder.setTableName(tableName);
        builder.setQueryType(QueryType.SELECT);
        builder.addQueryColumns(columns);
        builder.addCondition(condition);
        String query = builder.createQuery();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                T elem = entityClass.newInstance();
                for (ColumnInfo each : columns) {
                    Field field = elem.getClass().getDeclaredField(each.getColumnName());
                    field.setAccessible(true);
                    field.set(elem, EntityUtils.castFromSqlType(
                            resultSet.getObject(each.getDbColumnName()),
                            each.getColumnType()));
                }
                conn.close();
                return elem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    @Override
    public Long getNextIdVal(String tableName, String columnIdName) {
        try (
                Connection connection = DBManager.getConnection();
                Statement stmt = connection.createStatement()
        ) {
            ResultSet resultSet;
            resultSet = stmt.executeQuery("Select maxim" + columnIdName + "from" + tableName);
            resultSet.next();
            return resultSet.getLong(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object insert(Object entity) {
        if (entity == null) return null;

        QueryBuilder builder = new QueryBuilder();
        Connection conn = DBManager.getConnection();

        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

        Long id = new Long(0);

        for (ColumnInfo columnInfo : columns) {
            if (columnInfo.isId()) {
                Long nextIdVal = getNextIdVal(tableName, columnInfo.getColumnName());
                columnInfo.setValue(nextIdVal);
                id = nextIdVal;
            } else {
                Field field = null;
                try {
                    field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                    field.setAccessible(true);
                    columnInfo.setValue(field.get(entity));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        builder.setTableName(tableName);
        builder.setQueryType(QueryType.INSERT);
        builder.addQueryColumns(columns);

        String query = builder.createQuery();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            return findById(entity.getClass(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        //if(entityClass == null) return null;
        QueryBuilder builder = new QueryBuilder();
        Connection conn = DBManager.getConnection();


        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);


        builder.setTableName(tableName);
        builder.setQueryType(QueryType.SELECT);
        builder.addQueryColumns(columns);

        String query = builder.createQuery();

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            ArrayList<T> toReturnList = new ArrayList<T>();
            while (resultSet.next()) {
                T elem = entityClass.newInstance();
                for (ColumnInfo columnInfo : columns) {
                    Field field = elem.getClass().getDeclaredField(columnInfo.getColumnName());
                    field.setAccessible(true);
                    field.set(elem, EntityUtils.castFromSqlType(resultSet.getObject(columnInfo.getDbColumnName()), columnInfo.getColumnType()));
                }
                toReturnList.add(elem);
            }
            return toReturnList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public <T> T update(T entity) throws NoSuchFieldException, IllegalAccessException {

        try {
            Connection connection = DBManager.getConnection();
            String getTable = EntityUtils.getTableName(entity.getClass());

            List<ColumnInfo> column = EntityUtils.getColumns(entity.getClass());
            Long id = new Long(0);

            for (ColumnInfo columnInfo : column) {

                Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                field.setAccessible(true);
                columnInfo.setValue(field.get(entity));

            }
                Condition condition = new Condition();
                condition.setValue(column.get(0).getValue());
                condition.setColumnName(column.get(0).getDbColumnName());


                QueryBuilder queryBuilder = new QueryBuilder();
                queryBuilder.setQueryType(QueryType.UPDATE);
                queryBuilder.setTableName(getTable);
                queryBuilder.addCondition(condition);


                String query = queryBuilder.createQuery();
                Statement stmt = null;
                try {
                    stmt = connection.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);
                    return (T) findById(entity.getClass(), id);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return update(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Object entity) {
        try{
            Condition connection = (Condition) DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> column = EntityUtils.getColumns(entity.getClass());

            for(ColumnInfo columnInfo : column){
                Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                field.setAccessible(true);
                columnInfo.setValue(field.get(entity));
            }

            Condition condition = new Condition();
            condition.setColumnName(column.get(0).getDbColumnName());
            condition.setValue(column.get(0).getValue());

            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.setQueryType(QueryType.DELETE);
            queryBuilder.setTableName(tableName);
            queryBuilder.addQueryColumns(column);
            queryBuilder.addCondition(condition);

            String sql = queryBuilder.createQuery();
            Statement stmt = null;
            stmt.executeUpdate(sql);


        }catch (Exception e){
            e.printStackTrace();
        }


    }



    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) {
        try(Connection connection = DBManager.getConnection();
            Statement stmt  = connection.createStatement();
        ){

            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> column = EntityUtils.getColumns(entityClass.getClass());

            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.setQueryType(QueryType.SELECT);
            queryBuilder.setTableName(tableName);
            queryBuilder.addCondition((Condition) connection);

            for(String key : params.keySet()){
                Condition condition = new Condition();
                condition.setColumnName(key);
                condition.setValue(params.get(key));
                queryBuilder.addCondition(condition);
            }


            String sql = queryBuilder.createQuery();
            ResultSet resultSet = stmt.executeQuery(sql);

            List<T> insta = new ArrayList<T>();
            while(resultSet.next()) {
                T instance = entityClass.newInstance();
                for(ColumnInfo columnInfo : column){
                    Field filed = instance.getClass().getDeclaredField(columnInfo.getColumnName());
                    filed.setAccessible(true);
                    filed.set(instance,EntityUtils.castFromSqlType(resultSet.getObject(columnInfo.getDbColumnName()),columnInfo.getColumnType()));
                }
            }
            return insta;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}