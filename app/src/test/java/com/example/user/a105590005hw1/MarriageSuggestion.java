package com.example.user.a105590005hw1;

/**
 * Created by user on 2018/3/25.
 */
/**
 * Created by user on 2018/3/25.
 */
public class MarriageSuggestion {
    public String getSuggestion(String strSex,int age,int family){
        String strSug = "建議：";
        if(strSex.equals("male"))
            if (age < 30)
                if (family < 4)
                    strSug += "趕快結婚";
                else if (family >= 4 && family <= 10)
                    strSug += "趕快結婚";
                else
                    strSug += "還不急";
            else if (age >= 30 && age <= 40)
                if (family < 4)
                    strSug += "趕快結婚";
                else if (family >= 4 && family <= 10)
                    strSug += "開始找對象";
                else
                    strSug += "還不急";
            else
            if (family < 4)
                strSug += "開始找對象";
            else if (family >= 4 && family <= 10)
                strSug += "趕快結婚";
            else
                strSug += "開始找對象";
        else
        if (age < 28)
            if (family < 4)
                strSug += "趕快結婚";
            else if (family >= 4 && family <= 10)
                strSug += "趕快結婚";
            else
                strSug += "還不急";
        else if (age >= 28 && age <= 35)
            if (family < 4)
                strSug += "趕快結婚";
            else if (family >= 4 && family <= 10)
                strSug += "開始找對象";
            else
                strSug += "還不急";
        else
        if (family < 4)
            strSug += "開始找對象";
        else if (family >= 4 && family <= 10)
            strSug += "趕快結婚";
        else
            strSug += "開始找對象";
        return  strSug;
    }
}
