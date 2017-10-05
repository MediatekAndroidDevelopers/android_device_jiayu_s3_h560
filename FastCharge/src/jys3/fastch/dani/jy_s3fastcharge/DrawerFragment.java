package jys3.fastch.dani.jy_s3fastcharge;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import android.view.View.*;
import android.support.v4.widget.*;
import android.content.*;

public class DrawerFragment extends Fragment
{
	String ac = new String();
	String usb = new String();
	EditText etChaB;
	EditText etUSBB;
	DrawerLayout layout_main;
	Context context;
	
	public void DrawerFragment(){}
	
	public static DrawerFragment newInstance(){
		return new DrawerFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		context = getActivity();
		
		final View v = inflater.inflate(R.layout.frg_drawer,container,false);
		
		layout_main = (DrawerLayout)v.findViewById(R.id.activity_main);
		
		CheckBox toggle = (CheckBox)v.findViewById(R.id.cbBootToggle);
		etChaB = (EditText)v.findViewById(R.id.etChaB);
        etUSBB = (EditText)v.findViewById(R.id.etUSBB);

        File file = new File("/sdcard/.FCsetonBoot.sh");
        if (file.exists()) {
            toggle.setChecked(true);

			etChaB.setText(getValues(file.toString(),2));
			etUSBB.setText(getValues(file.toString(),3));

        }
        if (!file.exists()) {
            toggle.setChecked(false);
        }
		
		Button btnSave = (Button)v.findViewById(R.id.btnSaveSettings);
		btnSave.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					String ac = new String();
					String usb = new String();

					CheckBox toggle = (CheckBox)v.findViewById(R.id.cbBootToggle);


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
							Toast.makeText(context,"Saved",0).show();
							layout_main.closeDrawers();
						}else{Toast.makeText(context,"File could not be saved",0).show();}
					}
					if (!toggle.isChecked()){
						File f = new File("/sdcard/.FCsetonBoot.sh");
						f.delete();
						if (f.exists() == false){
							Toast.makeText(context,"Deactivated",0).show();
							layout_main.closeDrawers();
						}
					}
				}
				
			
		});
		
	return v;}
	
	
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
}
