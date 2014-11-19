package ir.bigandsmall.explorer_android.Displacement.Move;

import ir.bigandsmall.explorer_android.Displacement.CopyFiles;
import ir.bigandsmall.explorer_android.Displacement.Task.DisplacementAsyncTask;

import java.io.File;

public class MoveFile {

	static public  void moveFile(File sourceLocation, File targetLocation,DisplacementAsyncTask asyncTaskDialog) 
	{
		CopyFiles.copyFile(sourceLocation, targetLocation,asyncTaskDialog);
		checkRemoveFileSource(sourceLocation,targetLocation);
	}
	
	
	static private void checkRemoveFileSource(File sourceLocation, File targetLocation) 
	{		
		if(targetLocation.exists())
			sourceLocation.delete();
	}
}
