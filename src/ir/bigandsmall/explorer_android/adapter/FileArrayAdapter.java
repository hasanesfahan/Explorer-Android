package ir.bigandsmall.explorer_android.adapter;

import ir.bigandsmall.explorer_android.R;
import ir.bigandsmall.explorer_android.R.id;
import ir.bigandsmall.explorer_android.definitions.ListTypes;

import java.util.List;

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

	private Context c;
	private int id;
	private List<FileSpecifications>items;
	
	public FileArrayAdapter(Context context, int textViewResourceId,List<FileSpecifications> objects) 
	{
		super(context, textViewResourceId, objects);
		c = context;
		id = textViewResourceId;
		items = objects;
	}
	
	public FileSpecifications getItem(int i)
	{
		return items.get(i);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View cv = convertView;
		if (cv == null) 
		{
			LayoutInflater vi = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			cv = vi.inflate(id, null);
		}
		
		final FileSpecifications o = items.get(position);
		
		TextView tvName = (TextView) cv.findViewById(R.id.item_view_Text_Name);
		ImageView tvImage = (ImageView) cv.findViewById(R.id.item_view_Image_File);
		
		if(o.getType() == ListTypes.Folder)
			tvImage.setOnClickListener(this);
		
		o.setImage(tvImage);
		tvName.setText(o.getNameShow());
		
		if(cv.findViewById(R.id.item_view_Text_Desc) != null)
		{
			TextView tvDesc = (TextView) cv.findViewById(R.id.item_view_Text_Desc);
			tvDesc.setText(o.getDescription());
		}
		
		
		 
		return cv;
	}

	@Override
	public void onClick(View v) 
	{

		Toast.makeText(c, "sssssss", 1000).show();
	}
}