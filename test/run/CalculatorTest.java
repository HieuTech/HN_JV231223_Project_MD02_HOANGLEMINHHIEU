package run;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {



    @Test
    @DisplayName("Add 1 and 2")
    public void testAdd1And2(){

        int first = 1;
        int second = 2;
        int expect = 3;
        int result = Main.add(first, second);
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("Add 1 and -2")
    public void testAdd1AndSub2(){

        int first = 1;
        int second = -2;
        int expect = -1;
        int result = Main.add(first, second);
        assertEquals(expect, result);
    }
}