package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;
import ro.teamnet.zth.appl.domain.Department;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daniel.Diaconu on 17/07/12.
 */
public class EntityUtils {


    private EntityUtils() {
        throw new UnsupportedOperationException("Exception");
    }

    public static String getTableName(Class entity) {
        return ((Table) entity.getAnnotation(Table.class)).name();
    }


    public static List<ColumnInfo> getColumns(Class entity) {

        List<ColumnInfo> list = new LinkedList<>();

        Field field1[] = entity.getDeclaredFields();

        for (Field f : field1) {
            if (f.isAnnotationPresent(Column.class) || f.isAnnotationPresent(Id.class)) {
                ColumnInfo c = new ColumnInfo();
                c.setColumnName(f.getName());
                c.setColumnType(f.getType());
                //c.setDbColumnName(f.getAnnotations(Column.class).name());
                list.add(c);

                if (f.isAnnotationPresent(Column.class) == true) {
                   // c.setDbColumnName(f.getAnnotations(Column.class).name());
                    c.setId(false);


                } else {
                    //c.setDbColumnName(f.getAnnotations(Id.class).name());
                    c.setId(true);
                }
                list.add(c);
            }


        }

        return list;
    }

    public static void main(String[] args) {
        List<ColumnInfo> list1 = getColumns(Department.class);
        for(ColumnInfo lists : list1){
            System.out.println(lists.getColumnName());
            System.out.println(lists.getColumnType());
            System.out.println(lists.getDbColumnName());
            System.out.println("");

        }
    }

//    public static Object castFromSqlType(Object value, Class wantedType){
//
//        if(value instanceof BigDecimal ){
//            System.out.println(wantedType.getCanonicalName());
//        }
//
//
//    }


}
