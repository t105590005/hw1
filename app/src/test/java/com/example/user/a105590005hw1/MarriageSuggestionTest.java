package com.example.user.a105590005hw1;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 2018/3/25.
 */

public class MarriageSuggestionTest {
    @Test
    public void getSuggestion(){
        MarriageSuggestion marriageSuggestion=new MarriageSuggestion();
        String s=marriageSuggestion.getSuggestion("male",30,12);
        assertEquals(s,"建議：還不急");
        s=marriageSuggestion.getSuggestion("male",35,5);
        assertEquals(s,"建議：開始找對象");
        s=marriageSuggestion.getSuggestion("male",45,3);
        assertEquals(s,"建議：開始找對象");
        s=marriageSuggestion.getSuggestion("famale",25,3);
        assertEquals(s,"建議：趕快結婚");
        s=marriageSuggestion.getSuggestion("female",35,5);
        assertEquals(s,"建議：開始找對象");
        s=marriageSuggestion.getSuggestion("female",45,8);
        assertEquals(s,"建議：趕快結婚");
    }
}
