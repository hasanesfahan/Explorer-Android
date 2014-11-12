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
	
	public static String humanReadableByteCount(File directory, boolean si) 
	{
		
		long bytes = getSize(directory);
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}


}
