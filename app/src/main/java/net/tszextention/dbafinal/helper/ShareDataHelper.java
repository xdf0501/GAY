package net.tszextention.dbafinal.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import net.tszextention.utils.commonutils.AppVersionsUtils;
import net.tszextention.utils.commonutils.AppVersionsUtils.AppStruct;
import net.tszextention.utils.commonutils.Base64Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class ShareDataHelper {
	private static Context context;//should use applicationContext
	public static void setApplicationContext( Context applicationContext){
		context=applicationContext;
	}
	private ShareDataHelper(){

	}

	private static Context getContext(){
		if (context==null){
			Log.e(ShareDataHelper.class.getName(), "should call setApplicationContext first");
		}
		return context;
	}
	public static String getDefaultName(){
		String name=null;
		try {
			AppStruct appStruct= AppVersionsUtils.getPackageVersion(getContext());
			name=appStruct.apkPackageName;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (name==null || name.trim().length()==0) {
				name = "global_share";
			}
		}
		return name;
	}
	public static boolean contains(String key){
		return contains(null,key);
	}
	public static boolean contains(String name,String key){
		boolean flag=false;
		if(key!=null){
			if (getContext()!=null) {
				if (name==null || name.trim().length()==0) {
					name= ShareDataHelper.getDefaultName();
				}
				try {
					SharedPreferences sp=getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
					flag=sp.contains(key);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	public static Object getObject(String key) {
		return getObject( null, key);
	}
	public static Object getObject(String name,String key) {
		Object object=null;
		if (getContext()!=null) {
			if (name==null || name.trim().length()==0) {
				name= ShareDataHelper.getDefaultName();
			}
			try {
				SharedPreferences sp=getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
				String value = sp.getString(key, null);
				if(value!=null){
					byte[] base64Bytes = Base64Utils.decode(value);
					ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
					ObjectInputStream ois;
					ois = new ObjectInputStream(bais);
					object=ois.readObject();
				}
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		
		return object;
	}
	public static boolean setObject(String key,Object value) {
		return setObject(null, key, value);
	}
	public static boolean setObject(String name,String key,Object value) {
		boolean flag=false;
		try {
			if (getContext()!=null&&key!=null&&value!=null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(value);
				String objBase64 = new String(Base64Utils.encode(baos.toByteArray()));
				if (name==null || name.trim().length()== 0) {
					name= ShareDataHelper.getDefaultName();
				}
				SharedPreferences sp=getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.putString(key, objBase64);
				flag=editor.commit();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public static boolean remove(String key) {
		return remove(key);
	}
	public static boolean remove(String name,String key) {
		boolean flag=false;
		try {
			if (getContext()!=null&&key!=null) {
				if (name==null || name.trim().length()== 0) {
					name= ShareDataHelper.getDefaultName();
				}
				SharedPreferences sp=getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.remove(key);
				flag=editor.commit();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}
	//////////////// not test
	public static <T> T getObject(String key,Class<T> classOfT) {
		return getObject(null,key,classOfT);
	}
	public static <T> T getObject(String name,String key,Class<T> classOfT) {
		T newt = null;
		try {
			Object obj=getObject(name, key);
			if(obj!=null){
				newt=(T) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return newt;
	}
}
