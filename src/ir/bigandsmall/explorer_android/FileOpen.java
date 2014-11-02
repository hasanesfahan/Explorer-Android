package ir.bigandsmall.explorer_android;

import java.io.File;
import java.io.IOException;

import android.content.Intent;
import android.net.Uri;

public class FileOpen extends Intent {
	
	 public FileOpen(String url) 
	 {
	        
		 	File file=new File (url);
	        Uri uri = Uri.fromFile(file);
	        
	        setAction(Intent.ACTION_VIEW);
			
			
			
	        // Check what kind of file you are trying to open, by comparing the url with extensions.
	        // When the if condition is matched, plugin sets the correct intent (mime) type, 
	        // so Android knew what application to use to open the file
	        if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
	            // Word document
	            setDataAndType(uri, "application/msword");
	        } else if(url.toString().contains(".pdf")) {
	            // PDF file
	            setDataAndType(uri, "application/pdf");
	        } else if(url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
	            // Power-point file
	            setDataAndType(uri, "application/vnd.ms-powerpoint");
	        } else if(url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
	            // Excel file
	            setDataAndType(uri, "application/vnd.ms-excel");
	        } else if(url.toString().contains(".zip") || url.toString().contains(".rar")) {
	            // WAV audio file
	            setDataAndType(uri, "application/x-wav");
	        } else if(url.toString().contains(".rtf")) {
	            // RTF file
	            setDataAndType(uri, "application/rtf");
	        } else if(url.toString().contains(".wav") || url.toString().contains(".mp3")) {
	            // WAV audio file
	            setDataAndType(uri, "audio/x-wav");
	        } else if(url.toString().contains(".gif")) {
	            // GIF file
	            setDataAndType(uri, "image/gif");
	        } else if(url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
	            // JPG file
	            setDataAndType(uri, "image/jpeg");
	        } else if(url.toString().contains(".txt")) {
	            // Text file
	            setDataAndType(uri, "text/plain");
	        } else if(url.toString().contains(".3gp") || url.toString().contains(".mpg") || url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
	            // Video files
	            setDataAndType(uri, "video/*");
	        }else if(url.toString().contains(".apk"))  {
	            // Apk files
	            setDataAndType(uri, "application/vnd.android.package-archive");
	        } 
	        
	        else {
	            setDataAndType(uri, "*/*");
	        }
	        
	        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 

	    }
}
