package com.timnet.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Daniel.Diaconu on 17/07/10.
 */
public class MyUnitTest {

    @Test
    public void testConcatenate() {
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);

    }

    @Test
    public void testConcatenateNulls() {
        MyUnit myUnit = new MyUnit();
        String result = myUnit.concatenate(null, null);
        assertEquals(null, result);

        result = myUnit.concatenate("one", null);
        assertEquals("one", result);

        result = myUnit.concatenate("two", null);
        assertEquals("two", result);
    }

//    @Test
//    MyUnit myUnit = new MyUnit();
//
//    assertTrue(myUnit.getTheBoolean());
//
//    assertFalse(myUnit.getTheBoolean());
}


