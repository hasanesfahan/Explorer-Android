package ir.bigandsmall.explorer_android;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class DialogActionOnFolder extends Builder implements OnClickListener {

	
	private Context mContext;
	final FileSpecifications fsp;
	
	 public DialogActionOnFolder(Context context,FileSpecifications Objectfsp) 
	 {
	        super(context);
	        this.mContext = context;
	        this.fsp = Objectfsp;

	        
	       this.setTitle("Choose Action");

	        String action[] = new String[4];
	        action[0] = "Remove";
	        action[1] = "Copy To Clipboard";
	        action[2] = "Move To Clipboard";
	        action[3] = "Paste From Clipboard";
	        
	        this.setItems(action,this);
	        
    }
	 
	@Override
	public void onClick(DialogInterface dialog, int which) 
	{
	   if(which == 0)
	   {
		   MainActivity ma=(MainActivity)mContext;
		   ma.DeleteFile(fsp);
	   }
	   else if(which == 1)
	   {
		   MainActivity ma=(MainActivity)mContext;
		   ma.CopyFrom(fsp);
	   }
	   else if(which == 2)
	   {
		  // MainActivity ma=(MainActivity)mContext;
		  // ma.CopyFileTo(PathDirectory);
	   }
	   else if(which == 3)
	   {
		   MainActivity ma=(MainActivity)mContext;
		   ma.CopyTo(fsp);
	   }
	}
}
