package com.example.user.hw7;

/**
 * Created by user on 2018/5/8.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResultActivity extends AppCompatActivity {
    private EditText EdtCountSet,  EdtCountPlayerWin, EdtCountComWin, EdtCountDraw;
    private Button BtnClose;
    private int CountSet = 0,
            CountPlayerWin = 0,
            CountComWin = 0,
            CountDraw = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle =this.getIntent().getExtras();
        CountSet = bundle.getInt("CountSet");
        CountPlayerWin = bundle.getInt("CountPlayerWin");
        CountComWin = bundle.getInt("CountComWin");
        CountDraw = bundle.getInt("CountDraw");

        EdtCountSet = (EditText)findViewById(R.id.edtCountSet);
        EdtCountPlayerWin = (EditText)findViewById(R.id.edtCountPlayerWin);
        EdtCountComWin = (EditText)findViewById(R.id.edtCountComWin);
        EdtCountDraw = (EditText)findViewById(R.id.edtCountDraw);
        BtnClose=(Button)findViewById(R.id.btnClose);

        EdtCountSet.setText(Integer.toString(CountSet));
        EdtCountPlayerWin.setText(Integer.toString(CountPlayerWin));
        EdtCountComWin.setText(Integer.toString(CountComWin));
        EdtCountDraw.setText(Integer.toString(CountDraw));

        BtnClose.setOnClickListener(BtnCloseOnClick);
    }

    private View.OnClickListener BtnCloseOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent();
            it.setClass(ResultActivity.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("CountSet", CountSet);
            bundle.putInt("CountPlayerWin", CountPlayerWin);
            bundle.putInt("CountComWin", CountComWin);
            bundle.putInt("CountDraw", CountDraw);
            it.putExtras(bundle);
            startActivity(it);
            ResultActivity.this.finish();
        }
    };
}
