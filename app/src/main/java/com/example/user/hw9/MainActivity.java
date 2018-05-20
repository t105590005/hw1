package com.example.user.hw9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.view.SubMenu;
import android.view.Menu;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.view.ContextMenu;

public class MainActivity extends AppCompatActivity {
    private Spinner spin;
    private EditText date;
    private EditText cash;
    private DatePicker choosedate;
    private Button enter;
    private Button record;
    private ArrayList year, month, day, type, money;
    private String meal;
    private static final int MENU_MUSIC = Menu.FIRST,
            MENU_PLAY_MUSIC = Menu.FIRST + 1,
            MENU_STOP_PLAYING_MUSIC = Menu.FIRST + 2,
            MENU_ABOUT = Menu.FIRST + 3,
            MENU_EXIT = Menu.FIRST + 4;
    private RelativeLayout mRelativeLayout;
    private TextView mTxtView;

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
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        registerForContextMenu(mRelativeLayout);
        mTxtView = (TextView) findViewById(R.id.textView);
        registerForContextMenu(mTxtView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu(0, MENU_MUSIC, 0, "背景音樂")
                .setIcon(android.R.drawable.ic_media_ff);
        subMenu.add(0, MENU_PLAY_MUSIC, 0, "播放背景音樂");
        subMenu.add(0, MENU_STOP_PLAYING_MUSIC, 1, "停止播放背景音樂");
        menu.add(0, MENU_ABOUT, 1, "關於這個程式...")
                .setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0, MENU_EXIT, 2, "結束")
                .setIcon(android.R.drawable.ic_menu_close_clear_cancel);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_PLAY_MUSIC:
                Intent it = new Intent(MainActivity.this, MediaPlayService.class);
                startService(it);
                return true;
            case MENU_STOP_PLAYING_MUSIC:
                it = new Intent(MainActivity.this, MediaPlayService.class);
                stopService(it);
                return true;
            case MENU_ABOUT:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("關於這個程式")
                        .setMessage("選單範例程式")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .show();

                return true;
            case MENU_EXIT:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == mRelativeLayout) {
            if (menu.size() == 0) {
                SubMenu subMenu = menu.addSubMenu(0, MENU_MUSIC, 0, "背景音樂");
                subMenu.add(0, MENU_PLAY_MUSIC, 0, "播放背景音樂");
                subMenu.add(0, MENU_STOP_PLAYING_MUSIC, 1, "停止播放背景音樂");
                menu.add(0, MENU_ABOUT, 1, "關於這個程式...");
                menu.add(0, MENU_EXIT, 2, "結束");
            }
        }
        else if (v == mTxtView) {
            menu.add(0, MENU_ABOUT, 1, "關於這個程式...");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        onOptionsItemSelected(item);
        return super.onContextItemSelected(item);
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