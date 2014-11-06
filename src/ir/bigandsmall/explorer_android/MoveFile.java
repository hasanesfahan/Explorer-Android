package ir.bigandsmall.explorer_android;

import java.io.File;
import java.io.IOException;

public class MoveFile {
	
	public MoveFile(String source, String destination) throws IOException 
	{
		try {
			new MoveFile(source, destination);
			
			File f=new File(source);
			f.delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
	}

}
