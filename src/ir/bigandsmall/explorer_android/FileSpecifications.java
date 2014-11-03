package ir.bigandsmall.explorer_android;

import java.io.File;

import android.widget.ImageView;
 

public class FileSpecifications implements Comparable<FileSpecifications> {

	private String name;
	private String nameShow;
	private long desc; 
    private String pathFile;
    
    private ListFileTypes ListFileType;
   
    public FileSpecifications(String nameFile, long  descFile,String pathFile, ListFileTypes ListFileType)
    {
        this.name = nameFile;
        this.desc = descFile;   
        this.pathFile = pathFile;
        this.ListFileType = ListFileType;
        
        setFirstCharNameUpperCase();
    }
    
    public void setFirstName(String nameFile)
    {
    	this.name  =  nameFile;
    	setFirstCharNameUpperCase();
    }
    
    private void setFirstCharNameUpperCase()
    {
    	nameShow  = name.substring(0,1).toUpperCase() + name.substring(1);
    }
    
    public String getName()
    {
    	return name;
    }
    
    public String getPath()
    {
    	return pathFile;
    }
    
    public ListFileTypes getFileType()
    {
    	return ListFileType;
    }
    
    public String getNameShow()
    {
    	return nameShow;
    }
    
    public String getDescription(ListFileTypes filetype)
    {
    	if((ListFileTypes.Folder_Main == filetype)|| (ListFileTypes.Folder_Up == filetype))
    		return "";
    	
    	if(ListFileTypes.File_None == filetype)
    		return desc+" Byte";
    	return "<Dir> "+desc+" Byte";
   	
    }
  
    public void setImage(ImageView img) 
    {
    	
    	
    	if(ListFileTypes.Folder_Main  == getFileType())
    		img.setBackgroundResource(R.drawable.ic_directory);
    	
    	else if(ListFileTypes.Folder_Up == getFileType())
    		img.setBackgroundResource(R.drawable.ic_up);
    	
    	else if(ListFileTypes.Folder_None == getFileType())
    		img.setBackgroundResource(R.drawable.ic_directory);
    	
    	
    	
    	else 
    	{
    		ImageFile imgFi = new ImageFile();
	    	ListFileTypes filetype = imgFi.getImageFileType(name);
	    	
	    	if(ListFileTypes.File_Photo != filetype)
	    		img.setBackgroundResource(imgFi.getImageId(filetype));
	    	else
	    		img.setBackgroundDrawable(imgFi.getImageBitmap(pathFile));
    	}
    }
    public int compareTo(FileSpecifications o) 
    {
    	if(this.name != null)
    		return this.name.compareToIgnoreCase(o.getName());
    	else
    		throw new IllegalArgumentException();
    }
    
    
    public String getParentPath() 
    {
    	if(pathFile.lastIndexOf("/") > 0)
    		return  pathFile.substring(0,pathFile.lastIndexOf("/"));
    	
    	if(pathFile.lastIndexOf("/") == 0)
    		return  "/";
    	
    	return "";
    }

    


}
