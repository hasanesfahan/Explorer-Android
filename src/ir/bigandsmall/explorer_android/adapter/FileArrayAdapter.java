package ir.bigandsmall.explorer_android.adapter;

import ir.bigandsmall.explorer_android.R;
import ir.bigandsmall.explorer_android.definitions.ListFileTypes;
import ir.bigandsmall.explorer_android.definitions.ListTypes;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileArrayAdapter extends ArrayAdapter<FileSpecifications> {

	
	private HashMap<String, Bitmap> mCacheMap  = new HashMap<String, Bitmap>();
	
	//private Context c;
	private int id;
	private List<FileSpecifications>items;
	private LayoutInflater inflater;
	Activity ac;
	
	public FileArrayAdapter(Activity ac1, int textViewResourceId,List<FileSpecifications> objects) 
	{
		super(ac1, textViewResourceId, objects);
		 
		ac = ac1;
		id = textViewResourceId;
		items = objects;
		inflater = (LayoutInflater)ac.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		

	    if(mCacheMap == null)
			mCacheMap = new HashMap<String, Bitmap>();
		
	}
	
	 
	
	public FileSpecifications getItem(int i)
	{
		return items.get(i);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final FileSpecifications o = items.get(position);
		View cv = convertView;
		if (cv == null) 
		{
			 cv = inflater.inflate(id, parent, false);
		}
		else
		{
			if(isImageItem(o))
			{
				
			}
		}
		
		TextView tvName = (TextView) cv.findViewById(R.id.item_view_Text_Name);
		final ImageView tvImage = (ImageView) cv.findViewById(R.id.item_view_Image_File);
		TextView tvDesc = (TextView) cv.findViewById(R.id.item_view_Text_Desc);
		
		tvImage.setBackgroundDrawable(null);
		tvImage.setImageDrawable(null);
		
		
		if(isImageItem(o))
		{
			
			 if(mCacheMap.containsKey(o.getPath()))
			{
				 
				 tvImage.setImageBitmap(isBitmapCached(o.getPath()));
				//Toast.makeText(c, "aaaaaaaaa", 1000).show();
			}
			else if(isBitmapCached(o.getPath()) == null)
			{
			
				//Toast.makeText(c, "bbbbbb1", 1000).show();
			//if(position==8)
			//{
				Thread pics_thread = new Thread(new Runnable(){

	                @Override
	                public void run() {

	               final Bitmap bitmap = grtbitmap(o.getPath());

	                if(bitmap!=null){

	                   ac.runOnUiThread(new Runnable(){

	                            @Override
	                            public void run() {

	                            	mCacheMap.put(o.getPath(), bitmap);
	                            	tvImage.setImageBitmap(bitmap);
	                            	
	                            	notifyDataSetChanged();

	                            }

	                    });

	                }
	                }

	            });

	            pics_thread.start();
				
			}
			else
			{
				//Toast.makeText(c, "cccccccc", 1000).show();
				notifyDataSetChanged();
				tvImage.setImageBitmap(isBitmapCached(o.getPath()));
			} 
		}
		else
		{
			o.setImage(tvImage);
		}
		
		
		tvName.setText(o.getNameShow());
		tvDesc.setText(o.getDescription());
		
		return cv;
	}

	
	public Bitmap isBitmapCached(String name) {
		return mCacheMap.get(name);
	}
	
	private boolean isImageItem(FileSpecifications o)
	{
		if(o.getType() == ListTypes.File)
    		if(o.getFileType() == ListFileTypes.Photo)
    			return true;

		return false;
	}
	
	
	
	private Bitmap grtbitmap(String  params) {

		final File file = new File(params);
		
		 
			
		Bitmap mThumb = null;
		
		
		 
			long len_kb = file.length() / 1024;
			
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.outWidth = 52;
			options.outHeight = 52;
				
			if (len_kb > 1000 && len_kb < 5000) {
				options.inSampleSize = 32;
				options.inPurgeable = true;
				mThumb = BitmapFactory.decodeFile(file.getPath(), options);
									
			} else if (len_kb >= 5000) {
				options.inSampleSize = 32;
				options.inPurgeable = true;
				mThumb = BitmapFactory.decodeFile(file.getPath(), options);
								
			} else if (len_kb <= 1000) {
				options.inPurgeable = true;
				mThumb = Bitmap.createScaledBitmap(
						 						   BitmapFactory.decodeFile(file.getPath()),
						 						   52,
						 						   52,
						 						   false);
			}	
			 
			
			
		
		
	    return mThumb;
	}
}
