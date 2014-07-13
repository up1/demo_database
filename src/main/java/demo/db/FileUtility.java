package demo.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtility {

	@SuppressWarnings("finally")
	public static String read(File file) throws Exception {
		StringBuffer outputBuffer = new StringBuffer();
		BufferedReader br = null;
		try{
			String sCurrentLine;
			br = new BufferedReader(new FileReader(file));
			while ((sCurrentLine = br.readLine()) != null) {
				outputBuffer.append(sCurrentLine);
			}
 
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return outputBuffer.toString();
		}
 
	}

}
