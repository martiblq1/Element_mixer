package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

final class File_reader
{
	//METHODS
	//static
	static ArrayList<String> GET_LINES(final String path)
	{
		try
		{
			final File file = new File(path);
			final Scanner scanner = new Scanner(file);
			final ArrayList<String> lines = new ArrayList<String>();
			while(scanner.hasNextLine()) {lines.add(scanner.nextLine());}
			scanner.close();
			return lines;
		}
		catch (final FileNotFoundException exception) {exception.printStackTrace();}
		
		return null;
	}
}
