package ir.bigandsmall.explorer_android;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogFile extends Builder implements OnClickListener {

	
	private Context mContext;
	final FileSpecifications fsp;
	final String action[];
	
	 public DialogFile(Context context,FileSpecifications Objectfsp) 
	 {
	        super(context);
	        this.mContext = context;
	        this.fsp = Objectfsp;

	        
	        this.setTitle("Choose Action");

	        action = context.getResources().getStringArray(R.array.DialogFile);
	        
	        this.setItems(action,this);
	        
    }
	 
	@Override
	public void onClick(DialogInterface dialog, int which) 
	{
		MainActivity mTemp = (MainActivity)mContext;
		
		if(action[which].equalsIgnoreCase(mContext.getResources().getString(R.string.dialog_Delete)))
		    mTemp.DeleteFile(fsp);
		else if(action[which].equalsIgnoreCase(mContext.getResources().getString(R.string.dialog_Rename)))
			mTemp.RenameFilesAndFolder(fsp);
		
	}
}
