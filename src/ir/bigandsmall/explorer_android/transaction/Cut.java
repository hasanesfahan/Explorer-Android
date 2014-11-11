package ir.bigandsmall.explorer_android.transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Cut {
	
	static public boolean cutFile(File sourceLocation, File targetLocation)throws FileNotFoundException, IOException 
	{
		try 
		{
			Copy.copyFile(sourceLocation, targetLocation);
		}
		catch (Exception e) 
		{
			targetLocation.delete();
		}
		
		if(targetLocation.exists())
		{
			sourceLocation.delete();
			return true;
		}
		else
			return false;
	}
	
	static public boolean cutDirectory(File sourceLocation, File targetLocation)  throws IOException 
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
		    	if(!cutDirectory(new File(sourceLocation, children[i]), new File(targetLocation, children[i])))
		    		return false;
		    }
		}
		else 
		{
			return cutFile(sourceLocation, targetLocation);
		}
		
		if(targetLocation.exists())
			sourceLocation.delete();
		
		return true;
	}

}
