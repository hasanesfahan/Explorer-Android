package ir.bigandsmall.explorer_android;

public class IconType {
	
	String FileName ;
	
	public IconType(String FileName)
	{
		this.FileName = FileName;
	}
	
	public int getIconId()
	{

		if(getExtention().equalsIgnoreCase("pdf"))
			return R.drawable.ic_pdf;
		
		else if((getExtention().equalsIgnoreCase("doc")||getExtention().equalsIgnoreCase("docx")))
			return R.drawable.ic_word;
		
		else if((getExtention().equalsIgnoreCase("ppt"))||(getExtention().equalsIgnoreCase("pptx")))
			return R.drawable.ic_powerpoint;
		
		else if((getExtention().equalsIgnoreCase("xls"))||(getExtention().equalsIgnoreCase("xlsx")))
			return R.drawable.ic_excel;

		else if(getExtention().equalsIgnoreCase("txt"))
			return R.drawable.ic_txt;
 
		else 
			return R.drawable.ic_file;
	}

	public String getExtention()
	{
		if((FileName.lastIndexOf(".")-1)>0)
			return FileName.substring(FileName.lastIndexOf(".")+1);

		return "";
	}
	

}
