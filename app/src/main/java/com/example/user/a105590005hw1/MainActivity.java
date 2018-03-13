package com.example.user.a105590005hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import java.text.NumberFormat;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText sex;
    EditText age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        age=(EditText)findViewById(R.id.editText3);
        sex=(EditText)findViewById(R.id.editText);
        final TextView out = (TextView)findViewById(R.id.textView3);
        Button submit = (Button)findViewById(R.id.button);
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {

                if ( !("".equals(age.getText().toString()) || "".equals(sex.getText().toString())) )
                {
                    float fAge = Float.parseFloat(age.getEditableText().toString());
                    if("male".equals(sex.getEditableText().toString()))
                    {
                        if(fAge<30){
                            out.setText("建議:not hurry");
                        }
                        else if(fAge>35){
                            out.setText("建議:find couple");
                        }
                        else{
                            out.setText("建議:get marry");
                        }
                    }
                    else if("female".equals(sex.getEditableText().toString()))
                    {
                        if(fAge<28){
                            out.setText("建議:not hurry");
                        }
                        else if(fAge>32){
                            out.setText("建議:find couple");
                        }
                        else{
                           out.setText("建議:get marry");
                        }
                    }
                    else{
                        out.setText("輸入錯誤");
                    }
                }
                else {
                    out.setText("尚未輸入資料");
                }
            }
        }
        );
    }
}
