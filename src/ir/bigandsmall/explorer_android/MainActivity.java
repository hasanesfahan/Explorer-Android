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
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		setParametr();
		
		showCurentDirectory(new File("/"));
    }
	
	
	private void showCurentDirectory(File f)
	{
		ListView lv = (ListView) findViewById(R.id.act_main_list_view);
	    adapter = new FileArrayAdapter(this,R.layout.item_view, new ListFile(f).getListFile());
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
            	
            	if(!obj.isFileType())
            	{
            		showCurentDirectory(new File(obj.getPath()));
            	//	Toast.makeText(getApplicationContext(), obj.getPath(), Toast.LENGTH_SHORT).show();
            	}
            	else
            		Toast.makeText(getApplicationContext(), obj.getPath(), Toast.LENGTH_SHORT).show();
            }
		});
	}
	
}
