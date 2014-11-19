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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FileArrayAdapter extends ArrayAdapter<FileSpecifications> implements OnClickListener {

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
		final FileSpecifications fsp = items.get(position);
		if (convertView == null) 
			convertView = inflater.inflate(id, parent, false);

		
		TextView tvName = (TextView) convertView.findViewById(R.id.item_view_Text_Name);
		final ImageView tvImage = (ImageView) convertView.findViewById(R.id.item_view_Image_File);
		TextView tvDesc = (TextView) convertView.findViewById(R.id.item_view_Text_Desc);
		
		setImage(fsp, tvImage);
		
		tvName.setText(fsp.getNameShow());
		tvDesc.setText(fsp.getDescription());
		tvImage.setOnClickListener(this);
		
		return convertView;
	}

	
	@Override
	public void onClick(View v) 
	{
		ImageView vm = (ImageView)v;
		FileSpecifications f =(FileSpecifications) vm.getTag();
		
		if(f.getChoosed())
		{
			f.setChoosed(false);
			vm.setImageResource(0);
		}
		else
		{
			f.setChoosed(true);
			vm.setImageResource(R.drawable.ic_true);
		}
	}
	
	private void setImage(FileSpecifications fsp,ImageView tvImage)
	{
		tvImage.setBackgroundResource(R.drawable.ic_def);
		
		if(!fsp.getChoosed())
			tvImage.setImageResource(0);
		else
			tvImage.setImageResource(R.drawable.ic_true);
		
		
		if(isImageItem(fsp))
			imageLoader.newImageLoad(fsp.getPath(),tvImage);
		else
			fsp.setImage(tvImage);
		tvImage.setTag(fsp);
			
	}
	
	private boolean isImageItem(FileSpecifications o)
	{
		if(o.getType() == ListTypes.File)
    		if(o.getFileType() == ListFileTypes.Photo)
    			return true;

		return false;
	}
	
	 
	
	

}
