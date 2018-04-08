package com.example.user.myapplication;

/**
 * Created by user on 2018/4/8.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by hjki3 on 4/2/2018.
 */

public class WinOrLoseTest {
    private WinOrLose who;
    @Before public void setUp(){
        who=new WinOrLose();
    }
    @After public void tearDown(){
        who=null;
    }
    @Test
    public void testGetPlay(){
        assertEquals("win", who.who(1,3));
        assertEquals("lose", who.who(1,2));
        assertEquals("draw", who.who(1,1));
        assertEquals("win", who.who(2,1));
        assertEquals("lose", who.who(2,3));
        assertEquals("draw", who.who(2,2));
        assertEquals("win", who.who(3,2));
        assertEquals("lose", who.who(3,1));
        assertEquals("draw",who.who(3,3));
    }
}