package main;

import java.awt.Graphics;
import java.awt.Rectangle;

final class Delete_button implements Drawable
{
	//FIELDS----------------------------------------------
	//oracle classes
	private static final Rectangle BOUNDS = new Rectangle(0, 830, 250, 250);
	
	//METHODS---------------------------------------------
	//implementations
	@Override
	public void render(final Graphics graphics) {Asset_library.RENDER(graphics, Asset.DELETE, BOUNDS);}
	
	//default
	static boolean IN_TRASH(final Rectangle bounds) {return BOUNDS.intersects(bounds);}
}
