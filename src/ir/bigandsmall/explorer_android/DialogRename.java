package ir.bigandsmall.explorer_android;

import java.io.File;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

public class DialogRename extends Builder {
	
	private Context mContext;
	final FileSpecifications fsp;
	
	 public DialogRename(Context context,FileSpecifications Fsp) 
	 {
	       super(context);
	       this.mContext = context;
	       this.fsp = Fsp;

	        
	       this.setTitle("Enter New Name");

	       final EditText text = new EditText(mContext);
	       text.setSingleLine(true);

	        
	       this.setView(text);

	         
	       this.setPositiveButton("Create", new DialogInterface.OnClickListener(){
	            @Override
	            public void onClick(DialogInterface dialog, int which)
	            {
	            	 final String name = text.getText().toString();
	            	 
	            	 if(fsp.getType() == ListTypes.Folder)
	            	 {
	            		 File lastfilename = new File(fsp.getPath());
	            		 File newfilename = new File(fsp.getParentPath()+"/"+name);
	            		 lastfilename.renameTo(newfilename);
	            		 
	            	 }
	            	 
	            	  
	            	 Toast.makeText(mContext, fsp.getParentPath(), 1000).show();

	            	 MainActivity ma=(MainActivity)mContext;
                	 ma.refreshList(true);

	            }
	        });
	        
	       this.setNegativeButton("Cancel", null); 
	       
	        
	    }
	 
	 
 
}
