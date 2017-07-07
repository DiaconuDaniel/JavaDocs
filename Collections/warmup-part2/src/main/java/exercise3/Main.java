package exercise3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Daniel.Diaconu on 17/07/07.
 */
public class Main {



    public static void main(String[] args) {
        Map<AnotherStudent, BigDecimal> map1 = new HashMap<AnotherStudent, BigDecimal>();
        Map<AnotherStudent2, BigDecimal> map2 = new HashMap<AnotherStudent2, BigDecimal>();
        Map<AnotherStudent3, BigDecimal> map3 = new HashMap<AnotherStudent3, BigDecimal>();
        Map<AnotherStudent4, BigDecimal> map4 = new HashMap<AnotherStudent4, BigDecimal>();

        map1.put(new AnotherStudent("Florin", "Oprescu"), new BigDecimal(10));
        map1.put(new AnotherStudent("Ene", "Marian"), new BigDecimal(10));
        map1.put(new AnotherStudent("Florin", "Oprescu"), new BigDecimal(10));
        map2.put(new AnotherStudent2("Marius", "Popescu"), new BigDecimal(4));
        map2.put(new AnotherStudent2("Dutu", "Constantin"), new BigDecimal(4));
        map3.put(new AnotherStudent3("Alexandru", "Popovici"), new BigDecimal(21));
        map4.put(new AnotherStudent4("George", "Prunea"), new BigDecimal(32));

        method(map1);
        method(map2);

    }

    public static <V> void method(Map<V, BigDecimal> maps){
        Iterator it = maps.entrySet().iterator();
        while (it.hasNext()){
            V element = (V) it.next();
            System.out.println(element.toString());
        }
    }
}
