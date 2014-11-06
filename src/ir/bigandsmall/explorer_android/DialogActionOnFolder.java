package ir.bigandsmall.explorer_android;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

public class DialogActionOnFolder extends Builder {

	
	private Context mContext;
	final String PathDirectory;
	
	 public DialogActionOnFolder(Context context,String ObjectPath) 
	 {
	        super(context);
	        this.mContext = context;
	        this.PathDirectory = ObjectPath;

	        
	       this.setTitle("Choose Action");

	        String action[] = new String[3];
	        action[0] = "Remove";
	        action[1] = "Move";
	        action[2] = "Copy";

	         
	        this.setItems(action, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) 
	               {
	            	   if(which == 0)
	            	   {
	            		   MainActivity ma=(MainActivity)mContext;
	            		   ma.DeleteFile(PathDirectory);
	            	   }
	               }
	        });
	    }
	 
}
