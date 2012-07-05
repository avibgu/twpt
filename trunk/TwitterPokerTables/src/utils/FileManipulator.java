package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.Vector;

public class FileManipulator {

	public static Vector<String> getFileContent(String pFileName) {

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(new File(pFileName));
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return readFromInputStream(fis);
	}

	public static Vector<String> readFromInputStream(InputStream pInputStream) {

		Vector<String> answer = new Vector<String>();

		InputStreamReader isr = null;
		BufferedReader br = null;

		try {

			isr = new InputStreamReader(pInputStream);
			br = new BufferedReader(isr);

			while (br.ready())
				answer.add(br.readLine());

			pInputStream.close();
			isr.close();
			br.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return answer;
	}

	public static void writeToFile(String pContent, String pFilename) {

		PrintWriter out = null;

		try {

			out = new PrintWriter(pFilename);
			out.write(pContent);
			out.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
