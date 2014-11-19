package ir.bigandsmall.explorer_android.Displacement.Move;

import ir.bigandsmall.explorer_android.Displacement.CopyFiles;
import ir.bigandsmall.explorer_android.Displacement.Task.DisplacementAsyncTask;

import java.io.File;

public class MoveDirectorys {

	static public void moveDirectory(File sourceLocation, File targetLocation,DisplacementAsyncTask asyncTaskDialog ) 
	{
		if (sourceLocation.isDirectory()) 
			CopyFiles.copyFile(sourceLocation, targetLocation,asyncTaskDialog);
		else 
			MoveFile.moveFile(sourceLocation, targetLocation,asyncTaskDialog);

		checkRemoveDirectorySource(sourceLocation,targetLocation);
	}
	
	static private void checkRemoveDirectorySource(File sourceLocation, File targetLocation) 
	{		
		if(targetLocation.exists())
			sourceLocation.delete();
	}
}
