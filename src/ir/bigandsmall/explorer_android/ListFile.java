package ir.bigandsmall.explorer_android;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListFile {
	
	 
	private List<FileSpecifications>dir;
	public  ListFile(File f)
	{
		File[]dirs = f.listFiles();
	     
	    dir = new ArrayList<FileSpecifications>();
	    List<FileSpecifications>fls = new ArrayList<FileSpecifications>();
	    
	    String  parentPath =  f.getAbsolutePath().substring(0,f.getAbsolutePath().lastIndexOf("/")+1);
	    if(parentPath.length() == 0)
	    	parentPath = "/";
	   
	    if(f.getAbsolutePath().length() > 1)
	    	dir.add(new FileSpecifications(".." , 0 ,parentPath , false));
	    
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
                   
	                dir.add(new FileSpecifications(ff.getName() , buf ,ff.getAbsolutePath() , false));
	             }
	             else
	             {
	                 fls.add(new FileSpecifications(ff.getName(),ff.length() ,ff.getAbsolutePath()  ,true));
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
