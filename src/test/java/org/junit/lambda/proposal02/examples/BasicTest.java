package org.junit.lambda.proposal02.examples;

import org.junit.lambda.proposal02.Person;
import org.junit.lambda.proposal02.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.lambda.proposal02.LambdaAssert.*;

/*
    The JUnit4 way of defining tests should still work.
    Moreover, we could provide a mechanism for names with any characters.
 */
public class BasicTest {

    @Test
    public void aJunit4CompatibleTest() {
        assertEquals(1, 1);
    }

    @Test("test name can be any string")
    public void methodNameDoesNotMatterHere() {
        assertEquals(1, 1);
    }

    /**
     * Using lambdas allows deferring the execution of "expensive" code
     */
    @Test
    public void testWithLambdaAssertion() {
        assertTrue(() -> true, () -> "failure message");
    }

    @Test
    public void testException() {
        assertException(() -> {
            throw new RuntimeException();
        }, RuntimeException.class, () -> "expected RTE");
    }

    /**
     * Sometimes we want to use more than one assert and get results of all.
     * assertAll will collect result of all into single test result
     */
    @Test
    public void testWithCombinedAsserts() {
        Person person = new Person("Johannes", "Link", "Germany");
        assertAll(
                () -> assertEquals("Johannes", person.getFirstName()),
                () -> assertEquals("Link", person.getFirstName()),
                () -> assertEquals("Germany", person.getFirstName())
        );
    }


}
