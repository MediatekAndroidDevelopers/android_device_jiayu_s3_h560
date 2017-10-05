package jys3.fastch.dani.jy_s3fastcharge;

import android.content.*;
import android.widget.*;
import java.io.*;

public class BootReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
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
		Toast.makeText(context,"Setting the FastCharge current levels", Toast.LENGTH_LONG).show();
    }
}
