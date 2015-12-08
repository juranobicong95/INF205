package com.edu.fpt.demoparse;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.List;

import Adapter.ThongKe;

public class MainActivity extends ActionBarActivity {
    private ViewPager mViewPager;
    Button clickThongKe, clickThuChi, clickCaiDat, clickGioiThieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        //Anh xa
        clickGioiThieu = (Button)findViewById(R.id.buttonGioiThieu);
        clickThuChi = (Button)findViewById(R.id.buttonThuChi);
        clickCaiDat = (Button)findViewById(R.id.buttonCaiDat);
        clickThongKe = (Button)findViewById(R.id.buttonThongKe);

        //Lang nghe su kien clickGioiThieu
        clickGioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGioiThieu = new Intent(MainActivity.this, infoActivity.class);
                startActivity(intentGioiThieu);
            }
        });

        //lắng nghe sự kiện clickThuChi
        clickThuChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThuChi = new Intent(MainActivity.this, ThuChiActivity.class);
                startActivity(intentThuChi);
            }
        });

        //lắng nghe sự kiện clickThongKe
        clickGioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThongKe = new Intent(MainActivity.this, ThongKeActivity.class);
                startActivity(intentThongKe);
            }
        });

        //lắng nghe sự kiện clickCaiDat
        clickCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCaiDat = new Intent(MainActivity.this, CaiDatActivity.class);
                startActivity(intentCaiDat);
            }
        });

        //lắng nghe sự kiện clickThongKe
        clickThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThongKe = new Intent(MainActivity.this, ThongKeActivity.class);
                startActivity(intentThongKe);
            }
        });
        //parse
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "OFrUn9KQhhPe0zlOQ77AMCxKOLiZfyQfNP5dBQdb", "bPoL3RX7vCxZXWZfy2D3GO4eaqtnq3zHv3sAMyHO");
        //Do du lieu vao app
        ParseQuery<ParseObject> query = ParseQuery.getQuery("KhoanTC");
       // query.whereEqualTo("playerName", "Dan Stemkoski");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> scoreList, com.parse.ParseException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this, scoreList.size()+"", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
