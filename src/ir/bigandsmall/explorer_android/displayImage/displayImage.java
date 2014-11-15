package ir.bigandsmall.explorer_android.displayImage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class displayImage {

	enum ScalingLogic
	{
		FIT
	};
	
	public static Bitmap decodeFile(String pathName, int dstWidth, int dstHeight) {
	    Options options = new Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(pathName, options);
	    options.inJustDecodeBounds = false;
	    options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, dstWidth, dstHeight, ScalingLogic.FIT);
	    Bitmap unscaledBitmap = BitmapFactory.decodeFile(pathName, options);
	 
	    return unscaledBitmap;
	}
	
	
	 
	public static int calculateSampleSize(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
	    if (scalingLogic == ScalingLogic.FIT) {
	        final float srcAspect = (float)srcWidth / (float)srcHeight;
	        final float dstAspect = (float)dstWidth / (float)dstHeight;
	 
	        if (srcAspect > dstAspect) {
	            return srcWidth / dstWidth;
	        } else {
	            return srcHeight / dstHeight;
	        }
	    } else {
	        final float srcAspect = (float)srcWidth / (float)srcHeight;
	        final float dstAspect = (float)dstWidth / (float)dstHeight;
	 
	        if (srcAspect > dstAspect) {
	            return srcHeight / dstHeight;
	        } else {
	            return srcWidth / dstWidth;
	        }
	    }
	}
}
