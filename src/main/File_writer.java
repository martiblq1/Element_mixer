package main;

import java.io.FileWriter;
import java.io.IOException;

final class File_writer
{
	//METHODS
	//static
	static void WRITE_TO_FILE(final String file, final String line)
	{
		try
		{
			final FileWriter writer = new FileWriter(file, true);
			writer.write("\n" + line);
		    writer.close();
		}
		catch (final IOException exception) {exception.printStackTrace();}
	}
	
	static void RESET_SPAWNERS_FILE()
	{
		try
		{
			final FileWriter writer = new FileWriter("res/unlocked_elements.txt");
			writer.write("fire");
			writer.write("\n" + "water");
			writer.write("\n" + "earth");
			writer.write("\n" + "air");
		    writer.close();
		}
		catch (final IOException exception) {exception.printStackTrace();}
	}
}
