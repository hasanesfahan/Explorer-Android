package ir.bigandsmall.explorer_android;
 

public class FileSpecifications implements Comparable<FileSpecifications> {

	private String name;
	private String nameShow;
	private long desc;
    private boolean fileType;
   
    public FileSpecifications(String nameFile, long  descFile, boolean fileType)
    {
        name = nameFile;
  
        desc = descFile;  
        fileType = fileType;   
        
        
        setFirstCharNameUpperCase();
    }
    
    public void setFirstCharNameUpperCase()
    {
    	nameShow  = name.substring(0,1).toUpperCase() + name.substring(1);;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public boolean isFileType()
    {
    	return fileType;
    }
    
    public String getNameShow()
    {
    	return nameShow;
    }
    
    public String getDescription(boolean filetype)
    {
    	if(filetype)
    		return desc+" Byte";
    	return "<Dir> "+desc+" Byte";
   	
    }
  
    public int getImage(boolean filetype) 
    {
    	if(filetype)
    		return R.drawable.file_icon;
    	return R.drawable.directory_icon;
    }
    public int compareTo(FileSpecifications o) 
    {
    	if(this.name != null)
    		return this.name.compareToIgnoreCase(o.getName());
    	else
    		throw new IllegalArgumentException();
    }

}
