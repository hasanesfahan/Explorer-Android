package ir.bigandsmall.explorer_android.adapter;

import ir.bigandsmall.explorer_android.R;
import ir.bigandsmall.explorer_android.cacheImage.ImageLoader;
import ir.bigandsmall.explorer_android.definitions.ListFileTypes;
import ir.bigandsmall.explorer_android.definitions.ListTypes;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileArrayAdapter extends ArrayAdapter<FileSpecifications> {

	private ImageLoader imageLoader;

	
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
		
		imageLoader = new ImageLoader(ac1,this);

	   
		
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
			imageLoader.newImageLoad(o.getPath(),tvImage);
		}
		else
		{
			o.setImage(tvImage);
		}
		
		
		tvName.setText(o.getNameShow());
		tvDesc.setText(o.getDescription());
		
		return cv;
	}

	
	
	
	private boolean isImageItem(FileSpecifications o)
	{
		if(o.getType() == ListTypes.File)
    		if(o.getFileType() == ListFileTypes.Photo)
    			return true;

		return false;
	}
	
	
	
	
	
	

}
