package ir.bigandsmall.explorer_android;
 

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
    	if(ListFileTypes.File == filetype)
    		return desc+" Byte";
    	return "<Dir> "+desc+" Byte";
   	
    }
  
    public int getImage(ListFileTypes filetype) 
    {
    	if(ListFileTypes.File == filetype)
    		return new IconFileType(getName()).getImageId();
    	
    	else if(ListFileTypes.MainFolder == filetype)
    		return R.drawable.ic_home;
    	
    	else if(ListFileTypes.UpFolder == filetype)
    		return R.drawable.ic_up;
	
    	
    	return R.drawable.ic_directory;
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
