package ir.bigandsmall.explorer_android;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity  {

	private FileArrayAdapter adapter;
    
	private String CurentPath = "";
	
	private String MainFolderSelectedPath;
	
	private int ItemSelected = 0;
	
	private String clipboardPath;
	
	private LinearLayout ll;
	
	private ListView lv ;
	private GridView gv ;
	
	private boolean GridViewListView =  true;
	private FileSpecifications LastFileSpecifications;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		clipboardPath="";
		
		setParametr();
		
		showMainDirectory();
    }
	
	
	private void showMainDirectory()
	{
		LastFileSpecifications = null;
		
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
        	LastFileSpecifications  = adapter.getItem(position);

        	refreshList(false);
        }
	};
	
	
	
	
	OnItemLongClickListener  oilcl = new OnItemLongClickListener() 
	{
	    @Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) 
        {
	    	
	    	if(!CurentPath.equalsIgnoreCase(""))
	    	{
	    		ItemSelected= position;
	    		FileSpecifications obj = adapter.getItem(position);
		    	
		    	AlertDialog alertDialog= new DialogActionOnFolder(MainActivity.this , obj.getPath()).create();
	    		alertDialog.show();
	    		
	    	}
    		
	    	return true;
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
	    	
	    	case R.id.action_refresh :
	    		 refreshList(true);
	    	break;
	    }
	    return super.onOptionsItemSelected(item);
	  }
	 
	public void refreshList(boolean forceThisPath)
	{ 
		
		
		if(forceThisPath)
		{
			FileSpecifications fsp =new FileSpecifications(new File(CurentPath) , ".." , ListTypes.Folder , ListFolderTypes.Up );
			showCurentDirectory(new File(LastFileSpecifications.getPath()),fsp);
		}
		else if(LastFileSpecifications == null)
		{
			Toast.makeText(getApplicationContext(), "ssssssssss", 1000).show();
			showMainDirectory();
		}
		else if((LastFileSpecifications.getType() == ListTypes.Folder))
    	{

    		if(MainFolderSelectedPath.equalsIgnoreCase(""))
    		{
    			MainFolderSelectedPath = LastFileSpecifications.getPath();
    		}
    		
    		if((LastFileSpecifications.getFlolderType() == ListFolderTypes.Up)&&(MainFolderSelectedPath.equalsIgnoreCase(CurentPath)))
    		{
    			CurentPath = "";
        		showMainDirectory();
    		}
    		else
    		{
    			CurentPath = LastFileSpecifications.getPath();
    			FileSpecifications fsp =new FileSpecifications(new File(LastFileSpecifications.getParentPath()) , ".." , ListTypes.Folder , ListFolderTypes.Up );
    			showCurentDirectory(new File(LastFileSpecifications.getPath()),fsp);
			}
    		setCurentPath();
    	}
    	else
    	{
    		openfil1e(LastFileSpecifications.getPath());
		}
    	
    	
	}
	
	
	
	public void DeleteFile(String Path)
	{ 
		final File f = new File(Path);
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(f.getName()).setTitle("Remove Selected Files");
		builder.setNegativeButton("No", null);
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{
           @Override
           public void onClick(DialogInterface dialog, int which)
           {
           	 	f.delete();
           	 	refreshList(true);
           }
       });

 	   AlertDialog dialog = builder.create(); 
 	   dialog.show();
 	   
	}
	
	public void CopyFileFrom(String Path)
	{  
		clipboardPath = Path;
	}
	
	public void CopyFileTo(String destination)
	{ 
		if(clipboardPath.equalsIgnoreCase(""))
			return;
		
		try { 
			
			if(ItemSelected == 0)
				new CopyFile(clipboardPath, CurentPath);
			else
				new CopyFile(clipboardPath, destination);
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		clipboardPath = "";
		refreshList(true);
	}
	
	
}
