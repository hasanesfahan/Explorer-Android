package ir.bigandsmall.explorer_android;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile {
	
	public CopyFile(String source, String destination) throws IOException 
	{
	    InputStream in = new FileInputStream(new File(source));
	    OutputStream out = new FileOutputStream(new File(destination+"/a.png"));
	    
	    byte[] bufer = new byte[1024];
	    int length;
	    while ((length = in.read(bufer)) > 0)
	    {
	        out.write(bufer, 0, length);
	    }
	    
	    in.close();
	    out.close();
	}

}
