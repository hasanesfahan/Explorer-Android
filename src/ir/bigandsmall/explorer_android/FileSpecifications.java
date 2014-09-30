package ir.bigandsmall.explorer_android;

public class FileSpecifications implements Comparable<FileSpecifications> {

	private String name;
    private int image;
   
    public FileSpecifications(String nameFile, int imgFile)
    {
        name = nameFile;
        image = imgFile;           
    }
    
    public String getName()
    {
    	return name;
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
