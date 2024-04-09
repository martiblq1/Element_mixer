package main;

import java.awt.Graphics;

import abstracts.Clickie;

final class Close_button extends Clickie implements Drawable
{
	//METHODS
	//constructors
	Close_button() {super(1840, 0, 80, 80);}

	//implementations
	@Override
	public void click() {System.exit(0);}

	@Override
	public void render(final Graphics graphics) {Asset_library.RENDER(graphics, Asset.CLOSE, super.bounds);}

	@Override
	public void press() {}
}
