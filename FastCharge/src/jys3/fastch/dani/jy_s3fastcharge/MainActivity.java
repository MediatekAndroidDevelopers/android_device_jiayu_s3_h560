package jys3.fastch.dani.jy_s3fastcharge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.lang.Process;
import android.content.*;
import android.support.v4.widget.*;

public class MainActivity extends AppCompatActivity {

	public static Context context;
	
	android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		context = this;
        getSupportFragmentManager().beginTransaction().replace(R.id.flDrawer,DrawerFragment.newInstance()).addToBackStack(null).commit();
		getSupportFragmentManager().beginTransaction().replace(R.id.flMain,MainFragment.newInstance()).addToBackStack(null).commit();
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		final DrawerLayout layout_main = (DrawerLayout)findViewById(R.id.activity_main);

		mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,layout_main, R.string.abc_action_bar_home_description,R.string.abc_action_bar_home_subtitle_description_format){
			public void onDrawerClosed(View view){
				getSupportActionBar().setTitle("JY-S3 Fast Charge");
				supportInvalidateOptionsMenu();
			}

			public void onDrawerOpened(View view){
				getSupportActionBar().setTitle("No init.d?");
				supportInvalidateOptionsMenu();
			}


		};
		mDrawerToggle.setDrawerIndicatorEnabled(true);
		layout_main.setDrawerListener(mDrawerToggle);
    }

	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	

}
