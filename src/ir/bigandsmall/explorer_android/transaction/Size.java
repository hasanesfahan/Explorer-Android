package ir.bigandsmall.explorer_android.transaction;

import java.io.File;

public class Size {
	
	public static long getSize(File directory)
	{
	    long length = 0;
	    if(directory.isDirectory())
	    {
		    for (File file : directory.listFiles()) 
		    {
		        if (file.isFile())
		            length += file.length();
		        else
		            length += getSize(file);
		    }
	    }
	    else
	    	length += directory.length();
	    
	    return length;
	}

}
