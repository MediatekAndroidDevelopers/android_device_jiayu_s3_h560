package jys3.fastch.dani.jy_s3fastcharge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.app.*;
import android.os.*;
import java.lang.Process;

/**
 * Created by Dani on 0016, 16, Jul, 2016.
 */
public class OnBootFinish extends Service
{

	@Override
	public IBinder onBind(Intent p1)
	{
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);
		File file = new File("/sdcard/.FCsetonBoot.sh");
        if (file.exists()) {
			try{
                Process p = Runtime.getRuntime().exec("su");
                DataOutputStream outs = new DataOutputStream(p.getOutputStream());
				String cmd = "sh /sdcard/.FCsetonBoot.sh";
				outs.writeBytes(cmd + "\n");
            }catch(Exception e){
                e.printStackTrace();
            }
		}
		Toast.makeText(this,"Setting the FastCharge current levels", Toast.LENGTH_LONG).show();
		this.stopSelf();
	}
	

	
	
	
 
}
