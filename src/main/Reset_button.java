package main;

import java.awt.Graphics;

import abstracts.Clickie;

final class Reset_button extends Clickie implements Drawable
{
	//METHODS
	//constructors
	Reset_button() {super(0, 0, 120, 120);}

	//implementations
	@Override
	public void render(final Graphics graphics) {Asset_library.RENDER(graphics, Asset.RESET, bounds);}

	@Override
	public void click() {Game.RESET();}

	@Override
	public void press() {}
}
