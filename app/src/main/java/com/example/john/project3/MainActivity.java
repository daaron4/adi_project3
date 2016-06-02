package com.example.john.project3;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.john.project3.setup.DBAssetHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements ApiConnector.ApiResponseHandler {

    LocalDBHelper helper;
    ListView listView;
    CursorAdapter mCursorAdapter;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ApiConnector.getInstance(MainActivity.this).doRequest();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();
        //recyclerView = (RecyclerView) findViewById(R.id.main_list_tab_two);
        helper = LocalDBHelper.getInstance(MainActivity.this);
        //Assigning cursor using the key from intent and helper class

//
//        if(mCursorAdapter == null){
//            mCursorAdapter = new CursorAdapter(MainActivity.this, cursor, 0) {
//                //Inflating the views
//                @Override
//                public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
//                    LayoutInflater layout = LayoutInflater.from(context);
//                    return layout.inflate(R.layout.list_frag_format, viewGroup, false);
//                }
//
//                @Override
//                public void bindView(View view, Context context, Cursor cursor) {
//                    TextView rating = (TextView)view.findViewById(R.id.ratings_placeholder);
//                    rating.setText(cursor.getString(cursor.getColumnIndex(LocalDBHelper.COL_RATING)));
//                    TextView personName = (TextView)view.findViewById(R.id.main_list_name);
//                    ImageView personImage = (ImageView)view.findViewById(R.id.main_list_image);
//                }
//            };
//        }


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
                Toast.makeText(MainActivity.this, Storage.gaArrayList.toString(), Toast.LENGTH_SHORT).show();

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

    @Override
    public void handleResponseName(String[] idArray, String[] nameArray, String[] titleArray, String[] skillsArray, String[] openArray, String[] gitHubArray, String[] gaArray, String[] linkedInArray, String[] otherArray, String[] imageArray, String[] urlArray) {

        Storage.idArrayList = new ArrayList<>(Arrays.asList(idArray));
        Storage.nameArrayList = new ArrayList<>(Arrays.asList(nameArray));
        Storage.titleArrayList = new ArrayList<>(Arrays.asList(titleArray));
        Storage.skillsArrayList = new ArrayList<>(Arrays.asList(skillsArray));
        Storage.openArrayList = new ArrayList<>(Arrays.asList(openArray));
        Storage.gitHubArrayList = new ArrayList<>(Arrays.asList(gitHubArray));
        Storage.gaArrayList = new ArrayList<>(Arrays.asList(gaArray));
        Storage.linkedInArrayList = new ArrayList<>(Arrays.asList(linkedInArray));
        Storage.otherArrayList = new ArrayList<>(Arrays.asList(otherArray));
        Storage.imageArrayList = new ArrayList<>(Arrays.asList(imageArray));
        Storage.urlArrayList = new ArrayList<>(Arrays.asList(urlArray));

        Toast.makeText(MainActivity.this, Storage.nameArrayList.toString(), Toast.LENGTH_SHORT).show();
        helper.updateData(Storage.nameArrayList,
                Storage.titleArrayList,
                Storage.skillsArrayList,
                Storage.openArrayList,
                Storage.gitHubArrayList,
                Storage.gaArrayList,
                Storage.linkedInArrayList,
                Storage.otherArrayList,
                Storage.imageArrayList,
                Storage.urlArrayList);
        final Cursor cursor = helper.getDescriptionById(1);
        Log.e("Tag", cursor.toString());
    }

}
