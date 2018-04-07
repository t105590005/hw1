package com.example.user.myapplication;

/**
 * Created by user on 2018/4/8.
 */

public class MarriageSuggestion {
    public String getMarrySuggestion(int sex, String iAgeRange){
        String sug="建議:";
        if(sex==1){
            switch(iAgeRange) {
                case "(男生)小於30歲,(女生)小於28歲":
                    sug += "還不急";
                    break;
                case "(男生)30到40歲,(女生)28到35歲":
                    sug += "開始找對象";
                    break;
                case "(男生)大於40歲,(女生)大於35歲":
                    sug += "趕快結婚";
                    break;
            }
        }
        else if(sex==2){
            switch(iAgeRange) {
                case "(男生)小於30歲,(女生)小於28歲":
                    sug += "還不急";
                    break;
                case "(男生)30到40歲,(女生)28到35歲":
                    sug += "開始找對象";
                    break;
                case "(男生)大於40歲,(女生)大於35歲":
                    sug += "趕快結婚";
                    break;
            }
        }
        return sug;
    }
}