package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

final class Asset_library
{
	//FIELDS----------------------------------------------------------------------------------------------
	//oracle classes
	private final static HashMap<Asset, BufferedImage> ASSETS = new HashMap<Asset, BufferedImage>();
	
	//data types
	private static boolean loaded = false;
	
	//STATIC----------------------------------------------------------------------------------------------
	static
	{
		try
		{
			ASSETS.put(Asset.CLOSE, ImageIO.read(new File("res/close.png")));
			ASSETS.put(Asset.FIRE, ImageIO.read(new File("res/fire.png")));
			ASSETS.put(Asset.WATER, ImageIO.read(new File("res/water.png")));
			ASSETS.put(Asset.EARTH, ImageIO.read(new File("res/earth.png")));
			ASSETS.put(Asset.AIR, ImageIO.read(new File("res/air.png")));
			ASSETS.put(Asset.DELETE, ImageIO.read(new File("res/delete.png")));
			ASSETS.put(Asset.LAVA, ImageIO.read(new File("res/lava.png")));
			ASSETS.put(Asset.STONE, ImageIO.read(new File("res/stone.png")));
			ASSETS.put(Asset.MUD, ImageIO.read(new File("res/mud.png")));
			ASSETS.put(Asset.PLANT, ImageIO.read(new File("res/plant.png")));
			ASSETS.put(Asset.TREE, ImageIO.read(new File("res/tree.png")));
			ASSETS.put(Asset.WIND, ImageIO.read(new File("res/wind.png")));
			ASSETS.put(Asset.STICK, ImageIO.read(new File("res/stick.png")));
			ASSETS.put(Asset.PICKAXE, ImageIO.read(new File("res/pickaxe.png")));
			ASSETS.put(Asset.HILL, ImageIO.read(new File("res/hill.png")));
			ASSETS.put(Asset.MINE, ImageIO.read(new File("res/mine.png")));
			ASSETS.put(Asset.IRON, ImageIO.read(new File("res/iron.png")));
			ASSETS.put(Asset.AXE, ImageIO.read(new File("res/axe.png")));
			ASSETS.put(Asset.WOOD, ImageIO.read(new File("res/wood.png")));
			ASSETS.put(Asset.MOUNTAINS, ImageIO.read(new File("res/mountains.png")));
			ASSETS.put(Asset.BRICKS, ImageIO.read(new File("res/bricks.png")));
			ASSETS.put(Asset.HOUSE, ImageIO.read(new File("res/house.png")));
			ASSETS.put(Asset.VILLAGE, ImageIO.read(new File("res/village.png")));
			ASSETS.put(Asset.CITY, ImageIO.read(new File("res/city.png")));
			ASSETS.put(Asset.BALKAN_VILLAGE, ImageIO.read(new File("res/balkan_village.png")));
			ASSETS.put(Asset.LIGHTNING, ImageIO.read(new File("res/lightning.png")));
			ASSETS.put(Asset.PERSON, ImageIO.read(new File("res/person.png")));
			ASSETS.put(Asset.STUPID_PERSON, ImageIO.read(new File("res/stupid_person.png")));
			ASSETS.put(Asset.STUPID_PEOPLE, ImageIO.read(new File("res/stupid_people.png")));
			ASSETS.put(Asset.TROJAN, ImageIO.read(new File("res/trojan.png")));
			ASSETS.put(Asset.RESET, ImageIO.read(new File("res/reset.png")));
			loaded = true;
		}
		catch(final IOException exception) {exception.printStackTrace();}
	}
	
	//METHODS---------------------------------------------------------------------------------------------
	//static
	static void RENDER(final Graphics graphics, final Asset asset, final Rectangle bounds) {graphics.drawImage(ASSETS.get(asset), bounds.x, bounds.y, bounds.width, bounds.height, null);}

	//getters
	static boolean GET_LOADED() {return loaded;}
}
