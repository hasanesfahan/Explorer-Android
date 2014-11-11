package ir.bigandsmall.explorer_android.transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Copy {

	static public  void copyFile(File sourceLocation, File targetLocation)throws FileNotFoundException, IOException 
	{
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
	
	static public void copyDirectory(File sourceLocation, File targetLocation)  throws IOException 
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

}
