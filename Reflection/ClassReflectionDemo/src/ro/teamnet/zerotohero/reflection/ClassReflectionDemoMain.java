package ro.teamnet.zerotohero.reflection;

import ro.teamnet.zerotohero.reflection.demoobject.*;

import java.lang.Number;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {
    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //TODO get the class for a String object, and print it
        Class c = "object".getClass();
        System.out.println(c);

        //TODO get the class of an Enum, and print it
        Class enumClass = Enum.class;
        System.out.println(enumClass);


        //TODO get the class of a collection, and print it
        Class cls = Collection.class;
        System.out.println(cls);


        //TODO get the class of a primitive type, and print it
        //boolean b = true;
        c = int.class;
        System.out.println(c);
//        Integer inte = new Integer("integer").getClass();
//        System.out.println(inte);


        //TODO get and print the class for a field of primitive type
        MyClass fie = new MyClass();
        Field myField = fie.getClass().getDeclaredField("x");
        System.out.println(myField);


        //  TODO get and print the class for a primitive type, using the wrapper class

        Class in = Boolean.TYPE;
        System.out.println(in);

        //TODO get the class for a specified class name
        Class cls1 = Class.forName("ro.teamnet.zerotohero.reflection.demoobject.MyClass2");
        System.out.println(cls1);


        //TODO get the superclass of a class, and print it

        Class cls2 = ClassParent.class.getSuperclass();
        System.out.println(cls2);

        //TODO get the superclass of the superclass above, and print it

        Class cls3 = ClassParent.class.getSuperclass().getSuperclass();
        System.out.println(cls3);


        //TODO get and print the declared classes within some other class
        Class<?>[] cls20 = DeclaredClasses.class.getDeclaredClasses();
        for (int i = 0; i < cls20.length; i++) {
            System.out.println(cls20[i].getName());
        }


        //TODO print the number of constructors of a class
        int cls5 = Number.class.getDeclaredConstructors().length;
        System.out.println(cls5);

        //TODO get and invoke a public constructor of a class
        Constructor const1 = Numb.class.getConstructor(int.class);
        Numb n1 = (Numb) const1.newInstance(5);
        System.out.println(n1.a);


        //TODO get and print the class of one private field
        Numb n2 = new Numb(3);
        Field privateInt = Numb.class.getDeclaredField("b");
        privateInt.setAccessible(true);
        int x = (int) privateInt.getInt(n2);
        System.out.println(x);


        //TODO set and print the value of one private field for an object

        privateInt.set(n2, 45);
        System.out.println((int) privateInt.getInt(n2));


        //TODO get and print only the public fields class
        Field[] field1 = Numb.class.getFields();

        System.out.println(field1[0].get(n2));
        System.out.println(field1[1].get(n2));


        //TODO get and invoke one public method of a class

        Numb n7 = new Numb(4);
        Method m = Numb.class.getMethod("method200");
        System.out.println(m.invoke(n7));


        //TODO get and invoke one inherited method of a class

        Numb n8 = new Numb(4);
        Method m1 = Numb.class.getMethod("method");
        System.out.println(m1.invoke(n8));


        //TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
        //TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
        //what do you observe?

    }
}
