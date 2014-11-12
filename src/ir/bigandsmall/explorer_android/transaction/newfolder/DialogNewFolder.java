package ir.bigandsmall.explorer_android.transaction.newfolder;

import ir.bigandsmall.explorer_android.MainActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

public class DialogNewFolder extends Builder {
	
	private Context mContext;
	final String PathDirectory;
	
	 public DialogNewFolder(Context context,String Path) 
	 {
	       super(context);
	       this.mContext = context;
	       this.PathDirectory = Path;

	        
	       this.setTitle("New Folder");

	       final EditText text = new EditText(mContext);
	       text.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
	       text.setSingleLine(true);

	        
	       this.setView(text);

	         
	       this.setPositiveButton("Create", new DialogInterface.OnClickListener(){
	            @Override
	            public void onClick(DialogInterface dialog, int which)
	            {
	            	 final String name = text.getText().toString();
	            	 if(name.length() == 0)
	            		 return;
	            	 
	                 if(!NewFolder.createNewFolder(PathDirectory+"/"+name))
	                 	Toast.makeText(mContext, "cannot create folder here", Toast.LENGTH_SHORT).show();
	                 else
	                 {
	                	 MainActivity ma=(MainActivity)mContext;
	                	 ma.refreshList(true);
	                 }
	            }
	        });
	        
	       this.setNegativeButton("Cancel", null); 
	       
	        
	    }
	 
	 
 
}
