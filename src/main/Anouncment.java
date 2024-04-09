package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

final class Anouncment implements Drawable, Tickable
{
	//FIELDS-----------------------------------------------
	//oracle classes
	private static final Rectangle BOUNDS = new Rectangle(760, 160, 400, 400);
	private static final Font FONT = new Font("Arial", Font.PLAIN, 60);
	
	//enums
	private static Asset type = null;
	
	//data types
	private static byte ticks_passed = 0;
	private static boolean render = false;
	
	//METHODS----------------------------------------------
	//implementations
	@Override
	public void render(final Graphics graphics)
	{
		if(!render) {return;}
		Asset_library.RENDER(graphics, type, BOUNDS);
		graphics.setColor(Color.BLACK);
		graphics.setFont(FONT);
		graphics.drawString(type.toString(), 860, 660);
	}
	
	@Override
	public void tick()
	{
		if(!render) {return;}
		ticks_passed++;
		
		if(ticks_passed == 100)
		{
			ticks_passed = 0;
			render = false;
		}
	}
	
	//default
	static void DRAW(final Asset type)
	{
		Anouncment.type = type;
		render = true;
	}
	
	//getters
	static boolean GET_RENDER() {return render;}
}
