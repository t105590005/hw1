package com.example.user.hw9;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 2018/5/20.
 */

public class Record extends AppCompatActivity {
    private ArrayList year, month, day, type, money, record=new ArrayList();
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);

        Bundle bundle =this.getIntent().getExtras();
        year= bundle.getStringArrayList("year");
        month= bundle.getStringArrayList("month");
        day= bundle.getStringArrayList("day");
        type= bundle.getStringArrayList("type");
        money= bundle.getStringArrayList("money");

        list = (ListView) findViewById(R.id.listView);

        for(int i=0;i<year.size();i++)
            record.add("項目" + Integer.toString(i) + "   " + year.get(i) + "/" + month.get(i) + "/" + day.get(i) + "   " + type.get(i) + "   " + money.get(i));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, record );
        list.setAdapter(arrayAdapter);
    }
}