package ir.bigandsmall.explorer_android;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogFile extends Builder implements OnClickListener {

	
	private Context mContext;
	final FileSpecifications fsp;
	
	 public DialogFile(Context context,FileSpecifications Objectfsp) 
	 {
	        super(context);
	        this.mContext = context;
	        this.fsp = Objectfsp;

	        
	        this.setTitle("Choose Action");

	        String action[] = new String[4];
	        
	        
	        action[0] = "Rename";
	        action[1] = "Delete";
	        action[2] = "Copy To Clipboard";
	        action[3] = "Cut To Clipboard";
	        
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
