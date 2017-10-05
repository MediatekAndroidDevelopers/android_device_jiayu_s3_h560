package jys3.fastch.dani.jy_s3fastcharge;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.content.*;
import java.io.*;
import android.content.pm.*;
import android.support.annotation.*;

/**
 * Created by Dani on 2219, 02, Nov, 2016.
 */

public class daniio{
   
	public static void writeFile (String file, String content){
	BufferedWriter writer = null;
		try{
				File f = new File(file);
				writer = new BufferedWriter(new FileWriter(f));
				writer.write(content);
		}catch (IOException ioexception){
			ioexception.printStackTrace();
		}finally{
			try{
				writer.close();
			}catch(IOException e){e.printStackTrace();}
		}
	}
	

	
	public static String readLine(String file,int lineno){
			String content = new String();
			try{
				
				FileInputStream fstream = new FileInputStream(file);
				
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				
				while ((strLine = br.readLine()) != null)   {
					
					if (content.length() < 1){
						content = strLine;
					}else{
						content = content + "\n" + strLine;
					}
				}
				
				in.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			String[] lines = content.split("\n");
		return (lines[(lineno - 1)]);
	}
	
	public static String readAllText(String file){
		String content = new String();
		try{
			FileInputStream fstream = new FileInputStream(file);
			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			while ((strLine = br.readLine()) != null)   {
				
				if (content.length() < 1){
					content = strLine;
				}else{
					content = content + "\n" + strLine;
				}
			}
			
			in.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return (content);
	}
	
	public static String[] readAllLines(String file){
		String content = new String();
		String[] lines;
		try{
			FileInputStream fstream = new FileInputStream(file);
			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			while ((strLine = br.readLine()) != null)   {
				
				if (content.length() < 1){
					content = strLine;
				}else{
					content = content + "\n" + strLine;
				}
			}
			
			in.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		lines = content.split("\n");
		return lines;
	}
	
}
