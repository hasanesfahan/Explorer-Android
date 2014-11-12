package ir.bigandsmall.explorer_android.icon;

import ir.bigandsmall.explorer_android.R;
import ir.bigandsmall.explorer_android.R.drawable;
import ir.bigandsmall.explorer_android.definitions.ListFolderTypes;

public class ImageFolder {

	
	public static int getImageId(ListFolderTypes listFolderTypes)
	{
		//Folder Full
		if (listFolderTypes == ListFolderTypes.Directory) {
			return R.drawable.ic_directory;
        }
		
		//Folder Up
		if (listFolderTypes == ListFolderTypes.Up) {
			return R.drawable.ic_up;
        }
		
		//Folder Empty
		//if (listFolderTypes == listFolderTypes.Empty) {
		return R.drawable.ic_directory;
        
	}
}
