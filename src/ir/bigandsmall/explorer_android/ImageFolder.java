package ir.bigandsmall.explorer_android;

public class ImageFolder {

	
	public int getImageId(ListFolderTypes listFolderTypes)
	{
		//Folder Full
		if (listFolderTypes == ListFolderTypes.Full) {
			return R.drawable.ic_directory;
        }
		
		//Folder Up
		if (listFolderTypes == ListFolderTypes.Up) {
			return R.drawable.ic_up;
        }
		
		//Folder Empty
		//if (listFolderTypes == listFolderTypes.Empty) {
		return R.drawable.ic_empty;
        
	}
}
