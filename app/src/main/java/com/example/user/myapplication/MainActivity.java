package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mRadGrp;
    private RadioButton mRadBtnMale;
    private RadioButton mRadBtnFemale;
    private Spinner mSpnAge;
    private CheckBox mChk1,mChk2,mChk3,mChk4,mChk5,mChk6,mChk7,mChk8,mChk9,mChk10,mChk11,mChk12;
    private Button mBtnOK;
    private TextView mTxtSug;
    private TextView mTxtHob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadGrp=(RadioGroup)findViewById(R.id.radGrp);
        mRadBtnMale=(RadioButton)findViewById(R.id.radBtnMale);
        mRadBtnFemale=(RadioButton)findViewById(R.id.radBtnFemale);
        mSpnAge=(Spinner)findViewById(R.id.spnAge);
        mChk1=(CheckBox)findViewById(R.id.chk1);
        mChk2=(CheckBox)findViewById(R.id.chk2);
        mChk3=(CheckBox)findViewById(R.id.chk3);
        mChk4=(CheckBox)findViewById(R.id.chk4);
        mChk5=(CheckBox)findViewById(R.id.chk5);
        mChk6=(CheckBox)findViewById(R.id.chk6);
        mChk7=(CheckBox)findViewById(R.id.chk7);
        mChk8=(CheckBox)findViewById(R.id.chk8);
        mChk9=(CheckBox)findViewById(R.id.chk9);
        mChk10=(CheckBox)findViewById(R.id.chk10);
        mChk11=(CheckBox)findViewById(R.id.chk11);
        mChk12=(CheckBox)findViewById(R.id.chk12);
        mBtnOK=(Button)findViewById(R.id.btnOK);
        mTxtSug=(TextView)findViewById(R.id.txtSug);
        mTxtHob=(TextView)findViewById(R.id.txtHob);

        mBtnOK.setOnClickListener(btnOKOnClick);
    }

    private View.OnClickListener btnOKOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MarriageSuggestion ms=new MarriageSuggestion();
            String ageRange=mSpnAge.getSelectedItem().toString();
            String interest="你的興趣:";
            int sexType=0;
            switch (mRadGrp.getCheckedRadioButtonId()) {
                case R.id.radBtnMale:
                    sexType = 1;
                    break;
                case R.id.radBtnFemale:
                    sexType = 2;
                    break;
            }
            mTxtSug.setText(ms.getMarrySuggestion(sexType, ageRange));

            if(mChk1.isChecked())
                interest+= mChk1.getText().toString()+" ";
            if(mChk2.isChecked())
                interest+= mChk2.getText().toString()+" ";
            if(mChk3.isChecked())
                interest+= mChk3.getText().toString()+" ";
            if(mChk4.isChecked())
                interest+= mChk4.getText().toString()+" ";
            if(mChk5.isChecked())
                interest+= mChk5.getText().toString()+" ";
            if(mChk6.isChecked())
                interest+= mChk6.getText().toString()+" ";
            if(mChk7.isChecked())
                interest+= mChk7.getText().toString()+" ";
            if(mChk8.isChecked())
                interest+= mChk8.getText().toString()+" ";
            if(mChk9.isChecked())
                interest+= mChk9.getText().toString()+" ";
            if(mChk10.isChecked())
                interest+= mChk10.getText().toString()+" ";
            if(mChk11.isChecked())
                interest+= mChk11.getText().toString()+" ";
            if(mChk12.isChecked())
                interest+= mChk12.getText().toString()+" ";
            mTxtHob.setText(interest);
        }
    };
}