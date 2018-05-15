package com.example.user.hw8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner spin;
    private EditText date;
    private EditText cash;
    private DatePicker choosedate;
    private Button enter;
    private Button record;
    private ArrayList year, month, day, type, money;
    private String meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        year=new ArrayList();
        month=new ArrayList();
        day=new ArrayList();
        type=new ArrayList();
        money=new ArrayList();

        spin=(Spinner)findViewById(R.id.SpnMeal);
        date=(EditText)findViewById(R.id.edtDate);
        cash=(EditText)findViewById(R.id.edtMoney);
        choosedate=(DatePicker)findViewById(R.id.datePicker);
        enter=(Button)findViewById(R.id.btnEnter);
        record=(Button)findViewById(R.id.btnRecord);

        spin.setOnItemSelectedListener(spnTypeOnItemSelect);
        choosedate.init(2000,1,1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                date.setText(Integer.toString(year) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(dayOfMonth));
            }
        });
        enter.setOnClickListener(btnInputOnClick);
        record.setOnClickListener(btnRecordOnClick);
    }

    private AdapterView.OnItemSelectedListener spnTypeOnItemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            meal=adapterView.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private Button.OnClickListener btnInputOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            year.add(Integer.toString(choosedate.getYear()));
            month.add(Integer.toString(choosedate.getMonth()+1));
            day.add(Integer.toString(choosedate.getDayOfMonth()));
            type.add(meal);
            money.add(cash.getText().toString());

            Toast t=Toast.makeText(MainActivity.this, cash.getText().toString(), Toast.LENGTH_LONG);
            t.show();
        }
    };

    private Button.OnClickListener btnRecordOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent();
            it.setClass(MainActivity.this,Record.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("year", year);
            bundle.putStringArrayList("month", month);
            bundle.putStringArrayList("day", day);
            bundle.putStringArrayList("type", type);
            bundle.putStringArrayList("money", money);
            it.putExtras(bundle);
            startActivity(it);
        }
    };
}