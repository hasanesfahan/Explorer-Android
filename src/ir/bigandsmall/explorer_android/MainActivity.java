package ir.bigandsmall.explorer_android;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity  {

	private FileArrayAdapter adapter;
    
	private String CurentPath;
	
	private String MainFolderSelectedPath;
	
	private LinearLayout ll;
	private ListView lv ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		ll = (LinearLayout)findViewById(R.id.LinearLayout_Main);
		lv = new ListView(this);
		ll.addView(lv, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		
		setParametr();
		
		showMainDirectory();
    }
	
	
	private void showMainDirectory()
	{
		
		MainFolderSelectedPath = "";
		adapter = new FileArrayAdapter(this,R.layout.item_view, new ListMainFolder().getListFile());
	    lv.setAdapter(adapter);
	    
	}
	
	
	private void showCurentDirectory(File f,FileSpecifications fsp)
	{
	    adapter = new FileArrayAdapter(this,R.layout.item_view, new ListFiles(f,fsp).getListFile());
		lv.setAdapter(adapter);
	}
	
	private void setParametr()
	{
		lv.setOnItemClickListener(new OnItemClickListener() 
		{
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
            	FileSpecifications obj = adapter.getItem(position);
            	
            	if((obj.getFileType() == ListFileTypes.Folder)||(obj.getFileType() == ListFileTypes.UpFolder))
            	{
            		FileSpecifications fs;
            		if(MainFolderSelectedPath.equalsIgnoreCase(""))
            		{
            			MainFolderSelectedPath = obj.getPath();
            			fs = new FileSpecifications(".." , 0 , obj.getParentPath() , ListFileTypes.MainFolder);
            		}
            		else if(MainFolderSelectedPath.equalsIgnoreCase(obj.getPath()))
            		{
            			fs = new FileSpecifications(".." , 0 , obj.getParentPath() , ListFileTypes.MainFolder);
            		}
            		else
            		{
            			fs = new FileSpecifications(".." , 0 , obj.getParentPath() , ListFileTypes.UpFolder);
            		}	
            		
            		showCurentDirectory(new File(obj.getPath()),fs);
            		CurentPath = obj.getPath();  
            	}
            	else if(obj.getFileType() == ListFileTypes.MainFolder)
            	{
            		CurentPath = "";
            		showMainDirectory();
            	}
            	else if(obj.getFileType() == ListFileTypes.File)
            	{
            		openfil1e(obj.getPath());
            	}
            	setCurentPath();
            }
		});
	}
	
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
