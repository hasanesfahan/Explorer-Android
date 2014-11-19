package ir.bigandsmall.explorer_android.Displacement;

import ir.bigandsmall.explorer_android.Displacement.Task.DisplacementAsyncTask;

import java.io.File;

public class CopyDirectorys {


	static public void copyDirectory(File sourceLocation, File targetLocation,DisplacementAsyncTask asyncTaskDialog ) 
	{
		if (sourceLocation.isDirectory()) 
		{
			if (!targetLocation.exists()) 
				targetLocation.mkdirs();


		    String[] children = sourceLocation.list();
		    
		    for (int i = 0; i < children.length; i++) 
		        copyDirectory(new File(sourceLocation, children[i]), new File(targetLocation, children[i]),asyncTaskDialog);

		}
		else 
			CopyFiles.copyFile(sourceLocation, targetLocation,asyncTaskDialog);
	}
	
}
