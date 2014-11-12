package ir.bigandsmall.explorer_android.icon;

import ir.bigandsmall.explorer_android.R;
import ir.bigandsmall.explorer_android.R.drawable;
import ir.bigandsmall.explorer_android.definitions.ListFileTypes;

import java.io.File;

import android.graphics.drawable.Drawable;


public class ImageFile {
	
	public static int getImageId(String fileName)
	{
		return getImageId(getImageFileType(fileName));
	}
	
	public static int getImageId(ListFileTypes listFileTypes)
	{
		// Word document
		if (listFileTypes == ListFileTypes.Word) {
			return R.drawable.ic_word;
        }
		
		// PDF file
		if (listFileTypes == ListFileTypes.Pdf) {	
			return R.drawable.ic_pdf;
		}

        // Power-point file
		if (listFileTypes == ListFileTypes.Powerpoint) {
        	return R.drawable.ic_powerpoint;
        }
		
        // Excel file
		if (listFileTypes == ListFileTypes.Excel) {
        	return R.drawable.ic_excel;
        }
		
        // WAV audio file
		if (listFileTypes == ListFileTypes.Zip) {
        	return R.drawable.ic_zip;
        } 
        
		// RTF file
		if (listFileTypes == ListFileTypes.Rtf) {
        	return R.drawable.ic_rtf;
        }

        // WAV audio file
		if (listFileTypes == ListFileTypes.Sound) {
        	return R.drawable.ic_sound;
        } 
        
		// GIF file
		if (listFileTypes == ListFileTypes.Gif) {
        	return R.drawable.ic_gif;
        } 
        
		// Photo file
		if (listFileTypes == ListFileTypes.Photo) {
        	return R.drawable.ic_photo;
        }
        
		// Text file
		if (listFileTypes == ListFileTypes.Txt) {
        	return R.drawable.ic_txt;
        } 
        
		// Video files
		if (listFileTypes == ListFileTypes.Video) {
        	return R.drawable.ic_video;
        }
        
		// APK files
		if (listFileTypes == ListFileTypes.Apk) {
        	return R.drawable.ic_apk;
        } 
        
		// Other File Type Or None
        else {
        	return R.drawable.ic_file;
        }
	}
	
	
	
    public static ListFileTypes getImageFileType(String fileName)
	{
		String url = getExtention(fileName);
		
		// Word document
		if (url.equalsIgnoreCase(".doc") || url.equalsIgnoreCase(".docx")) {
			return ListFileTypes.Word;
        }
		// PDF file
		else if(url.equalsIgnoreCase(".pdf")){	
			return ListFileTypes.Pdf;
		}

        // Power-point file
        else if(url.equalsIgnoreCase(".ppt") || url.equalsIgnoreCase(".pptx")){
        	return ListFileTypes.Powerpoint;
        }
		
        // Excel file
        else if(url.equalsIgnoreCase(".xls") || url.equalsIgnoreCase(".xlsx")) {
        	return ListFileTypes.Excel;
        }
		
        // WAV audio file
        else if(url.equalsIgnoreCase(".zip") || url.equalsIgnoreCase(".rar")) {
        	return ListFileTypes.Zip;
        } 
        
		// RTF file
        else if(url.equalsIgnoreCase(".rtf")) {
        	return ListFileTypes.Rtf;
        }

        // WAV audio file
        else if(url.equalsIgnoreCase(".wav") || url.equalsIgnoreCase(".mp3")) {
        	return ListFileTypes.Sound;
        } 
        
		// GIF file
        else if(url.equalsIgnoreCase(".gif")) {
        	return ListFileTypes.Gif;
        } 
        
		// JPG file
        else if(url.equalsIgnoreCase(".jpg") || url.equalsIgnoreCase(".jpeg") || url.equalsIgnoreCase(".png")) {
        	return ListFileTypes.Photo;
        }
        
		// Text file
        else if(url.equalsIgnoreCase(".txt")) {
        	return ListFileTypes.Txt;
        } 
        
		// Video files
        else if(url.equalsIgnoreCase(".3gp") || url.equalsIgnoreCase(".mpg") || url.equalsIgnoreCase(".mpeg") || url.equalsIgnoreCase(".mpe") || url.equalsIgnoreCase(".mp4") || url.equalsIgnoreCase(".avi")) {
        	return ListFileTypes.Video;
        }
        
		// APK files
        else if(url.equalsIgnoreCase(".apk"))  {
        	return ListFileTypes.Apk;
        } 
        
		// Other File Type Or None
        else {
        	return ListFileTypes.Other;
        }
	}

	public static String getExtention(String name)
	{
		File f = new File(name);
		if((f.getName().lastIndexOf("."))>0)
			return f.getName().substring(f.getName().lastIndexOf("."));

		return "";
	}
}
