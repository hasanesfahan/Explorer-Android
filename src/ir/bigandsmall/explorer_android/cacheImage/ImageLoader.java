package ir.bigandsmall.explorer_android.cacheImage;

import ir.bigandsmall.explorer_android.adapter.FileArrayAdapter;
import ir.bigandsmall.explorer_android.displayImage.displayImage;

import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ImageLoader {
	private HashMap<String, Drawable> mCacheMap ;
	
	private Activity activity;
	private FileArrayAdapter fadpater;
	
	public ImageLoader(Activity activity,FileArrayAdapter fadpater) 
	{
		this.activity = activity;
		this.fadpater = fadpater;
		
		
		if(mCacheMap == null)
			mCacheMap = new HashMap<String, Drawable>();
	}
	
	
	public Drawable isBitmapCached(String name) {
		return mCacheMap.get(name);
	}
	
	private BitmapDrawable getImage(String STRING_PATH_TO_FILE)
	{
		 return new BitmapDrawable(activity.getResources(),displayImage.decodeFile(STRING_PATH_TO_FILE,20,20));
	}
	
	public void newImageLoad(String Path,ImageView tvImage)
	{
		if(mCacheMap.containsKey(Path))
		{
			if(isBitmapCached(Path) != null)
				tvImage.setBackgroundDrawable(isBitmapCached(Path));
		}
		else if(isBitmapCached(Path) == null)
		{
			loadThread(Path, tvImage);
		}
		
	}
	private void loadThread(final String Path,final ImageView tvImage)
	{

		Thread pics_thread = new Thread(new Runnable()
		{
			@Override
	        public void run() 
			{
	
		        final Drawable bitmap = getImage(Path);
		
		        if(bitmap!=null)
		        {
		        	activity.runOnUiThread(new Runnable(){
		
		        	@Override
		        	public void run() 
		        	{
		        		tvImage.setBackgroundDrawable(bitmap);
		        		fadpater.notifyDataSetChanged();
		        	}
		
		        	});
		
		        }
		        mCacheMap.put(Path, bitmap);
			}
		});
		pics_thread.start(); 
	}

}
