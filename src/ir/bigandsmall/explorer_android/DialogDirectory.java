package ir.bigandsmall.explorer_android;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogDirectory extends Builder implements OnClickListener {

	
	private Context mContext;
	final FileSpecifications fsp;
	
	 public DialogDirectory(Context context,FileSpecifications Objectfsp) 
	 {
	        super(context);
	        this.mContext = context;
	        this.fsp = Objectfsp;

	        
	       this.setTitle("Choose Action");

	        String action[] = new String[6];
	        action[0] = "Rename";
	        action[1] = "Delete";
	        action[2] = "Copy To Clipboard";
	        action[3] = "Cut To Clipboard";
	        action[4] = "New Folder";
	        action[5] = "Past From Clipboard";
	        
	        this.setItems(action,this);
	        
    }

	@Override
	public void onClick(DialogInterface dialog, int which) 
	{
	   if(which == 0)
	   {
		    
	   }
	   else if(which == 1)
	   {
		 
	   }
	   else if(which == 2)
	   {
		   
	   }
	   else if(which == 3)
	   {
		   
	   }
	}
}
