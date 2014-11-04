package ir.bigandsmall.explorer_android;

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
    	if((ListFileTypes.Other == filetype)|| (ListFileTypes.Other == filetype))
    		return "";
    	
    	if(ListFileTypes.Other == filetype)
    		return desc+" Byte";
    	return "<Dir> "+desc+" Byte";
   	
    }
  
    public void setImage(ImageView img) 
    {
    	
    	
    	if(ListFileTypes.Other  == getFileType())
    		img.setBackgroundResource(R.drawable.ic_directory);
    	
    	else if(ListFileTypes.Other == getFileType())
    		img.setBackgroundResource(R.drawable.ic_up);
    	
    	else if(ListFileTypes.Other == getFileType())
    		img.setBackgroundResource(R.drawable.ic_directory);
    	
    	
    	
    	else 
    	{
    		ImageFile imgFi = new ImageFile();
	    	ListFileTypes filetype = imgFi.getImageFileType(name);
	    	
	    	if(ListFileTypes.Photo != filetype)
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
