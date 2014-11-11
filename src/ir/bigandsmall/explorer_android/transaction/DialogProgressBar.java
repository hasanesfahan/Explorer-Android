package ir.bigandsmall.explorer_android.transaction;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DialogProgressBar extends Builder {
	
	private Context mContext;
	
	
	private TextView  FromCaption ;
	private TextView  FromPath ;
	private TextView  ToCaption ;
	private TextView  ToPath ;
	private ProgressBar  SingleProgress ;
	private TextView  SingleProgressValue ;
	private ProgressBar  MainProgress ;
	private TextView  MainProgressValue ;
	
	
	
	 
	 public DialogProgressBar(Context context) 
	 {
	       super(context);
	       this.mContext = context;
	        
	       LinearLayout LL = new LinearLayout(mContext);
	       LL.setOrientation(LinearLayout.VERTICAL);
	       
	       FrameLayout.LayoutParams LLParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
	       LL.setLayoutParams(LLParams);
	      
	       
	       
	       FromCaption = new TextView(mContext);
	       FromCaption.setText("From :");
	       FromCaption.setPadding(20, 15, 20, 0);
	       FrameLayout.LayoutParams FromCaptionParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
	       FromCaption.setLayoutParams(FromCaptionParams);  
	       LL.addView(FromCaption);
	       
	       
	       FromPath = new TextView(mContext);
	       FromPath.setPadding(20, 0, 20, 15);
	       FromPath.setSingleLine(true);
	       FrameLayout.LayoutParams FromPathParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
	       FromPath.setLayoutParams(FromPathParams);  
	       LL.addView(FromPath);
	       
	       
	       ToCaption = new TextView(mContext);
	       ToCaption.setText("To :");
	       ToCaption.setPadding(20, 0, 20, 0);
	       FrameLayout.LayoutParams ToCaptionParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
	       ToCaption.setLayoutParams(ToCaptionParams);  
	       LL.addView(ToCaption);
	       
	       
	       ToPath = new TextView(mContext);
	       ToPath.setPadding(20, 0, 20, 15);
	       ToPath.setSingleLine(true);
	       FrameLayout.LayoutParams ToPathParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
	       ToPath.setLayoutParams(ToPathParams);  
	       LL.addView(ToPath);
	       
	       SingleProgress = new ProgressBar(mContext, null, android.R.style.Widget_ProgressBar_Horizontal);
	       SingleProgress.setProgressDrawable(mContext.getResources().getSystem().getDrawable(android.R.drawable.progress_horizontal));
	       SingleProgress.setMax(100);
	       SingleProgress.setPadding(20, 0, 20, 10);
	       SingleProgress.setProgress(50);
	       SingleProgress.setIndeterminate(false);
	       FrameLayout.LayoutParams SingleProgressParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
	       SingleProgress.setLayoutParams(SingleProgressParams);  
	       LL.addView(SingleProgress);
	       
	       
	       SingleProgressValue = new TextView(mContext);
	       SingleProgressValue.setPadding(20, 0, 20, 15);
	       SingleProgressValue.setSingleLine(true);
	       SingleProgressValue.setGravity(Gravity.CENTER);
	       FrameLayout.LayoutParams SingleProgressValueParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
	       SingleProgressValue.setLayoutParams(SingleProgressValueParams);  
	       LL.addView(SingleProgressValue);
	       
	       
	       MainProgress = new ProgressBar(mContext, null, android.R.style.Widget_ProgressBar_Horizontal);
	       MainProgress.setProgressDrawable(mContext.getResources().getSystem().getDrawable(android.R.drawable.progress_horizontal));
	       MainProgress.setMax(100);
	       MainProgress.setPadding(20, 0, 20, 10);
	       MainProgress.setProgress(50);
	       MainProgress.setIndeterminate(false);
	       FrameLayout.LayoutParams MainProgressParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
	       MainProgress.setLayoutParams(MainProgressParams);  
	       LL.addView(MainProgress);
	       
	       
	       MainProgressValue = new TextView(mContext);
	       MainProgressValue.setPadding(20, 0, 20, 15);
	       MainProgressValue.setGravity(Gravity.CENTER);
	       MainProgressValue.setSingleLine(true);
	       FrameLayout.LayoutParams MainProgressValueParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
	       MainProgressValue.setLayoutParams(MainProgressValueParams);  
	       LL.addView(MainProgressValue);


  
	      
	       
	       this.setView(LL);
	       
	       this.setTitle("Copying"); 
	       this.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
	            @Override
	            public void onClick(DialogInterface dialog, int which)
	            {
	            	 
	            }
	        });
	      
	    }
		 
	 	public void setSingleProgress(int val)
	 	{
	 		SingleProgress.setProgress(val);
	 		SingleProgressValue.setText(val+"%");
	 	}
	 	
	 	public void setPathFromAndTo(String src, String dst)
	 	{
	 		FromPath.setText(src);
	 		ToPath.setText(dst);
	 	}
	 
	}
	 