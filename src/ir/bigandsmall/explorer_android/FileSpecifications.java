package ir.bigandsmall.explorer_android;

import ir.bigandsmall.explorer_android.definitions.ListFileTypes;
import ir.bigandsmall.explorer_android.definitions.ListFolderTypes;
import ir.bigandsmall.explorer_android.definitions.ListTypes;
import ir.bigandsmall.explorer_android.icon.ImageFile;
import ir.bigandsmall.explorer_android.icon.ImageFolder;
import ir.bigandsmall.explorer_android.transaction.Size;

import java.io.File;

import android.widget.ImageView;
 

public class FileSpecifications  implements Comparable<FileSpecifications> {

	private String name;
	private String nameShow;
	private String desc; 
    private String pathFile;
    
    private ListFileTypes listFileType;
    private ListFolderTypes listFolderTypes;
    private ListTypes listTypes;
   

    public FileSpecifications(File f  ,ListTypes listTypes)
    {
        this.name = f.getName();
        this.desc = Size.humanReadableByteCount(f,false) ;   
        this.pathFile = f.getAbsolutePath();
        
         this.listTypes = listTypes;
        
        setFirstCharNameUpperCase();
        
        this.listFileType = ImageFile.getImageFileType(name);
    }
    
    
    public FileSpecifications(File f , ListTypes listTypes, ListFolderTypes listFolderTypes)
    {
    	File[] filelist =f.listFiles(); 
    	

    	if(filelist != null)
    		this.desc  = ""+filelist.length;
	   	 
	   	 
        this.name = f.getName();   
        this.pathFile = f.getAbsolutePath();
        
        this.listFolderTypes = listFolderTypes;
        this.listTypes = listTypes;
        
        setFirstCharNameUpperCase();
    }
    
    public FileSpecifications(File f , String NameOrder,ListTypes listTypes, ListFolderTypes listFolderTypes)
    {

    	File[] filelist =f.listFiles(); 
    	
    	if(filelist != null)
    		this.desc =""+ filelist.length;

        this.name = NameOrder;   
        this.pathFile = f.getAbsolutePath();
        
        this.listFolderTypes = listFolderTypes;
        this.listTypes = listTypes;
        
        setFirstCharNameUpperCase();
    }
    
    private void setFirstCharNameUpperCase()
    {
    	if(name.length()<=1)
    		nameShow  = name.toUpperCase(); 
    	else
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
    
    
    public ListTypes getType()
    {
    	return listTypes;
    }
    
    public ListFolderTypes getFlolderType()
    {
    	return listFolderTypes;
    }
    
    public String getNameShow()
    {
    	return nameShow;
    }
    
    public String getDescription()
    {
    	if(ListTypes.File == listTypes)
    		return desc;
    	
    	else if(ListFolderTypes.Up == listFolderTypes)
    		return "";
    	
    	else
    		return String.format("%15s", "<Dir - "+desc+" >");
    }
  
    public void setImage(ImageView img) 
    {
    	if(listTypes == ListTypes.Folder)
    		img.setBackgroundResource(ImageFolder.getImageId(listFolderTypes));
    	else
    	{
    		//if(listFileType != ListFileTypes.Photo)
    			img.setBackgroundResource(ImageFile.getImageId(name));
    		//else
    		//	img.setBackgroundDrawable(ImageFile.getImageBitmap(pathFile));
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
