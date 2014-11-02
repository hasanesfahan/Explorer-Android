package ir.bigandsmall.explorer_android;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity  {

	private FileArrayAdapter adapter;
    
	//private static String CurentPath;
	
	private static String MainFolderSelectedPath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		setParametr();
		
		showMainDirectory();
    }
	
	
	private void showMainDirectory()
	{
		
		MainFolderSelectedPath = "";
		ListView lv = (ListView) findViewById(R.id.act_main_list_view);
		adapter = new FileArrayAdapter(this,R.layout.item_view, new ListMainFolder().getListFile());
	    lv.setAdapter(adapter);
	}
	
	
	private void showCurentDirectory(File f,FileSpecifications fsp)
	{
		ListView lv = (ListView) findViewById(R.id.act_main_list_view);
	    adapter = new FileArrayAdapter(this,R.layout.item_view, new ListFiles(f,fsp).getListFile());
		lv.setAdapter(adapter);
	}
	
	private void setParametr()
	{
		ListView lv = (ListView) findViewById(R.id.act_main_list_view);
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
            	}
            	else if(obj.getFileType() == ListFileTypes.MainFolder)
            	{
            		showMainDirectory();
            	}
            	else if(obj.getFileType() == ListFileTypes.File)
            	{
            		openfil1e(obj.getPath());
            		//Toast.makeText(getApplicationContext(), obj.getPath(), Toast.LENGTH_SHORT).show();
            	}
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
	
	

   
	
	 
}
