package ir.bigandsmall.explorer_android;

import java.io.File;

public class CollisionDiscovery {

	static public boolean isCollisionDiscovery(File sourceLocation, File targetLocation)  
	{
		if (sourceLocation.isDirectory()) 
		{
			if (sourceLocation.getPath().toString().equalsIgnoreCase(targetLocation.getPath().toString())) 
				return false;

		    String[] children = sourceLocation.list();
		    
		    for (int i = 0; i < children.length; i++) 
		    {
		    	return isCollisionDiscovery(new File(sourceLocation, children[i]), targetLocation);
		    }
		}
		return false;
	}
}
