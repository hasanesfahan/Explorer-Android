package ir.bigandsmall.explorer_android;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;


public class MainActivity extends ListActivity  {

	private File currentDirctory;
    private FileArrayAdapter adapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		currentDirctory = new File("/");
        showCurentDirectory(currentDirctory);
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
	                dir.add(new FileSpecifications(ff.getName(),R.drawable.ic_launcher));
	             }
	             else
	             {
	                 fls.add(new FileSpecifications(ff.getName(),R.drawable.ic_launcher));
	             } 
	         }
	         
	     }
	     catch(Exception e)
	     {}
	 
	     dir.addAll(fls);
	     
	     adapter = new FileArrayAdapter(this,R.layout.item_view,dir);
	     setListAdapter(adapter);
   }


}
