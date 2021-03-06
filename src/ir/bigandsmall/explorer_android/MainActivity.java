package ir.bigandsmall.explorer_android;

import ir.bigandsmall.explorer_android.Displacement.Task.DisplacementAsyncTask;
import ir.bigandsmall.explorer_android.Displacement.Task.TaskOperation;
import ir.bigandsmall.explorer_android.Displacement.Task.TypeOperation;
import ir.bigandsmall.explorer_android.adapter.FileArrayAdapter;
import ir.bigandsmall.explorer_android.adapter.FileSpecifications;
import ir.bigandsmall.explorer_android.definitions.ListFolderTypes;
import ir.bigandsmall.explorer_android.definitions.ListTypes;
import ir.bigandsmall.explorer_android.dialog.DialogDirectory;
import ir.bigandsmall.explorer_android.dialog.DialogFile;
import ir.bigandsmall.explorer_android.list.ListFilesDirectory;
import ir.bigandsmall.explorer_android.list.ListMainFolder;
import ir.bigandsmall.explorer_android.openFile.FileOpen;
import ir.bigandsmall.explorer_android.transaction.CollisionDiscovery;
import ir.bigandsmall.explorer_android.transaction.delete.DialogDelete;
import ir.bigandsmall.explorer_android.transaction.newfolder.DialogNewFolder;
import ir.bigandsmall.explorer_android.transaction.rename.DialogRename;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity  {

	private FileArrayAdapter adapter;
    
	private String CurentPath = "";
	
	private String MainFolderSelectedPath;
	
	//private int ItemSelected = 0;
	
	
	private FileSpecifications clipboardFromFileSpecifications=null;
	private FileSpecifications clipboardToFileSpecifications=null;
	private TaskOperation taskOperation = TaskOperation.Copy;
	private TypeOperation typeOperation = TypeOperation.None;
	
	
	private LinearLayout ll;
	
	private ListView lv ;
	
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
		
		
		
	    
		adapter = new FileArrayAdapter(MainActivity.this,R.layout.list_view, new ListMainFolder().getListFile());
		lv.setAdapter(adapter);
			
		setCurentPath();
	}
	
	
	private void showCurentDirectory(File f,FileSpecifications fsp)
	{
	     
	    adapter = new FileArrayAdapter(MainActivity.this,R.layout.list_view, new ListFilesDirectory(f,fsp).getListFile());
		lv.setAdapter(adapter);
		setCurentPath();
	}
	
	private void setParametr()
	{
		ll = (LinearLayout)findViewById(R.id.LinearLayout_Show_View);
		ll.removeAllViews();
		
		lv = new ListView(this);
		ll.addView(lv, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lv.setOnItemClickListener(oicl);
		lv.setOnItemLongClickListener(oilcl);
				
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
	    		
	    		boolean lasteselected = false;
	    		if(clipboardFromFileSpecifications != null)
	    			lasteselected = true;

	    		
			    AlertDialog alertDialog= new DialogDirectory(MainActivity.this , adapter.getItem(position),lasteselected).create();
		    	alertDialog.show();
		    	
	    	}
	    	else
	    	{

			    AlertDialog alertDialog= new DialogFile(MainActivity.this , adapter.getItem(position)).create();
		    	alertDialog.show();
		    	
	    	}
    		
	    	return true;
		}
	};
	
	private void openfile(String filePath)
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
	    	case R.id.action_newfolder :
	    		
	    		createNewFolder(CurentPath);

	    	break;
	    	
	    	case R.id.action_refresh :
	    		 refreshList(true);
	    	break;
	    }
	    return super.onOptionsItemSelected(item);
	}
	 
	public void refreshList(boolean forceThisPath)
	{ 
		
		if(LastFileSpecifications == null)
			showMainDirectory();
		else if (forceThisPath)
		{
			FileSpecifications fsp =new FileSpecifications(new File(CurentPath) , ".." , ListTypes.Folder , ListFolderTypes.Up );
			showCurentDirectory(new File(CurentPath),fsp);
		}
		else if((LastFileSpecifications.getType() == ListTypes.Folder))
    	{
			
			if(LastFileSpecifications.getFlolderType() == ListFolderTypes.Directory)
    		{
				CurentPath = LastFileSpecifications.getPath();
    			FileSpecifications fsp =new FileSpecifications(new File(CurentPath) , ".." , ListTypes.Folder , ListFolderTypes.Up );
    			showCurentDirectory(new File(CurentPath),fsp);
    			
				if(MainFolderSelectedPath.equalsIgnoreCase(""))
					MainFolderSelectedPath = CurentPath;
				
    		}
			else
			{
				if(MainFolderSelectedPath.equalsIgnoreCase(CurentPath))
				{
					showMainDirectory();
				}
				else
				{
					CurentPath = LastFileSpecifications.getParentPath();
	    			FileSpecifications fsp =new FileSpecifications(new File(CurentPath) , ".." , ListTypes.Folder , ListFolderTypes.Up );
	    			showCurentDirectory(new File(CurentPath),fsp);
				}
			}
    		
    	}
		else
	    {
			openfile(LastFileSpecifications.getPath());
		}
		
	}
	

	public void createNewFolder(String path)
	{ 
		AlertDialog alertDialog= new DialogNewFolder(this,path).create();
		alertDialog.show();
	}
	
	public void DeleteFile(FileSpecifications fsp)
	{ 
		AlertDialog alertDialog= new DialogDelete(this,fsp).create();
		alertDialog.show();
	}
	
	
	public void RenameFilesAndFolder(FileSpecifications fsp)
	{ 
		AlertDialog alertDialog= new DialogRename(this,fsp).create();
		alertDialog.show();
	}
	
	private void emptyClipboard()
	{
		clipboardFromFileSpecifications = null;
		clipboardToFileSpecifications = null;
	}
	
	public void CopyFrom(FileSpecifications fsp)
	{
		taskOperation = TaskOperation.Copy;
		clipboardFromFileSpecifications = fsp;
	}
	
	public void CutFrom(FileSpecifications fsp)
	{  
		taskOperation = TaskOperation.Cut;
		clipboardFromFileSpecifications = fsp;
	}
	
	public void PastTo(FileSpecifications fsp)
	{ 
		typeOperation=TypeOperation.None;
		clipboardToFileSpecifications = fsp;
		
		if(clipboardFromFileSpecifications == null)
			return;
		
		
		 if(CollisionDiscovery.isCollisionDiscovery(new File(clipboardFromFileSpecifications.getPath()), new File(clipboardToFileSpecifications.getPath())))
		{
			Toast.makeText(getApplicationContext(), "destination is source", Toast.LENGTH_SHORT).show();
			return;
		}
		
		
		
		
		
		try 
		{
			
			new DisplacementAsyncTask(MainActivity.this,taskOperation,typeOperation,"").execute(new File(clipboardFromFileSpecifications.getPath()),
					new File(clipboardToFileSpecifications.getPath()+"/"+clipboardFromFileSpecifications.getName()));
				
				
		} catch (Exception e) {

		}
		
		
		emptyClipboard(); 
		refreshList(true);

	}
	
	@Override
	public void onBackPressed() 
	{
		if(MainFolderSelectedPath.equalsIgnoreCase(""))
		{
			super.onBackPressed();
		}
		else
		{
			FileSpecifications fsp =new FileSpecifications(new File(CurentPath) , ".." , ListTypes.Folder , ListFolderTypes.Up );
			LastFileSpecifications = fsp;
			refreshList(false);
		}

	}
}
