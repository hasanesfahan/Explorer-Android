package ir.bigandsmall.explorer_android.dialog;

import ir.bigandsmall.explorer_android.FileSpecifications;
import ir.bigandsmall.explorer_android.ListFolderTypes;
import ir.bigandsmall.explorer_android.MainActivity;
import ir.bigandsmall.explorer_android.R;
import ir.bigandsmall.explorer_android.R.array;
import ir.bigandsmall.explorer_android.R.string;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogDirectory extends Builder implements OnClickListener {

	
	private Context mContext;
	final FileSpecifications fsp;
	final String action[];
	
	 public DialogDirectory(Context context,FileSpecifications Objectfsp , boolean lastSelected) 
	 {
	        super(context);
	        this.mContext = context;
	        this.fsp = Objectfsp;

	        
	        this.setTitle("Choose Action");

	        
	        if(Objectfsp.getFlolderType() == ListFolderTypes.Up)
	        	action = context.getResources().getStringArray(R.array.DialogDirectoryUp);
	        else if(lastSelected)
	        	action = context.getResources().getStringArray(R.array.DialogDirectoryWidthPast);
	        else
	        	action = context.getResources().getStringArray(R.array.DialogDirectory);
	        
	        this.setItems(action,this);
	        
    }

	@Override
	public void onClick(DialogInterface dialog, int which) 
	{
		MainActivity mTemp = (MainActivity)mContext;
		
		if(action[which].equalsIgnoreCase(mContext.getResources().getString(R.string.dialog_Delete)))
			mTemp.DeleteFile(fsp);
	   else if(action[which].equalsIgnoreCase(mContext.getResources().getString(R.string.dialog_Copy_To_Clipboard)))
	       mTemp.CopyFrom(fsp);
	   else if(action[which].equalsIgnoreCase(mContext.getResources().getString(R.string.dialog_Cut_To_Clipboard)))
	       mTemp.CutFrom(fsp);
	   else if(action[which].equalsIgnoreCase(mContext.getResources().getString(R.string.dialog_Past_From_Clipboard)))
	       mTemp.PastTo(fsp);
	   else if(action[which].equalsIgnoreCase(mContext.getResources().getString(R.string.dialog_Rename)))
	       mTemp.RenameFilesAndFolder(fsp);
	   else if(action[which].equalsIgnoreCase(mContext.getResources().getString(R.string.dialog_New_Folder)))
	       mTemp.createNewFolder(fsp.getPath());
	    
	}
}
