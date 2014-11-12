package ir.bigandsmall.explorer_android.transaction;

import ir.bigandsmall.explorer_android.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

public class CopyAsync extends AsyncTask<File, String, String>
{
	Context co;
	DialogProgressBar dialogProgressBar;
	AlertDialog alertDialog;
	private boolean MoveActive = false;
	
	public CopyAsync(Context co,Boolean MoveActive) 
	{
		this.co = co;
		this.MoveActive = MoveActive;
		
		if(MoveActive)
			dialogProgressBar= new DialogProgressBar(co,"Moving") ;
		else
			dialogProgressBar= new DialogProgressBar(co,"Copying") ;
		
		alertDialog = dialogProgressBar.create();
		alertDialog.show();
	}
	


    @Override
    protected void onPreExecute() 
    {
        super.onPreExecute();
    }

    @Override
	protected void onPostExecute(String result) 
	{
    	alertDialog.dismiss();
    	refreshPareentList();
	}
	
    
	private void refreshPareentList() 
	{
    	MainActivity mtemp = (MainActivity)co;
    	mtemp.refreshList(true);
	}
	

	@Override
	protected String doInBackground(File... files)
	{
		dialogProgressBar.setSizeAllFiles(Size.getSize(files[0]));
		try {
			copyDirectory(files[0],files[1]);
		} catch (Exception e) {}
		
		return null;
	}
	
	protected void onProgressUpdate(String... progress)
	{
		if(progress.length == 3)
		{
			dialogProgressBar.setPathFromAndTo(progress[0] , progress[1]);
			dialogProgressBar.setSizeCurentFile(Long.parseLong(progress[2]));
			
		}
		else
		{
			dialogProgressBar.setSizeCurentFilesLoaded(Long.parseLong(progress[0]));
		}
	}
	

	public void copyDirectory(File sourceLocation, File targetLocation) 
	{
		if (sourceLocation.isDirectory()) 
		{
			if (!targetLocation.exists()) 
			{
				targetLocation.mkdirs();
		    }

		    String[] children = sourceLocation.list();
		    
		    for (int i = 0; i < children.length; i++) 
		    {
		        copyDirectory(new File(sourceLocation, children[i]), new File(targetLocation, children[i]));
		    }
		}
		else 
		{
			copyFile(sourceLocation, targetLocation);
		}
		
		if(MoveActive)
			if(targetLocation.exists())
				sourceLocation.delete();
	}
	
	public  void copyFile(File sourceLocation, File targetLocation) 
	{
		publishProgress(sourceLocation.getPath() , targetLocation.getPath(),sourceLocation.length()+"");
		
		try
		{
			InputStream in = new FileInputStream(sourceLocation);
		    OutputStream out = new FileOutputStream(targetLocation);
	
		    byte[] buf = new byte[1024];
		    int len;
		    
		    while ((len = in.read(buf)) > 0) 
		    {
		    	out.write(buf, 0, len);
		    	
		    	publishProgress(""+len);
		    }
		    
		    in.close();
		    out.close();
		}
		catch (Exception e) 
		{
			targetLocation.delete();
		}
		
		if(MoveActive)
			if(targetLocation.exists())
				sourceLocation.delete();
	}
}
