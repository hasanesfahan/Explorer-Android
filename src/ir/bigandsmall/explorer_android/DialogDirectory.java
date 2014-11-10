package ir.bigandsmall.explorer_android;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogDirectory extends Builder implements OnClickListener {

	
	private Context mContext;
	final FileSpecifications fsp;
	
	 public DialogDirectory(Context context,FileSpecifications Objectfsp , boolean lastSelected) 
	 {
	        super(context);
	        this.mContext = context;
	        this.fsp = Objectfsp;

	        
	        this.setTitle("Choose Action");

	        String action[];
	        
	        if(Objectfsp.getFlolderType() == ListFolderTypes.Up)
	        {
	        	action = context.getResources().getStringArray(R.array.DialogDirectoryUp);
	        }
	        else if(lastSelected)
	        {
	        	action = context.getResources().getStringArray(R.array.DialogDirectoryWidthPast);
	        }
	        else
	        	action = context.getResources().getStringArray(R.array.DialogDirectory);
	        
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
