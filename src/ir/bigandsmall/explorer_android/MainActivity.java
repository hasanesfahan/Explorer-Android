package ir.bigandsmall.explorer_android;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


public class MainActivity extends ListActivity  {

	private FileArrayAdapter adapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		showCurentDirectory(new File("/"));
    }
	
	
	private void showCurentDirectory(File f)
	{
	    adapter = new FileArrayAdapter(this,R.layout.item_view, new ListFile(f).getListFile());
	    setListAdapter(adapter);
	}
	
	@Override
    protected void onListItemClick(ListView listview, View view, int position, long id) 
    {
    	super.onListItemClick(listview, view, position, id);
    	FileSpecifications obj = adapter.getItem(position);
    	
    	if(!obj.isFileType())
    	{
    		showCurentDirectory(new File(obj.getPath()));
    	}
    }
}
