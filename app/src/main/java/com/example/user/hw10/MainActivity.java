package com.example.user.hw10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Database settings
    public static final String DB_FILE = "contact.db", DB_TABLE = "contact";
    public static SQLiteDatabase sdbContact;

    // Fragments
    private AddNewContact addNewContact;
    private SearchContact searchContact;

    // Pagers
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager());

        // 設定 ViewPager 和 Pager Adapter。
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // 設定 ViewPager 給 TabLayout，就會顯示 tab pages。
        TabLayout tabLayout = findViewById(R.id.tblTabLine);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Close keyboard when user click TabLayout
                InputMethodManager imm = ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE));
                imm.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        // Initial Fragments
        addNewContact = new AddNewContact();
        searchContact = new SearchContact();

        // Setting up Database
        ContactDBOpenHelper contactDBOpenHelper = new ContactDBOpenHelper(getApplicationContext(), DB_FILE, null, 1);
        sdbContact = contactDBOpenHelper.getWritableDatabase();

        Cursor cursor = sdbContact.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + DB_TABLE + "'"
                , null);

        if(cursor != null) {
            if(cursor.getCount() == 0)	// DB Table not exist, therefore to construct a new one.
                sdbContact.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                        "_id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "phoneNumber TEXT," +
                        "phoneType TEXT);");

            cursor.close();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.itemSearch).getActionView();
        searchView.setOnQueryTextListener(searchView_OnQuery);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemAddContact) {
            ContentValues data = addNewContact.getContentValues();
            sdbContact.insert(DB_TABLE, null, data);
            searchContact.addDataToList(
                    "名字: " + data.getAsString("name") +
                    ",號碼: " + data.getAsString("phoneNumber") +
                    ",種類: " + data.getAsString("phoneType"));

            Toast.makeText(this, "資料已成功加入至資料庫中！", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sdbContact.close();
    }

    // On user searching data
    private final SearchView.OnQueryTextListener searchView_OnQuery = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Cursor cursor = null;
            if (!query.equals("")) {
                cursor = sdbContact.query(true,
                        DB_TABLE,
                        new String[]{"name", "phoneNumber", "phoneType"},
                        "name=" + "\"" + query + "\"",
                        null, null, null, null, null);
            }

            if (cursor == null)
                return false;

            // If couldn't find data, then show the message
            if (cursor.getCount() == 0) {
                Toast.makeText(MainActivity.this, "找不到目標資料！", Toast.LENGTH_LONG).show();
                searchContact.setListHighlight();
            }
            else {
                ArrayList<String> dataList = new ArrayList<>();
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    dataList.add(
                            "名字: " + cursor.getString(0) +
                            ",號碼: " + cursor.getString(1) +
                            ",種類: " + cursor.getString(2));
                    cursor.moveToNext();
                }
                searchContact.setListHighlight(dataList);
            }

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) { return false; }
    };

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            // 根據目前tab標籤頁的位置，傳回對應的fragment物件
            switch (position) {
                case 0:
                    fragment = addNewContact;
                    break;
                case 1:
                    fragment = searchContact;
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "新增聯絡人";
                case 1:
                    return "搜尋聯絡人";
                default:
                    return null;
            }
        }
    }
}
