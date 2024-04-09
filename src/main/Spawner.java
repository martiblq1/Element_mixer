package main;

import java.awt.Graphics;

import abstracts.Clickie;

final class Spawner extends Clickie implements Drawable
{
	//FIELDS----------------------------------------
	//custom classes
	private Asset type = null;
	
	//METHODS---------------------------------------
	//constructors
	Spawner(final Asset type)
	{
		super(1780, Game.GET_LAST_SPAWNER_Y() + 120, 80, 80);
		this.type = type;
	}

	//implementations
	@Override
	public void click() {}

	@Override
	public void press()
	{
		final Element element = new Element(super.bounds.x, super.bounds.y, type);
		Game.ADD_ELEMENT(element);
		Game.MOVE_ELEMENT_TO_TOP(element);
		element.press();
	}

	@Override
	public void render(final Graphics graphics) {Asset_library.RENDER(graphics, type, super.bounds);}
	
	//default
	void move_spawner(final int y_offset) {super.bounds.y += y_offset;}
	
	//getters
	Asset get_type() {return type;}
	int get_y() {return super.bounds.y;}
}
