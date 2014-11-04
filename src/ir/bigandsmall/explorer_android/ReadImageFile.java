package ir.bigandsmall.explorer_android;


import java.io.FileInputStream;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ReadImageFile {

	public Drawable ReadImageFromFile(String uri) 
	{
		try 
		{
			return  new BitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(uri)));
		} 
		catch (Exception e) {}
		
		return null;
	}
}
