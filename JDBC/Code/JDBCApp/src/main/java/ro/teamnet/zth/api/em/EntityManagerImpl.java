package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel.Diaconu on 17/07/13.
 */
public class EntityManagerImpl implements EntityManager {


    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        try (
                Connection connection = DBManager.getConnection();
                Statement stmt = connection.createStatement();
        ) {

            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> tableColumn = EntityUtils.getColumns(entityClass);
            List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass, Id.class);

            Condition condition = new Condition();
            condition.setColumnName(fields.get(0).getAnnotation(Id.class).name());
            condition.setValue(id);

            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.setTableName(QueryType.SELECT);
            queryBuilder.setTableName(tableName);
            queryBuilder.addQueryColumns(tableColumn);
            queryBuilder.addCondition(condition);


            ResultSet rs = stmt.executeQuery(queryBuilder.createQuery());

            T instance = null;
            if (rs.next()) {
                instance = entityClass.newInstance();
                for (ColumnInfo column : tableColumn) {
                    column.setValue(rs.getObject(column.getDbColumnName()));
                    Field field = instance.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                }
            }

            return instance;


        } catch (Exception e) {
            e.printStackTrace();
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

        }


        return null;
    }

    @Override
    public <T> Object insert(T entity) {
        try (
                Connection connection = DBManager.getConnection();
                Statement stmt = connection.createStatement();

        ) {
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> tableColumns = EntityUtils.getColumns(entity.getClass());

            Long lastId = null;
            String stringId = null;

            for(ColumnInfo columnInfo : tableColumns){

                if(columnInfo.isId()){
                    Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                    field.setAccessible(true);

                    if(field.getType().equals(String.class)){
                        columnInfo.setValue(EntityUtils.getSqlValue(field.get(entity)));
                        stringId = (String)EntityUtils.getSqlValue(field.get(entity));

                    }else{
                        lastId = getNextIdVal(tableName, columnInfo.getColumnName());
                        columnInfo.setValue(lastId);
                    }
                }else {
                    Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                    field.setAccessible(true);
                    columnInfo.setValue(EntityUtils.getSqlValue(field.get(entity)));
                }
            }
                QueryBuilder queryBuilder = new QueryBuilder();
                queryBuilder.setQueryType(QueryType.INSERT);
                queryBuilder.setTableName(tableName);
                queryBuilder.addQueryColumns(tableColumns);

                String sql = queryBuilder.createQuery();
                stmt.executeUpdate(sql);
            if(lastId== null){
                return true;
            }else{
                return (T) findById(entity.getClass(),lastId);
            }

            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public <T> List<T> findAll (Class < T > entityClass) {
                try(
                        Connection connection = DBManager.getConnection();
                        Statement stmt = connection.createStatement();

                ){
                    String tableName = EntityUtils.getTableName(entityClass);
                    List<ColumnInfo> tableColumns = EntityUtils.getColumns(entityClass);

                    QueryBuilder queryBuilder = new QueryBuilder();
                    queryBuilder.setQueryType(QueryType.SELECT);
                    queryBuilder.setTableName(tableName);
                    queryBuilder.addCondition((Condition) tableColumns);

                    String sql = queryBuilder.createQuery();
                    ResultSet resultSet = stmt.executeQuery(sql);
                    List<T> insta = new ArrayList<>();

                    while(resultSet.next()){
                        T instance = entityClass.newInstance();
                            for(ColumnInfo columnInfo : tableColumns){
                                columnInfo.setValue(resultSet.getObject(columnInfo.getDbColumnName()));

                                Field field = instance.getClass().getDeclaredField(columnInfo.getColumnName());
                                field.setAccessible(true);
                                field.set(instance, EntityUtils.castFromSqlType(columnInfo.getValue(), columnInfo.getColumnType()));
                            }

                            insta.add(instance);
                    }

                    return insta;



                }catch (Exception e){
                    e.printStackTrace();
                }

            return null;
        }
    }
