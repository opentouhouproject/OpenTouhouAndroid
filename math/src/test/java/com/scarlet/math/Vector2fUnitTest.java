package com.scarlet.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
 * Unit testing for the Vector2f class.
 */
public class Vector2fUnitTest {
    private static double ERROR = 0.0001;

    @Test
    public void test_dot() {
        Vector2f u = new Vector2f(3, -4);
        Vector2f v = new Vector2f(1, 2);

        assertEquals(-5.0f, u.dot(v), ERROR);
    }

    @Test
    public void test_static_dotProduct() {
        Vector2f u = new Vector2f(3, -4);
        Vector2f v = new Vector2f(1, 2);

        assertEquals(-5.0f, Vector2f.dotProduct(u, v), ERROR);
    }

    @Test
    public void test_distance() {
        Vector2f u = new Vector2f(3, -4);
        Vector2f v = new Vector2f(1, 2);

        assertEquals(6.32455f, u.distance(v), ERROR);
    }

    @Test
    public void test_static_distance() {
        Vector2f u = new Vector2f(3, -4);
        Vector2f v = new Vector2f(1, 2);

        assertEquals(6.32455f, Vector2f.distance(u, v), ERROR);
    }

    @Test
    public void test_distanceSquare() {
        Vector2f u = new Vector2f(3, -4);
        Vector2f v = new Vector2f(1, 2);

        assertEquals(40.0f, u.distanceSquare(v), ERROR);
    }

    @Test
    public void test_static_distanceSquare() {
        Vector2f u = new Vector2f(3, -4);
        Vector2f v = new Vector2f(1, 2);

        assertEquals(40.0f, Vector2f.distanceSquare(u, v), ERROR);
    }
}