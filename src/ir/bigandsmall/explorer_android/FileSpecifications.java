package ir.bigandsmall.explorer_android;

public class FileSpecifications implements Comparable<FileSpecifications> {

	private String name;
	private String nameShow;
    private int image;
   
    public FileSpecifications(String nameFile, int imgFile)
    {
        name = nameFile;
        image = imgFile;      
        
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
    
    public String getNameShow()
    {
    	return nameShow;
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
