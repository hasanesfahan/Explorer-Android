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
		 File[]dirs = f.listFiles();
	     
	     List<FileSpecifications>dir = new ArrayList<FileSpecifications>();
	     List<FileSpecifications>fls = new ArrayList<FileSpecifications>();
	    
	     try
	     {
	         for(File ff: dirs)
	         {
	             if(ff.isDirectory())
	             {
	            	 File[] filelist =ff.listFiles(); 

	            	 int buf = 0;
	            	 if(filelist != null)
	            		 buf = filelist.length;
                   
	                dir.add(new FileSpecifications(ff.getName() , buf ,ff.getAbsolutePath() , false));
	             }
	             else
	             {
	                 fls.add(new FileSpecifications(ff.getName(),ff.length() ,ff.getAbsolutePath()  ,true));
	             } 
	         }
	         
	     }
	     catch(Exception e)
	     {}
	     
	     Collections.sort(dir);
         Collections.sort(fls);
	 
	     dir.addAll(fls);
	     
	     adapter = new FileArrayAdapter(this,R.layout.item_view,dir);
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
