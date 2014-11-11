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
import android.util.Log;

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
			 
		InputStream in = new FileInputStream(sourceLocation);
	    OutputStream out = new FileOutputStream(targetLocation);

	    
	    lenghtOfFile = sourceLocation.length();
	    
	    Log.d("ANDRO_ASYNC_ff",lenghtOfFile+"");
	    
	    
	    byte[] buf = new byte[1024];
	    int len;
	    
	    long total = 0;
	    
	    while ((len = in.read(buf)) > 0) 
	    {
	      out.write(buf, 0, len);
	      total+=len;
	      publishProgress(""+(int)((total*100)/lenghtOfFile));
	      
	      for (int i = 0; i < 999999999; i++);
	      
	    }
	    
	    in.close();
	    out.close(); 
	    
		
	  // Copy.copyFile(sourceLocation, targetLocation, this);
			
			
	
		} catch (Exception e) {}
	return null;

	}

	protected void onProgressUpdate(String... progress)
	{
		dialogProgressBar.setSingleProgress(Integer.parseInt(progress[0]));
	}

	@Override
	protected void onPostExecute(String unused)
	{
		
	}
	
	

	
	
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
	    
	    while ((len = in.read(buf)) > 0) 
	    {
	    	out.write(buf, 0, len);
	    }
	    
	    in.close();
	    out.close();
	}
}
