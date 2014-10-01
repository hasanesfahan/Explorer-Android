package ir.bigandsmall.explorer_android;
 

public class FileSpecifications implements Comparable<FileSpecifications> {

	private String name;
	private String nameShow;
	private long desc;
    private int image;
    private boolean fileType;
   
    public FileSpecifications(String nameFile, long  descFile, int imgFile , boolean fileType)
    {
        name = nameFile;
        image = imgFile;   
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
    
    public long getDescription()
    {
    	return desc;
    }
  
    public int getImage() 
    {
    	return image;
    }
    public int compareTo(FileSpecifications o) 
    {
    	if(this.name != null)
    		return this.name.compareToIgnoreCase(o.getName());
    	else
    		throw new IllegalArgumentException();
    }

}
