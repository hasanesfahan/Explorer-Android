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
	
	private FileSpecifications clipboardFromFileSpecifications=null;
	private FileSpecifications clipboardToFileSpecifications=null;
	
	private LinearLayout ll;
	
	private ListView lv ;
	private GridView gv ;
	
	private boolean GridViewListView =  true;
	private FileSpecifications LastFileSpecifications;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 
		
		setParametr();
		
		showMainDirectory();
    }
	
	
	private void showMainDirectory()
	{
		CurentPath = "";
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
			
		setCurentPath();
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
	    	//when long click in main page
	    	if ((CurentPath.equalsIgnoreCase("")))
	    		return true;
	    	
	    	FileSpecifications temp = adapter.getItem(position);
	    	if(temp.getType() == ListTypes.Folder)
	    	{
	    		if((clipboardFromFileSpecifications == null)&&(temp.getFlolderType() == ListFolderTypes.Up))
	    			return true;
	    		
	    		ItemSelected= position;
	    		boolean lasteselected = false;
	    		if(clipboardFromFileSpecifications != null)
	    			lasteselected = true;

	    		
			    AlertDialog alertDialog= new DialogDirectory(MainActivity.this , adapter.getItem(position),lasteselected).create();
		    	alertDialog.show();
		    	
	    	}
	    	else
	    	{
		    	ItemSelected= position;

			    AlertDialog alertDialog= new DialogFile(MainActivity.this , adapter.getItem(position)).create();
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
        		showMainDirectory();
    		}
    		else
    		{
    			CurentPath = LastFileSpecifications.getPath();
    			FileSpecifications fsp =new FileSpecifications(new File(LastFileSpecifications.getParentPath()) , ".." , ListTypes.Folder , ListFolderTypes.Up );
    			showCurentDirectory(new File(LastFileSpecifications.getPath()),fsp);
			}
    		
    	}
    	else
    	{
    		openfil1e(LastFileSpecifications.getPath());
		}
    	
		setCurentPath();
	}
	
	
	
	public void DeleteFile(FileSpecifications fsp)
	{ 
		final File f = new File(fsp.getPath());
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(f.getName()).setTitle("Remove Selected Files");
		builder.setNegativeButton("No", null);
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{
           @Override
           public void onClick(DialogInterface dialog, int which)
           {
        	   if(f.isDirectory())
        		   DeleteDirectorys.deleteDirectory(f);
        	   else
           	 		f.delete();
        	   
        	   refreshList(true);
           }
       });

 	   AlertDialog dialog = builder.create(); 
 	   dialog.show();
 	   
 	   emptyClipboard();
	}
	
	private void emptyClipboard()
	{
		clipboardFromFileSpecifications = null;
		clipboardToFileSpecifications = null;
	}
	
	public void CopyFrom(FileSpecifications fsp)
	{  
		clipboardFromFileSpecifications = fsp;
	}
	
	public void CopyTo(FileSpecifications fsp)
	{ 
		clipboardToFileSpecifications = fsp;
		
		if(clipboardFromFileSpecifications == null)
			return;
		
		if((clipboardFromFileSpecifications.getType() == ListTypes.Folder)
					&&(clipboardToFileSpecifications.getType() == ListTypes.Folder))
		{
			try 
			{
				//if()
				new Copy().copyDirectory(new File(clipboardFromFileSpecifications.getPath()), new File(clipboardToFileSpecifications.getPath()));
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			finally
			{
				clipboardFromFileSpecifications = null;
				clipboardToFileSpecifications = null;
			}
		}
		else
		{
			
		}
		


	}
	
	
}
