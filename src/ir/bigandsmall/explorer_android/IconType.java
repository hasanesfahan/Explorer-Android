package ir.bigandsmall.explorer_android;

public class IconType {
	
	String FileName ;
	
	public IconType(String FileName)
	{
		this.FileName = FileName;
	}
	
	public int getIconId()
	{
		if(getExtention().equalsIgnoreCase(".asf"))
			return R.drawable.ic_secure;
		else 
			return R.drawable.ic_file;
				
	}
	
	
	public String getExtention()
	{
		if(FileName.lastIndexOf(".")>0)
			return FileName.substring(FileName.lastIndexOf("."));

		return "";
	}
	

}
