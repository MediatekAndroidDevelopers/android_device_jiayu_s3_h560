package jys3.fastch.dani.jy_s3fastcharge;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.content.Intent;
import android.os.Bundle;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.lang.Process;

/**
 * Created by Dani on 0019, 19, Jul, 2016.
 */
public class set_on_boot_settings extends Activity {
	String ac = new String();
	String usb = new String();
	EditText etChaB;
	EditText etUSBB;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_on_boot_settings);
        CheckBox toggle = (CheckBox)findViewById(R.id.cbBootToggle);
		etChaB = (EditText)findViewById(R.id.etChaB);
        etUSBB = (EditText)findViewById(R.id.etUSBB);
    
        File file = new File("/sdcard/.FCsetonBoot.sh");
        if (file.exists()) {
            toggle.setChecked(true);
			
			etChaB.setText(getValues(file.toString(),2));
			etUSBB.setText(getValues(file.toString(),3));
			
        }
        if (!file.exists()) {
            toggle.setChecked(false);
        }
    }
	
	private String getValues(String filepath, int lineindex){
		String[] filter = daniio.readAllLines(filepath);
		String tmp = new String(filter[lineindex]);
		String s = "";
		String digits ="0123456789";
		for (int x=5; x<=8 ; x++){
			if (digits.contains(String.valueOf(tmp.charAt(x)))){
				if (s.length() == 0){
					s = String.valueOf(tmp.charAt(x));
				}else{s = s + String.valueOf(tmp.charAt(x));}
			}
		}
	return s;}


    public void onbtnSaveClick (View view) {
		String ac = new String();
		String usb = new String();
		
        CheckBox toggle = (CheckBox)findViewById(R.id.cbBootToggle);
        
		
        ac = etChaB.getText().toString();
        usb = etUSBB.getText().toString();
        if (toggle.isChecked()){
			File f = new File("/sdcard/.FCsetonBoot.sh");
			if (f.exists()){
				f.delete();
			}
			String nl = "\n";
			String script = new String();
			script = "#!/system/bin/sh" + nl + "echo 1 > /sys/kernel/charge_levels/quick_charge_enable" + nl +"echo " + ac + " > /sys/kernel/charge_levels/charge_level_ac" + nl + "echo " + usb + " > /sys/kernel/charge_levels/charge_level_usb";
			daniio.writeFile("/sdcard/.FCsetonBoot.sh",script);
			
			File file = new File("/sdcard/.FCsetonBoot.sh");
			if (file.exists() == true){
				Toast.makeText(this,"Saved",0).show();
				this.finish();
			}else{Toast.makeText(this,"File could not be saved",0).show();}
        }
        if (!toggle.isChecked()){
            File f = new File("/sdcard/.FCsetonBoot.sh");
			f.delete();
			if (f.exists() == false){
				Toast.makeText(this,"Deactivated",0).show();
				this.finish();
			}
        }

    }
}
