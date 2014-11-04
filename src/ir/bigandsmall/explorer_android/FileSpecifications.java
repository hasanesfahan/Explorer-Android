package ir.bigandsmall.explorer_android;

import java.io.File;

import android.widget.ImageView;
 

public class FileSpecifications  implements Comparable<FileSpecifications> {

	private String name;
	private String nameShow;
	private long desc; 
    private String pathFile;
    
    private ListFileTypes listFileType;
    private ListFolderTypes listFolderTypes;
    private ListTypes listTypes;
   

    public FileSpecifications(File f  ,ListTypes listTypes, ListFileTypes listFileType)
    {
        this.name = f.getName();
        this.desc =  f.length() ;   
        this.pathFile = f.getAbsolutePath();
        
        this.listFileType = listFileType;
        this.listTypes = listTypes;
        
        //setFirstCharNameUpperCase();
    }
    
    
    public FileSpecifications(File f , ListTypes listTypes, ListFolderTypes listFolderTypes)
    {
    	File[] filelist =f.listFiles(); 
    	
    	long  descFile = 0;
	   	 if(filelist != null)
	   		descFile = filelist.length;
	   	 
	   	 
        this.name = f.getName();
        this.desc = descFile;   
        this.pathFile = f.getAbsolutePath();
        
        this.listFolderTypes = listFolderTypes;
        this.listTypes = listTypes;
        
        //setFirstCharNameUpperCase();
    }
    
    public FileSpecifications(File f , String NameOrder,ListTypes listTypes, ListFolderTypes listFolderTypes)
    {
    	
        this.name = NameOrder;//f.getName();
        this.desc = 0;   
        this.pathFile = f.getAbsolutePath();
        
        this.listFolderTypes = listFolderTypes;
        this.listTypes = listTypes;
        
        //setFirstCharNameUpperCase();
    }
    
    public void setFolderType( ListFolderTypes listFolderTypes)
    {
    	this.listFolderTypes = listFolderTypes;
    }
    
    
    
    public void setFirstName(String nameFile)
    {
    	this.name  =  nameFile;
    	setFirstCharNameUpperCase();
    }
    
    private void setFirstCharNameUpperCase()
    {
//    	nameShow  = name.substring(0,1).toUpperCase() + name.substring(1);
    	nameShow  = name;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public String getPath()
    {
    	return pathFile;
    }
    
    public ListTypes getType()
    {
    	return listTypes;
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
    	
    	if(listTypes == ListTypes.Folder)
    		img.setBackgroundResource(ImageFolder.getImageId(listFolderTypes));
    	else
    		img.setBackgroundResource(R.drawable.ic_file);
    		
    	
    	
    	/*if(ListFileTypes.Other  == getFileType())
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
    	}*/
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
