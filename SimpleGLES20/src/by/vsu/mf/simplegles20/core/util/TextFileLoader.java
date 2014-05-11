package by.vsu.mf.simplegles20.core.util;

import java.io.InputStreamReader;
import java.util.Scanner;

import android.content.Context;

public class TextFileLoader {
	
	public static String load(int resId, Context context){
        InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
        Scanner scanner = new Scanner(in);
        StringBuilder file = new StringBuilder();
        while(scanner.hasNextLine()){
            file.append(scanner.nextLine()).append('\n');
        }
        scanner.close();
        return file.toString();
    }
}
