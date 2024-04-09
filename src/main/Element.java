package main;

import java.awt.Graphics;
import java.awt.MouseInfo;

import abstracts.Clickie;

final class Element extends Clickie implements Drawable, Tickable
{
	//FIELDS-----------------------------------
	//custom classes
	private Asset type = null;
	
	//data types
	private boolean following_mouse = false;
	private byte width_from_press = 0, height_from_press = 0;
	
	//METHODS----------------------------------
	//constructors
	Element(final int x, final int y, final Asset type)
	{
		super(x, y, 80, 80);
		this.type = type;
	}
	
	//implementations
	@Override
	public void click()
	{
		following_mouse = false;
		
		if(Delete_button.IN_TRASH(super.bounds))
		{
			Game.DELETE_ELEMENT(this);
			return;
		}
		
		Game.CHECK_FOR_INTERSECTIONS(this);
	}
	
	@Override
	public void press()
	{
		Game.MOVE_ELEMENT_TO_TOP(this);
		width_from_press = (byte) (MouseInfo.getPointerInfo().getLocation().x - super.bounds.x);
		height_from_press = (byte) (MouseInfo.getPointerInfo().getLocation().y - super.bounds.y);
		following_mouse = true;
	}

	@Override
	public void render(final Graphics graphics)
	{
		if(Anouncment.GET_RENDER()) {return;}
		Asset_library.RENDER(graphics, type, super.bounds);
	}

	@Override
	public void tick()
	{
		if(!following_mouse) {return;}
		super.bounds.x = MouseInfo.getPointerInfo().getLocation().x - width_from_press;
		super.bounds.y = MouseInfo.getPointerInfo().getLocation().y - height_from_press;
	}
	
	//default
	boolean in_element(final Element element) {return element.bounds.intersects(super.bounds);}
	void change_element(final Asset asset) {type = asset;}
	
	//getters
	Asset get_asset() {return type;}
}
