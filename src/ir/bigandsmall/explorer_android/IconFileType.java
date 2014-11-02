package ir.bigandsmall.explorer_android;

import java.io.File;

public class IconFileType {
	

	String urlPaht;
	
	public IconFileType(String urlPaht)
	{
		this.urlPaht = urlPaht;
	}
	
	public int getImageId()
	{
		String url = getExtention();
		
		// Word document
		if (url.equalsIgnoreCase(".doc") || url.equalsIgnoreCase(".docx")) {
            
			return R.drawable.ic_word;
        }
		// PDF file
		else if(url.equalsIgnoreCase(".pdf")){	
			return R.drawable.ic_pdf;
		}

        // Power-point file
        else if(url.equalsIgnoreCase(".ppt") || url.equalsIgnoreCase(".pptx")){
        	return R.drawable.ic_powerpoint;
        }
		
        // Excel file
        else if(url.equalsIgnoreCase(".xls") || url.equalsIgnoreCase(".xlsx")) {
        	return R.drawable.ic_excel;
        }
		
        // WAV audio file
        else if(url.equalsIgnoreCase(".zip") || url.equalsIgnoreCase(".rar")) {
        	return R.drawable.ic_zip;
        } 
        
		// RTF file
        else if(url.equalsIgnoreCase(".rtf")) {
        	return R.drawable.ic_rtf;
        }

        // WAV audio file
        else if(url.equalsIgnoreCase(".wav") || url.equalsIgnoreCase(".mp3")) {
        	return R.drawable.ic_sound;
        } 
        
		// GIF file
        else if(url.equalsIgnoreCase(".gif")) {
        	return R.drawable.ic_gif;
        } 
        
		// JPG file
        else if(url.equalsIgnoreCase(".jpg") || url.equalsIgnoreCase(".jpeg") || url.equalsIgnoreCase(".png")) {
        	return R.drawable.ic_photo;
        }
        
		// Text file
        else if(url.equalsIgnoreCase(".txt")) {
        	return R.drawable.ic_txt;
        } 
        
		// Video files
        else if(url.equalsIgnoreCase(".3gp") || url.equalsIgnoreCase(".mpg") || url.equalsIgnoreCase(".mpeg") || url.equalsIgnoreCase(".mpe") || url.equalsIgnoreCase(".mp4") || url.equalsIgnoreCase(".avi")) {
        	return R.drawable.ic_video;
        }
        
		// APK files
        else if(url.equalsIgnoreCase(".apk"))  {
        	return R.drawable.ic_apk;
        } 
        
		// Other File Type Or None
        else {
        	return R.drawable.ic_file;
        }
	}

	public String getExtention()
	{
		File f = new File(urlPaht);
		if((f.getName().lastIndexOf(".")-1)>0)
			return f.getName().substring(f.getName().lastIndexOf(".")+1);

		return "";
	}

}
