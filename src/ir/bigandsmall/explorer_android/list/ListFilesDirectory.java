package ir.bigandsmall.explorer_android.list;

import ir.bigandsmall.explorer_android.adapter.FileSpecifications;
import ir.bigandsmall.explorer_android.definitions.ListFolderTypes;
import ir.bigandsmall.explorer_android.definitions.ListTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListFilesDirectory {

 
	private List<FileSpecifications>dir;
	private List<FileSpecifications>fls ;
	private File f;
	
	public  ListFilesDirectory(File f,FileSpecifications fsp)
	{
		this.f = f; 
		dir = new ArrayList<FileSpecifications>();
		fls = new ArrayList<FileSpecifications>();
	   
		addFolderGotoUp(fsp);
	}
	
	public List<FileSpecifications> getListFile()
	{
		addFolderAndFile(f);
		return dir;
	}
	
	
	private void addFolderGotoUp(FileSpecifications fsp)
	{
		dir.add(fsp);
	}
	
	private void addFolderAndFile(File f)
	{
		File[]dirs = f.listFiles();
	    try
	    {
	         for(File ff: dirs)
	         {
	             if(ff.isDirectory())
	            	 addFolder(ff);
	             else
	            	 addFile(ff);
	         }
	    }
	    catch(Exception e){}
	    sort();
	    addFileAfterFolder();
	}
	
	private void addFolder(File f)
	{
		dir.add(new FileSpecifications(f  , ListTypes.Folder , ListFolderTypes.Directory ));
	}
	
	private void addFile(File f)
	{
		fls.add(new FileSpecifications(f  ,ListTypes.File));
	}

	private void sort()
	{
		Collections.sort(dir);
        Collections.sort(fls);
	}
	
	private void addFileAfterFolder()
	{
		dir.addAll(fls);
	}

}
