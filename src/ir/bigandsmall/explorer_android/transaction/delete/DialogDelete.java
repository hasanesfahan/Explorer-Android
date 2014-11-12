package ir.bigandsmall.explorer_android.transaction.delete;

import ir.bigandsmall.explorer_android.MainActivity;
import ir.bigandsmall.explorer_android.adapter.FileSpecifications;

import java.io.File;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

public class DialogDelete extends Builder {
	
	private Context mContext;
	final FileSpecifications fsp;
	
	 public DialogDelete(Context context,  FileSpecifications Fsp ) 
	 {
	       super(context);
	       this.mContext = context;
	       this.fsp = Fsp;

	        
	       this.setTitle("Remove Selected Files");
	       this.setMessage(Fsp.getName());

	        
	         
	       this.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
	            @Override
	            public void onClick(DialogInterface dialog, int which)
	            {
	            	File f = new File(fsp.getPath());
	            	
	            	if(f.isDirectory())
		        		   DeleteDirectorys.deleteDirectory(f);
	            	else
	            		f.delete();
		        	
	            	MainActivity mtemp = (MainActivity)mContext;
	            	mtemp.refreshList(true);
	            }
	        });
	        
	       this.setNegativeButton("No", null); 
	       
	        
	    }
		 
		 
	 
	}
	 