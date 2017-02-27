package main;

import java.io.*;


/**
 * Created by roberttatoian on 2/23/17.
 */
public class FileManager {

	File output;
	FileWriter writer;
	PrintWriter printWriter;

	public FileManager (String name) {
		output = new File(name);

		try {
			writer = new FileWriter(output,true);
		}
		catch (IOException e) {
			e.printStackTrace();
		}


	}

	public void writeFile(int times){

		try {
			writer.write(Integer.toString(times));
			writer.write("\n");
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		try {
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
