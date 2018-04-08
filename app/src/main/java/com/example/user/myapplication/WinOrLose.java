package com.example.user.myapplication;

/**
 * Created by user on 2018/4/8.
 */

public class WinOrLose {
    public String who(int player , int iComPlay) {
        if(iComPlay == player) return "draw";
        if(iComPlay == 1 && player == 2) return "win";
        if(iComPlay == 2 && player == 3) return "win";
        if(iComPlay == 3 && player == 1) return "win";
        if(iComPlay == 1 && player == 3) return "lose";
        if(iComPlay == 2 && player == 1) return "lose";
        if(iComPlay == 3 && player == 2) return "lose";
        return "";
    }
}