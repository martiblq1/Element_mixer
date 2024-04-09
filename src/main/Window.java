package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import abstracts.Clickie;

@SuppressWarnings("serial")
final class Window extends JFrame
{
	//CLASSES--------------------------------------------------------
	private static class PANEL extends JPanel
	{
		//CLASSES-----------------------------------------------------
		private static class MOUSE implements MouseListener
		{
			//FIELDS-----------------------------------------------------------
			private static final ArrayList<Clickie> CLICKIE_LIST = new ArrayList<Clickie>();
			
			//METHODS----------------------------------------------------------
			//implementations
			@Override
			public void mouseClicked(final MouseEvent event) {}

			@Override
			public void mousePressed(final MouseEvent event)
			{
				for(Clickie clickie : CLICKIE_LIST)
				{
					if(clickie.pressed(event.getX(), event.getY()))
					{
						clickie.press();
						break;
					}
				}
			}

			@Override
			public void mouseReleased(final MouseEvent event)
			{
				for(Clickie clickie : CLICKIE_LIST)
				{
					if(clickie.clicked(event.getX(), event.getY()))
					{
						clickie.click();
						break;
					}
				}
			}

			@Override
			public void mouseEntered(final MouseEvent event) {}

			@Override
			public void mouseExited(final MouseEvent event) {}
			
			//static
			static void ADD_CLICKIE(final Clickie clickie) {CLICKIE_LIST.add(clickie);}
			static void DELETE_CLICKIE(final Clickie clickie) {CLICKIE_LIST.remove(clickie);}
			
			static void MOVE_ELEMENT_TO_TOP(final Clickie clickie)
			{
				CLICKIE_LIST.remove(clickie);
				CLICKIE_LIST.add(0, clickie);
			}
		}
		
		private static class WHEEL implements MouseWheelListener
		{
			//METHODS
			//implementations
			@Override
			public void mouseWheelMoved(final MouseWheelEvent event)
			{
				byte y_offset = 100;
				
				if(event.getWheelRotation() < 0)
					Game.SCROLL_SPAWNERS(y_offset);
				else
					Game.SCROLL_SPAWNERS(-y_offset);
			}
		}
		
		//FIELDS------------------------------------------------------
		//oracle classes
		private static final ArrayList<Drawable> DRAWABLE_LIST = new ArrayList<Drawable>();
		
		//METHODS---------------------------------------------
		//constructors
		PANEL()
		{
			this.setDoubleBuffered(true);
			this.setEnabled(true);
			this.setFocusable(true);
			this.setBounds(0, 0, 1920, 1080);
			this.setPreferredSize(new Dimension(1920, 1080));
			this.setVisible(true);
			this.addMouseListener(new MOUSE());
			this.addMouseWheelListener(new WHEEL());
		}
		
		//implementations
		@Override
		protected void paintComponent(final Graphics graphics)
		{
			if(!Asset_library.GET_LOADED()) {return;}
			super.paintComponent(graphics);
			graphics.setColor(new Color(200, 200, 200));
			graphics.fillRect(1720, 0, 200, 1080);
			for(Drawable drawable : DRAWABLE_LIST) {drawable.render(graphics);}
		}
		
		//static
		static void ADD_DRAWABLE(final Drawable drawable) {DRAWABLE_LIST.add(drawable);}
		static void ADD_CLICKIE(final Clickie clickie) {MOUSE.ADD_CLICKIE(clickie);}
		static void MOVE_ELEMENT_TO_TOP_OF_CLICKIE(final Clickie clickie) {MOUSE.MOVE_ELEMENT_TO_TOP(clickie);}
		static void DELETE_ELEMENT_FROM_CLICKIE(final Clickie clickie) {MOUSE.DELETE_CLICKIE(clickie);}
		static void DELETE_ELEMENT_FROM_DRAWABLE(final Drawable drawable) {DRAWABLE_LIST.remove(drawable);}
		
		static void MOVE_ELEMENT_TO_TOP_OF_DRAWABLE(final Drawable drawable)
		{
			DRAWABLE_LIST.remove(drawable);
			DRAWABLE_LIST.add(drawable);
		}
	}
	
	//FIELDS---------------------------------------------------------
	//custom classes
	private static final PANEL PANEL_ = new PANEL();
	
	//METHODS--------------------------------------------------------
	//constructors
	Window()
	{
		this.setUndecorated(true);
		this.setVisible(true);
		this.setTitle("Trojan elements");
		this.setLayout(new BorderLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(PANEL_);
		this.pack();
	}
	
	//static
	static void RENDER() {PANEL_.repaint();}
	static void ADD_DRAWABLE(final Drawable drawable) {PANEL.ADD_DRAWABLE(drawable);}
	static void ADD_CLICKIE(final Clickie clickie) {PANEL.ADD_CLICKIE(clickie);}
	static void MOVE_ELEMENT_TO_TOP_OF_DRAWABLE(final Drawable drawable) {PANEL.MOVE_ELEMENT_TO_TOP_OF_DRAWABLE(drawable);}
	static void MOVE_ELEMENT_TO_TOP_OF_CLICKIE(final Clickie clickie) {PANEL.MOVE_ELEMENT_TO_TOP_OF_CLICKIE(clickie);}
	static void DELETE_ELEMENT_FROM_DRAWABLE(final Drawable drawable) {PANEL.DELETE_ELEMENT_FROM_DRAWABLE(drawable);}
	static void DELETE_ELEMENT_FROM_CLICKIE(final Clickie clickie) {PANEL.DELETE_ELEMENT_FROM_CLICKIE(clickie);}
}
