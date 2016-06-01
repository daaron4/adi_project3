package com.example.john.project3;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.john.project3.setup.DBAssetHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LocalDBHelper helper;
    ListView listView;
    CursorAdapter mCursorAdapter;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();
        //recyclerView = (RecyclerView) findViewById(R.id.main_list_tab_two);
        helper = LocalDBHelper.getInstance(MainActivity.this);
        //Assigning cursor using the key from intent and helper class
        final Cursor cursor = helper.getRatings();

//        arrayList = new ArrayList<>();
//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//        listView.setAdapter(arrayAdapter);

        if(mCursorAdapter == null){
            mCursorAdapter = new CursorAdapter(MainActivity.this, cursor, 0) {
                //Inflating the views
                @Override
                public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                    LayoutInflater layout = LayoutInflater.from(context);
                    return layout.inflate(R.layout.list_frag_format, viewGroup, false);
                }

                @Override
                public void bindView(View view, Context context, Cursor cursor) {
                    TextView rating = (TextView)view.findViewById(R.id.ratings_placeholder);
                    rating.setText(cursor.getString(cursor.getColumnIndex(LocalDBHelper.COL_RATING)));
                    TextView personName = (TextView)view.findViewById(R.id.main_list_name);
                    ImageView personImage = (ImageView)view.findViewById(R.id.main_list_image);
                }
            };
        }


        //Reference the TabLayout in activity_main.xml and set it's three tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("List"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Reference our ViewPager in activity_main.xml
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        //Instantiate our PagerAdapter
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());

        //Set adapter to viewPager
        viewPager.setAdapter(adapter);

        //Set the tabs to call our PagerAdapters getItem method (that's where you need add your fragments)
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
