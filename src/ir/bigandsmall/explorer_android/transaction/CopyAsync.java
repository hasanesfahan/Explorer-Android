package ir.bigandsmall.explorer_android.transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

public class CopyAsync extends AsyncTask<String, String, String>
{
	Context co;
	DialogProgressBar dialogProgressBar;
	
	public CopyAsync(Context co) 
	{
		this.co = co;
		
		dialogProgressBar= new DialogProgressBar(co) ;
		AlertDialog alertDialog = dialogProgressBar.create();
		alertDialog.show();
	}
	


    @Override
    protected void onPreExecute() 
    {
    	
        super.onPreExecute();
    }

 
	 

	@Override
	protected String doInBackground(String... aurl)
	{
	 
		File sourceLocation = new File("storage/sdcard/DCIM/a/w.jpg");
		
		File targetLocation = new File("storage/sdcard/DCIM/b/w.jpg");
		
		
		
		
		long lenghtOfFile = 0;
		try {
			
			copyDirectory(sourceLocation,targetLocation);
			
	
		} catch (Exception e) {}
	return null;

	}

	

	@Override
	protected void onPostExecute(String unused)
	{
		
	}
	
	protected void onProgressUpdate(String... progress)
	{
		//dialogProgressBar.setSingleProgress(Integer.parseInt(progress[0]));
	}
	
/*	private void setProgressSingle(int SingleProgress)
	{
		dialogProgressBar.setSingleProgress(SingleProgress);
	}*/
	
	/*private void setProgressMain(int mainProgress)
	{
		//dialogProgressBar.setSingleProgress(mainProgress);
	}*/
	
	
	public void copyDirectory(File sourceLocation, File targetLocation)  throws IOException 
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
	}
	
	public  void copyFile(File sourceLocation, File targetLocation)throws FileNotFoundException, IOException 
	{
		
		dialogProgressBar.setPathFromAndTo(sourceLocation.getPath() , targetLocation.getPath());
		
		InputStream in = new FileInputStream(sourceLocation);
	    OutputStream out = new FileOutputStream(targetLocation);

	    byte[] buf = new byte[1024];
	    int len;
	    
	    long lenghtOfFile = sourceLocation.length(),total=0;
	    
	    while ((len = in.read(buf)) > 0) 
	    {
	    	out.write(buf, 0, len);
	    	
	    	total+=len;
	    	publishProgress(""+(int)((total*100)/lenghtOfFile));
	    }
	    
	    in.close();
	    out.close();
	}
}
