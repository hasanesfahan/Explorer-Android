package ir.bigandsmall.explorer_android.Displacement;

import ir.bigandsmall.explorer_android.Displacement.Task.DisplacementAsyncTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFiles {
	
	
	static public  void copyFile(File sourceLocation, File targetLocation,DisplacementAsyncTask asyncTaskDialog) 
	{
		asyncTaskDialog.startUpdateProgress(sourceLocation.getPath() , targetLocation.getPath(),sourceLocation.length()+"");
		
		try
		{
			InputStream in = new FileInputStream(sourceLocation);
		    OutputStream out = new FileOutputStream(targetLocation);
	
		    byte[] buf = new byte[1024];
		    int len;
		    
		    while ((len = in.read(buf)) > 0) 
		    {
		    	out.write(buf, 0, len);
		    	
		    	asyncTaskDialog.startUpdateProgress(""+len);
		    }
		    
		    in.close();
		    out.close();
		}
		catch (Exception e) 
		{
			targetLocation.delete();
		}
		
	}

}
