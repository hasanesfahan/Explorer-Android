package ir.bigandsmall.explorer_android;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;


public class ImageFile {
	
	public int getImageId(ListFileTypes listFileTypes)
	{
		// Word document
		if (listFileTypes == ListFileTypes.File_Word) {
			return R.drawable.ic_word;
        }
		
		// PDF file
		if (listFileTypes == ListFileTypes.File_Pdf) {	
			return R.drawable.ic_pdf;
		}

        // Power-point file
		if (listFileTypes == ListFileTypes.File_Powerpoint) {
        	return R.drawable.ic_powerpoint;
        }
		
        // Excel file
		if (listFileTypes == ListFileTypes.File_Excel) {
        	return R.drawable.ic_excel;
        }
		
        // WAV audio file
		if (listFileTypes == ListFileTypes.File_Zip) {
        	return R.drawable.ic_zip;
        } 
        
		// RTF file
		if (listFileTypes == ListFileTypes.File_Rtf) {
        	return R.drawable.ic_rtf;
        }

        // WAV audio file
		if (listFileTypes == ListFileTypes.File_Sound) {
        	return R.drawable.ic_sound;
        } 
        
		// GIF file
		if (listFileTypes == ListFileTypes.File_Gif) {
        	return R.drawable.ic_gif;
        } 
        
		// Photo file
		//if (listFileTypes == ListFileTypes.File_Photo) {
        //	return R.drawable.ic_photo;
        //}
        
		// Text file
		if (listFileTypes == ListFileTypes.File_Txt) {
        	return R.drawable.ic_txt;
        } 
        
		// Video files
		if (listFileTypes == ListFileTypes.File_Video) {
        	return R.drawable.ic_video;
        }
        
		// APK files
		if (listFileTypes == ListFileTypes.File_Apk) {
        	return R.drawable.ic_apk;
        } 
        
		// Other File Type Or None
        else {
        	return R.drawable.ic_file;
        }
	}
	

	public Drawable getImageBitmap(String url)
	{
		// photo file
		return new ReadImageFile().ReadImageFromFile(url);
	}
	
    public ListFileTypes getImageFileType(String fileName)
	{
		String url = getExtention(fileName);
		
		// Word document
		if (url.equalsIgnoreCase(".doc") || url.equalsIgnoreCase(".docx")) {
			return ListFileTypes.File_Word;
        }
		// PDF file
		else if(url.equalsIgnoreCase(".pdf")){	
			return ListFileTypes.File_Pdf;
		}

        // Power-point file
        else if(url.equalsIgnoreCase(".ppt") || url.equalsIgnoreCase(".pptx")){
        	return ListFileTypes.File_Powerpoint;
        }
		
        // Excel file
        else if(url.equalsIgnoreCase(".xls") || url.equalsIgnoreCase(".xlsx")) {
        	return ListFileTypes.File_Excel;
        }
		
        // WAV audio file
        else if(url.equalsIgnoreCase(".zip") || url.equalsIgnoreCase(".rar")) {
        	return ListFileTypes.File_Zip;
        } 
        
		// RTF file
        else if(url.equalsIgnoreCase(".rtf")) {
        	return ListFileTypes.File_Rtf;
        }

        // WAV audio file
        else if(url.equalsIgnoreCase(".wav") || url.equalsIgnoreCase(".mp3")) {
        	return ListFileTypes.File_Sound;
        } 
        
		// GIF file
        else if(url.equalsIgnoreCase(".gif")) {
        	return ListFileTypes.File_Gif;
        } 
        
		// JPG file
        else if(url.equalsIgnoreCase(".jpg") || url.equalsIgnoreCase(".jpeg") || url.equalsIgnoreCase(".png")) {
        	return ListFileTypes.File_Photo;
        }
        
		// Text file
        else if(url.equalsIgnoreCase(".txt")) {
        	return ListFileTypes.File_Txt;
        } 
        
		// Video files
        else if(url.equalsIgnoreCase(".3gp") || url.equalsIgnoreCase(".mpg") || url.equalsIgnoreCase(".mpeg") || url.equalsIgnoreCase(".mpe") || url.equalsIgnoreCase(".mp4") || url.equalsIgnoreCase(".avi")) {
        	return ListFileTypes.File_Video;
        }
        
		// APK files
        else if(url.equalsIgnoreCase(".apk"))  {
        	return ListFileTypes.File_Apk;
        } 
        
		// Other File Type Or None
        else {
        	return ListFileTypes.File_None;
        }
	}

	public String getExtention(String name)
	{
		File f = new File(name);
		if((f.getName().lastIndexOf("."))>0)
			return f.getName().substring(f.getName().lastIndexOf("."));

		return "";
	}
}
