package ir.bigandsmall.explorer_android;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


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
		ll = (LinearLayout)findViewById(R.id.LinearLayout_Show_View);
		ll.removeAllViews();
		
		if(GridViewListView)
		{
			lv = new ListView(this);
			ll.addView(lv, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			lv.setOnItemClickListener(oicl);
			lv.setOnItemLongClickListener(oilcl);
		}
		else
		{
			gv = new GridView(this);
			gv.setNumColumns(4);
			ll.addView(gv, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			gv.setOnItemClickListener(oicl);
			gv.setOnItemLongClickListener(oilcl);
		}
		
		
		 
	}
	
	
	OnItemClickListener oicl = new OnItemClickListener() 
	{
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
        	FileSpecifications obj = adapter.getItem(position);

        	
        	if((obj.getType() == ListTypes.Folder))
        	{

        		if(MainFolderSelectedPath.equalsIgnoreCase(""))
        			MainFolderSelectedPath = obj.getPath();
        		
        		
        		if((obj.getFlolderType() == ListFolderTypes.Up)&&(MainFolderSelectedPath.equalsIgnoreCase(CurentPath)))
        		{
        			CurentPath = "";
            		showMainDirectory();
        		}
        		else
        		{
        			CurentPath = obj.getPath();
        			FileSpecifications fsp =new FileSpecifications(new File(obj.getParentPath()) , ".." , ListTypes.Folder , ListFolderTypes.Up );
        			showCurentDirectory(new File(obj.getPath()),fsp);
				}
        		  
        	}
        	else
        	{
        		openfil1e(obj.getPath());
			}
        	
        	setCurentPath();
        }
	};
	
	
	OnItemLongClickListener  oilcl = new OnItemLongClickListener() 
	{
	    @Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) 
        {
	    	FileSpecifications obj = adapter.getItem(position);
	    	
			return false;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) 
	 {
	    switch (item.getItemId()) 
	    {
	    	case R.id.action_settings:
	    		GridViewListView = !GridViewListView;
	    		setParametr();
	    		showMainDirectory();
	    	break;
	    	
	    	case R.id.action_newfolder :
	    		
	    		AlertDialog alertDialog= new DialogNewFolder(this,CurentPath).create();
	    		alertDialog.show();

	    	break;
	    }
	    return super.onOptionsItemSelected(item);
	  }
	
}
