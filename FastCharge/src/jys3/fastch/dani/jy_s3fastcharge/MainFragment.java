package jys3.fastch.dani.jy_s3fastcharge;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;
import java.io.*;

import java.lang.Process;
import android.content.*;
import android.view.View.*;
import android.support.v4.widget.*;

public class MainFragment extends Fragment
{
	public void MainFragment(){}
	
	public static MainFragment newInstance(){
		return new MainFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		final View v = inflater.inflate(R.layout.frg_main,container,false);
		final String sysrw = "mount -o rw,remount,rw /system";
		
		Button Initd = (Button)v.findViewById(R.id.btnInitd);
        TextView instat = (TextView)v.findViewById(R.id.tvInitd);
        File inscript = new File("/system/etc/init.d/01fastcharge");
        TextView status = (TextView) v.findViewById(R.id.txvStatus);
        EditText ac = (EditText)v.findViewById(R.id.etCha);
        EditText usb = (EditText)v.findViewById(R.id.etUSB);
        try{
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream outs = new DataOutputStream(p.getOutputStream());
            outs.writeBytes(sysrw + "\n");
        }catch(Exception e){
            e.printStackTrace();
        }
        File file = new File("/sys/kernel/charge_levels/quick_charge_enable");
        if (inscript.exists()){instat.setText("init.d script found :D");}
        if (!inscript.exists()){
			File infol = new File("/etc/init.d");
			if (infol.exists()){if (infol.isDirectory()){
					if (!new File("/system/etc/init.d/01fastcharge").exists()){
						instat.setText("init.d script not found, press the button below to add");
						Initd.setVisibility(View.VISIBLE);
					}else{
						instat.setText("init.d support not found :(");
					}
				}}


        }
        if (file.exists()) {
            try {
                FileInputStream fstream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine = br.readLine();
                if(strLine.equals("0")){
                    status.setText("Fast Charge available, but disabled");
                }
                if(strLine.equals("1")){
                    status.setText("Fast Charge available, and enabled");
                }
                Button btnToggle = (Button)v.findViewById(R.id.btnToggle);
                btnToggle.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!file.exists()) {
            status.setText("Fast Charge unavailable, your kernel probably doesnt support it");
            Button btnToggle = (Button)v.findViewById(R.id.btnToggle);
            btnToggle.setVisibility(View.INVISIBLE);
        }
        file = new File("/sys/kernel/charge_levels/charge_level_ac");
        if(file.exists()){
            try {
                FileInputStream fstream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine = br.readLine();
                ac.setText(strLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!file.exists()) {
            Toast.makeText((Context)getActivity(),"ERR: AC Charge Level not found",Toast.LENGTH_SHORT).show();
        }

        file = new File("/sys/kernel/charge_levels/charge_level_usb");
        if(file.exists()){
            try {
                FileInputStream fstream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine = br.readLine();
                usb.setText(strLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!file.exists()) {
            Toast.makeText((Context)getActivity(),"ERR: USB Charge Level not found",Toast.LENGTH_SHORT).show();
        }
		
		final Button btnCheck = (Button)v.findViewById(R.id.btnCheck);
		btnCheck.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					TextView status = (TextView) v.findViewById(R.id.txvStatus);
					EditText ac = (EditText)v.findViewById(R.id.etCha);
					EditText usb = (EditText)v.findViewById(R.id.etUSB);
					File file = new File("/sys/kernel/charge_levels/quick_charge_enable");
					if (file.exists()) {
						try {
							FileInputStream fstream = new FileInputStream(file);
							BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
							String strLine = br.readLine();
							if(strLine.equals("0")){
								status.setText("Fast Charge available, but disabled");
							}
							if(strLine.equals("1")){
								status.setText("Fast Charge available, and enabled");
							}
							Button btnToggle = (Button)v.findViewById(R.id.btnToggle);
							btnToggle.setVisibility(View.VISIBLE);

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (!file.exists()) {
						status.setText("Fast Charge unavailable, your kernel probably doesnt support it");
						Button btnToggle = (Button)v.findViewById(R.id.btnToggle);
						btnToggle.setVisibility(View.INVISIBLE);
					}
					file = new File("/sys/kernel/charge_levels/charge_level_ac");
					if (file.exists()){
						try {
							FileInputStream fstream = new FileInputStream(file);
							BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
							String strLine = br.readLine();
							ac.setText(strLine);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					file = new File("/sys/kernel/charge_levels/charge_level_usb");
					if (file.exists()){
						try {
							FileInputStream fstream = new FileInputStream(file);
							BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
							String strLine = br.readLine();
							usb.setText(strLine);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		});
		
		final Button btnToggle = (Button)v.findViewById(R.id.btnToggle);
		btnToggle.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					TextView status = (TextView) v.findViewById(R.id.txvStatus);
					String fcstatus = status.getText().toString();
					if(fcstatus.equals("Fast Charge available, but disabled")){
						try{
							Process p = Runtime.getRuntime().exec("su");
							DataOutputStream outs = new DataOutputStream(p.getOutputStream());
							String cmd = "rm /sys/kernel/charge_levels/quick_charge_enable";
							outs.writeBytes(cmd + "\n");
							cmd = "echo 1 > /sys/kernel/charge_levels/quick_charge_enable";
							outs.writeBytes(cmd + "\n");
							status.setText("Fast Charge available, and enabled");
						}catch(Exception e){
							e.printStackTrace();
						}
					}

					if(fcstatus.equals("Fast Charge available, and enabled")){
						try{
							Process p = Runtime.getRuntime().exec("su");
							DataOutputStream outs = new DataOutputStream(p.getOutputStream());
							String cmd = "rm /sys/kernel/charge_levels/quick_charge_enable";
							outs.writeBytes(cmd + "\n");
							cmd = "echo 0 > /sys/kernel/charge_levels/quick_charge_enable";
							outs.writeBytes(cmd + "\n");
							status.setText("Fast Charge available, but disabled");
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
		});
		
		final Button btnSet = (Button)v.findViewById(R.id.btnSet);
		btnSet.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					EditText ac = (EditText)v.findViewById(R.id.etCha);
					EditText usb = (EditText)v.findViewById(R.id.etUSB);
					String accurrent = new String(ac.getText().toString());
					String usbcurrent = new String(usb.getText().toString());
					try{
						Process p = Runtime.getRuntime().exec("su");
						DataOutputStream outs = new DataOutputStream(p.getOutputStream());
						outs.writeBytes(sysrw + "\n");
					}catch(Exception e){
						e.printStackTrace();
					}
					try{
						Process p = Runtime.getRuntime().exec("su");
						DataOutputStream outs = new DataOutputStream(p.getOutputStream());
						String cmd = "rm /sys/kernel/charge_levels/charge_level_ac";
						outs.writeBytes(cmd + "\n");
						cmd = "echo " + accurrent + " > /sys/kernel/charge_levels/charge_level_ac";
						outs.writeBytes(cmd + "\n");
					}catch(Exception e){
						e.printStackTrace();
					}
					try{
						Process p = Runtime.getRuntime().exec("su");
						DataOutputStream outs = new DataOutputStream(p.getOutputStream());
						String cmd = "rm /sys/kernel/charge_levels/charge_level_usb";
						outs.writeBytes(cmd + "\n");
						cmd = "echo " + usbcurrent + " > /sys/kernel/charge_levels/charge_level_usb";
						outs.writeBytes(cmd + "\n");
					}catch(Exception e){
						e.printStackTrace();
					}
					String initdscript = "echo -e " + "\"" + "#!/system/bin/sh\n#call userinit.sh if present in /data/local\necho " + accurrent + " > /sys/kernel/charge_levels/charge_level_ac\necho " + usbcurrent + " > /sys/kernel/charge_levels/charge_level_usb" + "\" >> /system/etc/init.d/01fastcharge";
					try{
						Process p = Runtime.getRuntime().exec("su");
						DataOutputStream outs = new DataOutputStream(p.getOutputStream());
						outs.writeBytes(sysrw + "\n");
					}catch(Exception e){
						e.printStackTrace();
					}
					try{
						Process p = Runtime.getRuntime().exec("su");
						DataOutputStream outs = new DataOutputStream(p.getOutputStream());
						outs.writeBytes("rm /system/etc/init.d/01fastcharge" + "\n");
					}catch(Exception e){
						e.printStackTrace();
					}
					try{
						Process p = Runtime.getRuntime().exec("su");
						DataOutputStream outs = new DataOutputStream(p.getOutputStream());
						String cmd = initdscript;
						outs.writeBytes(cmd + "\n");
						cmd = "chmod 755 /system/etc/init.d/01fastcharge";
						outs.writeBytes(cmd + "\n");
					}catch(Exception e){
						e.printStackTrace();
					}
				}
		});
		
		final Button btnInidtd = (Button)v.findViewById(R.id.btnInitd);
		btnInidtd.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					TextView instat = (TextView)v.findViewById(R.id.tvInitd);
					File f = new File("/sdcard/01fastcharge");
					if (f.exists()){
						f.delete();
					}
					try {
						FileWriter f0 = new FileWriter(f);
						String ac = new String("1700");
						String usb = new String("1000");

						String newLine = System.getProperty("line.separator");
						f0.write("#!/system/bin/sh" + newLine);
						f0.write("echo 1 > /sys/kernel/charge_levels/quick_charge_enable" + newLine);
						f0.write("echo " + ac + " > /sys/kernel/charge_levels/charge_level_ac" + newLine);
						f0.write("echo " + usb + " > /sys/kernel/charge_levels/charge_level_usb");
						f0.close();
					}catch (IOException e) {
						e.printStackTrace();
					}
					try{
						Process p = Runtime.getRuntime().exec("su");
						DataOutputStream outs = new DataOutputStream(p.getOutputStream());
						String cmd = "mv /sdcard/01fastcharge /system/etc/init.d/01fastcharge";
						outs.writeBytes(cmd + "\n");
						cmd = "chmod 755 /system/etc/init.d/01fastcharge";
						outs.writeBytes(cmd + "\n");
					}catch(Exception e){
						e.printStackTrace();
					}
					instat.setText("init.d script has been written, it is now recommended to reboot");
				}
		});
		
		
	
		
	return v;}
	
}
