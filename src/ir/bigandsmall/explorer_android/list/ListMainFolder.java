package ir.bigandsmall.explorer_android.list;

import ir.bigandsmall.explorer_android.adapter.FileSpecifications;
import ir.bigandsmall.explorer_android.definitions.ListFolderTypes;
import ir.bigandsmall.explorer_android.definitions.ListTypes;

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
	    	dir.add(new FileSpecifications(getSDCardDirectory() , "SD-card" , ListTypes.Folder , ListFolderTypes.Directory ));
	    
	    // add Photos
	    if(IsMountedSDCard())
	    	dir.add(new FileSpecifications(getPhotoDirectory() , "Photos" , ListTypes.Folder , ListFolderTypes.Directory ));
	   
	    // 	add Download Files
	    dir.add(new FileSpecifications(getDownloadDirectory() , "Downloaded Files" , ListTypes.Folder , ListFolderTypes.Directory ));
	   
	    // 	add File system root
	    dir.add(new FileSpecifications(getRootDirectory() , "File system root" , ListTypes.Folder , ListFolderTypes.Directory ));
	   
	      
	      
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
