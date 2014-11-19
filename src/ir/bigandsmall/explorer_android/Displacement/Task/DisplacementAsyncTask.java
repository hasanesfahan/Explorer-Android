package ir.bigandsmall.explorer_android.Displacement.Task;

import ir.bigandsmall.explorer_android.MainActivity;
import ir.bigandsmall.explorer_android.Displacement.CopyDirectorys;
import ir.bigandsmall.explorer_android.Displacement.Move.MoveDirectorys;
import ir.bigandsmall.explorer_android.dialog.DialogProgressBar;
import ir.bigandsmall.explorer_android.transaction.Size;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class DisplacementAsyncTask extends AsyncTask<File, String, String>
{
	private Activity ac;
	private DialogProgressBar dialogProgressBar;
	private AlertDialog alertDialog;
	private TaskOperation taskOperation; 
	private TypeOperation typeOperation ;
	private String Password ;
	private boolean IncorrectPassword = false;
	
	public DisplacementAsyncTask(Activity ac,TaskOperation taskOperation,TypeOperation typeOperation,String Password ) 
	{
		this.ac = ac;
		this.taskOperation = taskOperation;
		this.typeOperation = typeOperation;
		this.Password = Password;
		
		createDialog();
	}
	
	public void createDialog() 
	{
		if(TypeOperation.None == typeOperation)
		{
			if(TaskOperation.Copy == taskOperation)
				dialogProgressBar= new DialogProgressBar(ac,"Copying...",this) ;
			else if(TaskOperation.Cut == taskOperation)
				dialogProgressBar= new DialogProgressBar(ac,"Moving...",this) ;
		}
		
		alertDialog = dialogProgressBar.create();
		alertDialog.show();
	}
	


    @Override
    protected void onPreExecute() 
    {
        super.onPreExecute();
    }

    @Override
	protected void onPostExecute(String result) 
	{
		if(IncorrectPassword)
			Toast.makeText(ac, "Incorrect password!", Toast.LENGTH_LONG).show();
		
    	alertDialog.dismiss();
    	refreshPareentList();
	}
	
    
	private void refreshPareentList() 
	{
    	MainActivity mtemp = (MainActivity)ac;
    	mtemp.refreshList(true);
	}
	

	@Override
	protected String doInBackground(File... files)
	{
		dialogProgressBar.setSizeAllFiles(Size.getSize(files[0]));
		try {
			
			
			if(TypeOperation.None == typeOperation)
			{
				if(TaskOperation.Copy == taskOperation)
				{
					CopyDirectorys.copyDirectory(files[0],files[1],this);
				}
				else if(TaskOperation.Cut == taskOperation)
				{
					MoveDirectorys.moveDirectory(files[0],files[1],this);
				}
			}
				

		} catch (Exception e) {}
		
		return null;
	}
	
	public void onProgressUpdate(String... progress)
	{
		if(progress.length == 3)
		{
			dialogProgressBar.setPathFromAndTo(progress[0] , progress[1]);
			dialogProgressBar.setSizeCurentFile(Long.parseLong(progress[2]));
			
		}
		else
		{
			dialogProgressBar.setSizeCurentFilesLoaded(Long.parseLong(progress[0]));
		}
	}
	
	
	public void startUpdateProgress(String... progress)
	{
		publishProgress(progress);
	}

}
