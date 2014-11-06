package ir.bigandsmall.explorer_android;

import java.io.File;

public class NewFolder {
	
	
	public static boolean createNewFolder(String Path)
	{
		File f = new File(Path);
        if (!f.exists()) 
            if(f.mkdirs())
            	return true;
        
        
        return false;
	}

}
