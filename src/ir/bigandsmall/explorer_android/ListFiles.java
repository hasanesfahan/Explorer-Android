package ir.bigandsmall.explorer_android;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListFiles {

 
	private List<FileSpecifications>dir;
	   List<FileSpecifications>fls ;
	
	public  ListFiles(File f,FileSpecifications fsp)
	{
		File[]dirs = f.listFiles();
	     
	    dir = new ArrayList<FileSpecifications>();
	    fls = new ArrayList<FileSpecifications>();
	   
	    // add up folder
	    dir.add(fsp);
	    
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
	     catch(Exception e)
	     {}
	     

	    sort();
	 
	    dir.addAll(fls);
	      
	}
	
	public List<FileSpecifications> getListFile()
	{
		return dir;
	}
	
	
	private void addFolder(File f)
	{
	   	 File[] filelist =f.listFiles(); 
	
	   	 int buf = 0;
	   	 if(filelist != null)
	   		 buf = filelist.length;
	      
	   	 dir.add(new FileSpecifications(f.getName() , buf ,f.getAbsolutePath() , ListFileTypes.Other));
	}
	
	private void addFile(File f)
	{
		fls.add(new FileSpecifications(f.getName(),f.length() ,f.getAbsolutePath()  ,ListFileTypes.Other));
	}

	private void sort()
	{
		Collections.sort(dir);
        Collections.sort(fls);
	}

}
