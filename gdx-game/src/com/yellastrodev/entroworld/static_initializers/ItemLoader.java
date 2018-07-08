package com.yellastrodev.entroworld.static_initializers;
import android.util.*;
import com.badlogic.gdx.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;
import org.json.*;
import android.os.*;
import android.app.*;
import android.content.*;
import com.yellastrodev.entroworld.game_core.items.*;

public class ItemLoader
{
	private final static String lTAG = "ItemLoader_trase";
	
	private static JSONArray sJsonItems;
	
	private final static String sFileNameItems = "items.json";
	
	private static String kItName="name",kItFile="file",kItType="type",kItArm="armor",
	kItAtc="Atack",kItSet="Set";

	
	public static List<Equp> getEqupList()
	{
		if(sJsonItems==null)
			sJsonItems=GetJsonFromFile(sFileNameItems);
			
		List<Equp> fList = new ArrayList<>();
			
		int l = sJsonItems.length();
		for(int i=0;i<l;i++)
		{
			try
			{
				JSONObject qJsonItem = sJsonItems.getJSONObject(i);
			
				Equp qEqup = new Equp(
					qJsonItem.getString(kItFile),
					qJsonItem.getString(kItType),
					new EqupStatistic(
						qJsonItem.getString(kItArm),
						qJsonItem.getString(kItAtc)),
					qJsonItem.getString(kItFile),
					qJsonItem.getString(kItSet));
				fList.add(qEqup);
			}
			catch (JSONException e)
			{Log.e(lTAG,"getEqupList()index = "+i,e);}
		}
		return fList;
	}
	
	public static void createItem(String fFileName,
		String fName, String fType,String fArmor,String fDmg,String fSet)
	{
		JSONObject fJsItem = new JSONObject();
		
		if(sJsonItems==null)
			sJsonItems=GetJsonFromFile(sFileNameItems);
		
		try
		{
			fJsItem.put(kItName, fName);
			fJsItem.put(kItFile, fFileName);
			fJsItem.put(kItType, fType);
			fJsItem.put(kItArm, fArmor);
			fJsItem.put(kItAtc, fDmg);
			fJsItem.put(kItSet, fSet);
			sJsonItems.put(fJsItem);
			WriteFile(sJsonItems,sFileNameItems);
		}
		catch (JSONException e)
		{}

	}
	
	public static JSONArray GetJsonFromFile (String filename)
	{
		String result="";


		JSONArray jsonObj=null;
		try {
            File yourFile = new File(Environment.getExternalStorageDirectory(), filename);
            FileInputStream stream = new FileInputStream(yourFile);
            String jsonStr = null;
            try {
                FileChannel fc = stream.getChannel();
                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

                jsonStr = Charset.defaultCharset().decode(bb).toString();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
                stream.close();
			}
			     
			jsonObj = new JSONArray(jsonStr);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new JSONArray();
		}
		return jsonObj;
	}
	
	public static void WriteFile(JSONArray jsonObject,String filename)
	{
		try {
			Writer output = null;
			File file = new File(Environment.getExternalStorageDirectory(), filename);
			output = new BufferedWriter(new FileWriter(file));
			output.write(jsonObject.toString());
			output.close();

			//Toast.makeText(getApplicationContext(), "Composition saved", Toast.LENGTH_LONG).show();

		} catch (Exception e) {
			Log.e(lTAG,"WriteJsonError",e);
			//e.printStackTrace();
			File file = new File(Environment.getExternalStorageDirectory(), filename);
			try
			{
				file.createNewFile();
			}
			catch (IOException ee)
			{
				Log.e(lTAG,"WriteJsonError",e);
			}
		}
	
	}
	/*
	public static File getIntDir()
	{
		return android.content.Context.getFilesDir();
	}*/
}
