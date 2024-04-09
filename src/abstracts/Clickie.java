package abstracts;

import java.awt.Rectangle;

public abstract class Clickie
{
	//FIELDS----------------------------------------------
	//oracle classes
	protected Rectangle bounds = null;
	
	//METHODS---------------------------------------------
	//constructors
	public Clickie(final int x, final int y, final int width, final int height) {bounds = new Rectangle(x, y, width, height);}
	
	//abstract
	public abstract void click();
	public abstract void press();
	
	//public
	public final boolean clicked(final int x, final int y) {return bounds.contains(x, y);}
	public final boolean pressed(final int x, final int y) {return bounds.contains(x, y);}
}
