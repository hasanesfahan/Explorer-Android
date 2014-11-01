package ir.bigandsmall.explorer_android;
 

public class FileSpecifications implements Comparable<FileSpecifications> {

	private String name;
	private String nameShow;
	private long desc;
    private boolean fileType;
    private String pathFile;
   
    public FileSpecifications(String nameFile, long  descFile,String pathFile, boolean filetype)
    {
        this.name = nameFile;
        this.desc = descFile;  
        this.fileType = filetype;   
        this.pathFile = pathFile;
        
        setFirstCharNameUpperCase();
    }
    
    public void setFirstCharNameUpperCase()
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
    		return new IconType(getName()).getIconId();
    	return R.drawable.ic_directory;
    }
    public int compareTo(FileSpecifications o) 
    {
    	if(this.name != null)
    		return this.name.compareToIgnoreCase(o.getName());
    	else
    		throw new IllegalArgumentException();
    }

}
