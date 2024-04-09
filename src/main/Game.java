package main;

import java.util.ArrayList;
import java.util.HashMap;

final class Game implements Runnable
{
	//FIELDS--------------------------------------------------
	//custom classes
	private static final Anouncment ANOUNCMENT = new Anouncment();
	private static Unlocked_items_list unlocked_items_list = null;
	
	//oracle classes
	@SuppressWarnings("unused")
	private static final Window WINDOW = new Window();
	private static final ArrayList<Element> ELEMENTS_LIST = new ArrayList<Element>();
	private static final ArrayList<Spawner> SPAWNER_LIST = new ArrayList<Spawner>();
	private static final ArrayList<Tickable> TICKABLE_LIST = new ArrayList<Tickable>();
	private final Thread thread = new Thread(this);
	private static final HashMap<String, Asset> ELEMENTS_IDS = new HashMap<String, Asset>();
	
	//STATIC--------------------------------------------------
	static
	{
		final Close_button close_button = new Close_button();
		final Delete_button delete_button = new Delete_button();
		final Reset_button reset_button = new Reset_button();
		Window.ADD_DRAWABLE(close_button);
		Window.ADD_CLICKIE(close_button);
		Window.ADD_DRAWABLE(delete_button);
		Window.ADD_DRAWABLE(ANOUNCMENT);
		Window.ADD_CLICKIE(reset_button);
		Window.ADD_DRAWABLE(reset_button);
		TICKABLE_LIST.add(ANOUNCMENT);
		ELEMENTS_IDS.put("fire", Asset.FIRE);
		ELEMENTS_IDS.put("water", Asset.WATER);
		ELEMENTS_IDS.put("earth", Asset.EARTH);
		ELEMENTS_IDS.put("air", Asset.AIR);
		ELEMENTS_IDS.put("lava", Asset.LAVA);
		ELEMENTS_IDS.put("stone", Asset.STONE);
		ELEMENTS_IDS.put("mud", Asset.MUD);
		ELEMENTS_IDS.put("plant", Asset.PLANT);
		ELEMENTS_IDS.put("tree", Asset.TREE);
		ELEMENTS_IDS.put("wind", Asset.WIND);
		ELEMENTS_IDS.put("stick", Asset.STICK);
		ELEMENTS_IDS.put("pickaxe", Asset.PICKAXE);
		ELEMENTS_IDS.put("hill", Asset.HILL);
		ELEMENTS_IDS.put("mine", Asset.MINE);
		ELEMENTS_IDS.put("iron", Asset.IRON);
		ELEMENTS_IDS.put("axe", Asset.AXE);
		ELEMENTS_IDS.put("wood", Asset.WOOD);
		ELEMENTS_IDS.put("mountains", Asset.MOUNTAINS);
		ELEMENTS_IDS.put("bricks", Asset.BRICKS);
		ELEMENTS_IDS.put("house", Asset.HOUSE);
		ELEMENTS_IDS.put("village", Asset.VILLAGE);
		ELEMENTS_IDS.put("city", Asset.CITY);
		ELEMENTS_IDS.put("balkan_village", Asset.BALKAN_VILLAGE);
		ELEMENTS_IDS.put("lightning", Asset.LIGHTNING);
		ELEMENTS_IDS.put("person", Asset.PERSON);
		ELEMENTS_IDS.put("stupid_person", Asset.STUPID_PERSON);
		ELEMENTS_IDS.put("stupid_people", Asset.STUPID_PEOPLE);
		ELEMENTS_IDS.put("trojan", Asset.TROJAN);
		for(String id : File_reader.GET_LINES("res/unlocked_elements.txt")) {SPAWNER_LIST.add(new Spawner(ELEMENTS_IDS.get(id)));}
		unlocked_items_list = new Unlocked_items_list();
		Window.ADD_DRAWABLE(unlocked_items_list);
		
		for(Spawner spawner : SPAWNER_LIST)
		{
			Window.ADD_DRAWABLE(spawner);
			Window.ADD_CLICKIE(spawner);
		}
	}
	
	//METHODS-------------------------------------------------
	//constructors
	Game() {thread.start();}
	
	//private
	private static void RENDER() {Window.RENDER();}
	
	private static void TICK()
	{
		for(Tickable tickable : TICKABLE_LIST) {tickable.tick();}
	}
	
	//implementations
	@Override
	public void run()
	{
		double delta = 0;
		long now = 0;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		while(true)
		{
			now = System.nanoTime();
			delta += (now-lastTime)/16666666.66666667;
			timer += now-lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				TICK();
				RENDER();
				delta--;
			}
			
			if(timer >= 1000000000) {timer = 0;}
		}
	}
	
	//static
	static void MOVE_ELEMENT_TO_TOP(final Element element)
	{
		Window.MOVE_ELEMENT_TO_TOP_OF_CLICKIE(element);
		Window.MOVE_ELEMENT_TO_TOP_OF_DRAWABLE(element);
		TICKABLE_LIST.remove(element);
		TICKABLE_LIST.add(0, element);
	}
	
	static void ADD_ELEMENT(final Element element)
	{
		ELEMENTS_LIST.add(element);
		Window.ADD_CLICKIE(element);
		Window.ADD_DRAWABLE(element);
		TICKABLE_LIST.add(element);
	}
	
	static void DELETE_ELEMENT(final Element element)
	{
		ELEMENTS_LIST.remove(element);
		Window.DELETE_ELEMENT_FROM_CLICKIE(element);
		Window.DELETE_ELEMENT_FROM_DRAWABLE(element);
	}
	
	static void CHECK_FOR_INTERSECTIONS(final Element element)
	{
		for(Element element_from_list : ELEMENTS_LIST)
		{
			if(element_from_list == element) {continue;}
			
			if(element_from_list.in_element(element))
			{
				for(String line : File_reader.GET_LINES("res/recipies.txt"))
				{
					String first_element = "", second_element = "", result = "";
					char[] recipie = line.toCharArray();
					
					for(char character : recipie)
					{
						if(character == '+') {break;}
						first_element += character;
					}
					
					for(int index = first_element.length() + 1; index < recipie.length; index++)
					{
						if(recipie[index] == '=') {break;}
						second_element += recipie[index];
					}
					
					for(int index = first_element.length() + second_element.length() + 2; index < recipie.length; index++) {result += recipie[index];}
					
					if((ELEMENTS_IDS.get(first_element) == element.get_asset() && ELEMENTS_IDS.get(second_element) == element_from_list.get_asset()) || (ELEMENTS_IDS.get(second_element) == element.get_asset() && ELEMENTS_IDS.get(first_element) == element_from_list.get_asset()))
					{
						Asset type = ELEMENTS_IDS.get(result);
						boolean spawner_type_unlocked = false;
						element.change_element(type);
						DELETE_ELEMENT(element_from_list);
						
						for(Spawner spawner : SPAWNER_LIST)
						{
							if(spawner.get_type() == type)
							{
								spawner_type_unlocked = true;
								break;
							}
						}
						
						if(!spawner_type_unlocked)
						{
							Spawner spawner = new Spawner(type);
							SPAWNER_LIST.add(spawner);
							Window.ADD_DRAWABLE(spawner);
							Window.ADD_CLICKIE(spawner);
							File_writer.WRITE_TO_FILE("res/unlocked_elements.txt", result);
							Anouncment.DRAW(type);
							Unlocked_items_list.INCREMENT();
						}
						
						return;
					}
				}
			}
		}
	}

	static void SCROLL_SPAWNERS(final int y_offset)
	{
		if((SPAWNER_LIST.get(0).get_y() == 100 && y_offset > 0) || (SPAWNER_LIST.get(SPAWNER_LIST.size() - 1).get_y() <= 960 && y_offset < 0)) {return;}
		for(Spawner spawner : SPAWNER_LIST) {spawner.move_spawner(y_offset);}
	}
	
	static int GET_LAST_SPAWNER_Y()
	{
		if(SPAWNER_LIST.size() == 0) {return -20;}
		return SPAWNER_LIST.get(SPAWNER_LIST.size() - 1).get_y();
	}
	
	static void RESET()
	{
		File_writer.RESET_SPAWNERS_FILE();
		Unlocked_items_list.RESET();
		
		for(Spawner spawner : SPAWNER_LIST)
		{
			Window.DELETE_ELEMENT_FROM_DRAWABLE(spawner);
			Window.DELETE_ELEMENT_FROM_CLICKIE(spawner);
		}
		
		for(Element element : ELEMENTS_LIST)
		{
			Window.DELETE_ELEMENT_FROM_DRAWABLE(element);
			Window.DELETE_ELEMENT_FROM_CLICKIE(element);
			TICKABLE_LIST.remove(element);
		}
		
		SPAWNER_LIST.clear();
		ELEMENTS_LIST.clear();
		for(String id : File_reader.GET_LINES("res/unlocked_elements.txt")) {SPAWNER_LIST.add(new Spawner(ELEMENTS_IDS.get(id)));}
		
		for(Spawner spawner : SPAWNER_LIST)
		{
			Window.ADD_DRAWABLE(spawner);
			Window.ADD_CLICKIE(spawner);
		}
	}
	
	static int GET_SPAWNERS_SIZE() {return SPAWNER_LIST.size();}
}
