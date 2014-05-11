package by.vsu.mf.simplegles20.core.util;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class BitmapLoader {
	public static Bitmap load(int resId, Context context) {
		Bitmap bitmap = null;
		InputStream is = context.getResources().openRawResource(resId);
		try {
			bitmap = BitmapFactory.decodeStream(is);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.e("OpenGL", "Load texture " + resId + " FAILED");
			}
		}
		return bitmap;
	}
}
