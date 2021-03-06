package ir.bigandsmall.explorer_android.transaction.rename;

import ir.bigandsmall.explorer_android.MainActivity;
import ir.bigandsmall.explorer_android.adapter.FileSpecifications;

import java.io.File;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
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
	       text.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
	       text.setText(Fsp.getName());
	        
	       this.setView(text);

	         
	       this.setPositiveButton("Rename", new DialogInterface.OnClickListener(){
	            @Override
	            public void onClick(DialogInterface dialog, int which)
	            {
	            	 final String name = text.getText().toString();
	            	 if(name.length() == 0)
	            		 return;
	            	 
            	 	 File lastfilename = new File(fsp.getPath());
            		 File newfilename = new File(fsp.getParentPath()+"/"+name);
            		 if(!lastfilename.renameTo(newfilename))
            			 Toast.makeText(mContext, "Unable to rename !", Toast.LENGTH_LONG).show();
            	 
	    
	            	 MainActivity ma=(MainActivity)mContext;
                	 ma.refreshList(true);

	            }
	        });
	        
	    }
 
}
