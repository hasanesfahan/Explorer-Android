package ir.bigandsmall.explorer_android;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

public class ListMainFolder {
	
	
	
	
	
	
	private List<FileSpecifications>dir;
	public  ListMainFolder()
	{
		dir = new ArrayList<FileSpecifications>();
	    
		
		// add SD card
	    if(IsMountedSDCard())
	    	dir.add(new FileSpecifications("SD-card" , 0 ,getSDCardDirectory().getAbsolutePath() , ListFileTypes.Folder_None));
	    
	    
	    // add Photos
	    if(IsMountedSDCard())
	    	dir.add(new FileSpecifications("Photos" , 0 ,getPhotoDirectory().getAbsolutePath() , ListFileTypes.Folder_None));
	   
	   
	    // 	add Download Files
	    dir.add(new FileSpecifications("Downloaded Files" , 0 ,getDownloadDirectory().getAbsolutePath() , ListFileTypes.Folder_None));
	   
	   
	    // 	add File system root
	    dir.add(new FileSpecifications("File system root" , 0 ,getRootDirectory().getAbsolutePath() , ListFileTypes.Folder_None));
	   
	      
	      
	}
	
	public List<FileSpecifications> getListFile()
	{
		return dir;
	}
	
	
	/**  return directory   Photo   **/
	private File getPhotoDirectory()
	{
		return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
	}
	
	/**  return directory   Download   **/
	private File getDownloadDirectory()
	{
		return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
	}
	
	/**  return directory   Root   **/
	private File getRootDirectory()
	{
		return new File("/");
	}
	
	
	/**  return directory   SD card   **/
	private File getSDCardDirectory()
	{
		return Environment.getExternalStorageDirectory();
	}
	
	/**  is mount SD card to system **/
	private boolean IsMountedSDCard()
	{
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

}
