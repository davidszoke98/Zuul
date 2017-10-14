package com.zuul.game;

import java.util.*;
public class Game 
{
	private ArrayList<Room> rooms;
	private Room currentRoom;
	
	public Game()
	{
		this.setupGame();
	}
	
	public void play() 
	{
		System.out.println(currentRoom.getShortDescription());
		Commands commandline=new Commands();
		boolean finished=false;
		System.out.print("Welcome to our game.\nYou can type in 'help' to show your available commands.\n");
		while(!finished)
		{
			System.out.print("> ");
			commandline.setCommand();
			if(commandline.getFirstCommand().equals("quit")||commandline.getFirstCommand().equals("exit"))
			{
				finished=true;
			}
			switch(commandline.getFirstCommand())
			{
				case "help":
				{
					System.out.println("Here are the commands that you can use in this game:");
					commandline.getHelp();
					break;
				}
				case "go":
				{
					String direction=commandline.getSecondCommand();
					if(currentRoom.getExits(direction)==null)
					{
						System.out.println("I can't go there");
					}
					else
					{
						currentRoom = currentRoom.getExits(direction);
						System.out.println(currentRoom.getShortDescription());
					}
					break;
				}
				case "take":
				{
					
				}
			}
		}
		System.out.println("Thank you for using our product!");
	}
	
	/**
	 * This method creates the game settings (rooms, arrays filled with rooms, items in rooms and places the items in random order in random rooms
	 * with almost random stats. 
	 */
	public void setupGame() {
		// Start of creation of rooms ----------------------------------
		rooms = new ArrayList<Room>();
		
		Room entrance = new Room("Entrance");
		Room escapePod = new Room("Escape pod");
		
		Room storageRoom = new Room("");
		Room airlock = new Room("");
		Room lab = new Room("");
		
		Room livingQuarters = new Room("");
		Room controlCenter = new Room("");
		Room lifeSupportCenter = new Room("");
		
		Room medBay = new Room("");
		
		rooms.add(entrance);
		rooms.add(escapePod);
		
		rooms.add(storageRoom);
		rooms.add(airlock);
		rooms.add(lab);
		
		rooms.add(livingQuarters);
		rooms.add(controlCenter);
		rooms.add(lifeSupportCenter);
		
		rooms.add(medBay);
		
		entrance.setExit("left", lifeSupportCenter);
		entrance.setExit("right", controlCenter);
		
		lifeSupportCenter.setExit("right", entrance);
		lifeSupportCenter.setExit("up", medBay);
		lifeSupportCenter.setExit("left", airlock);
		
		airlock.setExit("right", lifeSupportCenter);
		airlock.setExit("up", medBay);
		airlock.setExit("right", lab);
		
		lab.setExit("left", escapePod);
		lab.setExit("up", medBay);
		lab.setExit("right", airlock);
		
		escapePod.setExit("left", storageRoom);
		escapePod.setExit("right", lab);
		
		storageRoom.setExit("left", escapePod);
		storageRoom.setExit("down", medBay);
		storageRoom.setExit("right", livingQuarters);
		
		livingQuarters.setExit("left", storageRoom);
		livingQuarters.setExit("down", medBay);
		livingQuarters.setExit("right", controlCenter);
		
		medBay.setExit("up-left", storageRoom);
		medBay.setExit("down-left", lab);
		medBay.setExit("up", livingQuarters);
		medBay.setExit("down", airlock);
		medBay.setExit("up-right", controlCenter);
		medBay.setExit("down-right", lifeSupportCenter);
		// End of creation of rooms ------------------------------------
		
		currentRoom = entrance;
		
		/**
		 * TODO
		 * make items with almost randomized stats
		 * place items in randomized rooms
		 * place a few healing items in medbay
		 * place a few attacking items in the storage room
		 */
		
	}

}
