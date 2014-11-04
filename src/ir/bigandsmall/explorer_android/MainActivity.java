package ir.bigandsmall.explorer_android;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity  {

	private FileArrayAdapter adapter;
    
	private String CurentPath;
	
	private String MainFolderSelectedPath;
	
	private LinearLayout ll;
	
	private ListView lv ;
	private GridView gv ;
	
	private boolean GridViewListView =  true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		ll = (LinearLayout)findViewById(R.id.LinearLayout_Main);
		
		
		
		if(GridViewListView)
		{
			lv = new ListView(this);
			ll.addView(lv, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		}
		else
		{
			gv = new GridView(this);
			gv.setNumColumns(4);
			ll.addView(gv, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		}
			
		
		
		
		
		setParametr();
		
		showMainDirectory();
    }
	
	
	private void showMainDirectory()
	{
		
		MainFolderSelectedPath = "";
		
		
		
	    
		
		if(GridViewListView)
		{
			adapter = new FileArrayAdapter(this,R.layout.list_view, new ListMainFolder().getListFile());
			lv.setAdapter(adapter);
		}
		else
		{
			adapter = new FileArrayAdapter(this,R.layout.grid_view, new ListMainFolder().getListFile());
			gv.setAdapter(adapter);
		}
			
	    
	}
	
	
	private void showCurentDirectory(File f,FileSpecifications fsp)
	{
	     
	    if(GridViewListView)
		{
	    	adapter = new FileArrayAdapter(this,R.layout.list_view, new ListFilesDirectory(f,fsp).getListFile());
			lv.setAdapter(adapter);
		}
		else
		{
			adapter = new FileArrayAdapter(this,R.layout.grid_view, new ListFilesDirectory(f,fsp).getListFile());
			gv.setAdapter(adapter);
		}
	}
	
	private void setParametr()
	{
		if(GridViewListView)
			lv.setOnItemClickListener(oicl);
		else
			gv.setOnItemClickListener(oicl);
		
		 
	}
	
	
	OnItemClickListener oicl = new OnItemClickListener() 
	{
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
        	FileSpecifications obj = adapter.getItem(position);
        	
        	//if((obj.getFileType() == ListFileTypes.Other)||(obj.getFileType() == ListFileTypes.Other))
        	{
        		FileSpecifications fs;
        		/*if(MainFolderSelectedPath.equalsIgnoreCase(""))
        		{
        			MainFolderSelectedPath = obj.getPath();
        			fs = new FileSpecifications(".." , 0 , obj.getParentPath() , ListFileTypes.Other);
        			
        		}
        		else if(MainFolderSelectedPath.equalsIgnoreCase(obj.getPath()))
        		{
        			fs = new FileSpecifications(".." , 0 , obj.getParentPath() , ListFileTypes.Other);
        		}
        		else
        		{
        			fs = new FileSpecifications(".." , 0 , obj.getParentPath() , ListFileTypes.Other);
        		}	*/
        		
        		obj.setFolderType(ListFolderTypes.Up);
        		obj.setFirstName("..");
        		
        		showCurentDirectory(new File(obj.getPath()),obj);
        		CurentPath = obj.getPath();  
        	}
        	/*else if(obj.getFileType() == ListFileTypes.Other)
        	{
        		CurentPath = "";
        		showMainDirectory();
        	}
        	else if(obj.getFileType() == ListFileTypes.Other)
        	{
        		openfil1e(obj.getPath());
        	}*/
        	//setCurentPath();
        }
	};
	
	private void openfil1e(String filePath)
	{ 
		 FileOpen inten = new FileOpen(filePath);

		 try {
			 startActivity(inten);
		 } 
		 catch (Exception e) {
		 } 
	}
	
	
	private void setCurentPath()
	{ 
		TextView tv = (TextView)findViewById(R.id.textView_Path_Curent);
		tv.setText(CurentPath);
		
	}

   
	
	 
}
