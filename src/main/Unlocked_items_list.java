package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

final class Unlocked_items_list implements Drawable
{
	//FIELDS-----------------------------------------------
	//oracle classes
	private static final Font FONT = new Font("Arial", Font.PLAIN, 25);
	
	//data_types
	private static int found_elements = 0;
	
	//METHODS----------------------------------------------
	//constructors
	Unlocked_items_list() {found_elements = Game.GET_SPAWNERS_SIZE();}
	
	//implementations
	@Override
	public void render(final Graphics graphics)
	{
		graphics.setColor(Color.BLACK);
		graphics.setFont(FONT);
		graphics.drawString("Discovered elements: " + found_elements + "/28", 1408, 25);
	}
	
	//default
	static void RESET() {found_elements = 4;}
	static void INCREMENT() {found_elements++;}
}
