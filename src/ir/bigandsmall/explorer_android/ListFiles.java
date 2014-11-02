package ir.bigandsmall.explorer_android;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListFiles {
	
	 
	private List<FileSpecifications>dir;
	public  ListFiles(File f,FileSpecifications fsp)
	{
		File[]dirs = f.listFiles();
	     
	    dir = new ArrayList<FileSpecifications>();
	    List<FileSpecifications>fls = new ArrayList<FileSpecifications>();
	   
	    // add up folder
	    dir.add(fsp);
	    
	    try
	    {
	         for(File ff: dirs)
	         {
	             if(ff.isDirectory())
	             {
	            	 File[] filelist =ff.listFiles(); 

	            	 int buf = 0;
	            	 if(filelist != null)
	            		 buf = filelist.length;
                   
	                dir.add(new FileSpecifications(ff.getName() , buf ,ff.getAbsolutePath() , ListFileTypes.Folder));
	             }
	             else
	             {
	                 fls.add(new FileSpecifications(ff.getName(),ff.length() ,ff.getAbsolutePath()  ,ListFileTypes.File));
	             } 
	         }
	         
	     }
	     catch(Exception e)
	     {}
	     
	     Collections.sort(dir);
         Collections.sort(fls);
	 
	     dir.addAll(fls);
	      
	}
	
	public List<FileSpecifications> getListFile()
	{
		return dir;
	}
	

}
