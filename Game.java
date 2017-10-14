package com.zuul.game;

import java.util.*;
public class Game 
{
	private ArrayList<Room> rooms;
	private Room currentRoom;
	private Player player=new Player(100);
	public Game()
	{
		this.setupGame();
	}
	public void play() 
	{
		Commands commandline=new Commands();
		boolean finished=false;
		System.out.print("Space Jungle\u00AE 2017\nThis game is written by Nikola Velichkov, Piotr Gzubiczki and David Szoke.\nVersion 1.0.0\nYou can type 'help' to show your available commands.\n\n");
		System.out.println("You are an astronaut who just found a space station near Earth.");
		System.out.println("You are chased by aliens from Mars because you have kidnapped one of their children to examine it.");
		System.out.println("Your only way to get back on Earth is to dock to the space station and find a way to the return spaceship.\n");
		System.out.printf("You are in the %s\n",currentRoom.getName());
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
						System.out.printf("You entered the %s\n",currentRoom.getName());
					}
					break;
				}
				case "look":
				{
					System.out.println(currentRoom.getDescription());
					break;
				}
				case "take":
				{
					if(currentRoom.getItemNumber()>0)
					{
						String itemName=commandline.getSecondCommand();
						for(Item a : currentRoom.getItems())
						{
							if(a.getName().equals(itemName))
							{
								player.addItem(a);
							}
							else
							{
								System.out.printf("There is no %s in this room.\n",itemName);
							}
						}
					}
					else
					{
						System.out.println("This room doesn't have anything to take.");
					}
					break;
				}
				case "inventory":
				{
					if(player.getInventory().size()>0)
					{
						System.out.println("Your inventory contains the following:");
						for(Item a : player.getInventory())
						{
							System.out.println(a.getName());
						}
					}
					else System.out.println("Your inventory is empty.");
					break;
				}
				case "exit":
				{
					break;
				}
				default:
				{
					System.out.printf("I don't know how to %s.\n",commandline.getFirstCommand());
					break;
				}
			}
		}
		System.out.println("Thank you for using our product!");
	}
	/**
	 * This method will make new rooms and add some exits and items if necessary. This method
	 * is called in the constructor of the class.
	 * 
	 */
	public void setupGame()
	{
		// Start of creation of rooms ----------------------------------
		rooms = new ArrayList<Room>();
				
		Room entrance = new Room("Entrance","This is the entrance. You just docked your spaceship here and got off.\nThere are two doors. One to the left and one to the right.");
		Room escapePod = new Room("Escape pod","You reached your goal, this is the room that has the return spaceship.");
		
		Room storageRoom = new Room("Storage room","");
		Room airlock = new Room("Airlock","");
		Room lab = new Room("Laboratory","");
				
		Room livingQuarters = new Room("Living quarters","");
		Room controlCenter = new Room("Control center","");
		Room lifeSupportCenter = new Room("Life support center","This is the life support center. You can see some bandages laying around.");
				
		Room medBay = new Room("Medbay","");
				
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
		
		controlCenter.setExit("right", entrance);
		controlCenter.setExit("down", medBay);
		controlCenter.setExit("left", livingQuarters);
				
		medBay.setExit("up-left", storageRoom);
		medBay.setExit("down-left", lab);
		medBay.setExit("up", livingQuarters);
		medBay.setExit("down", airlock);
		medBay.setExit("up-right", controlCenter);
		medBay.setExit("down-right", lifeSupportCenter);
		// End of creation of rooms ------------------------------------
		Item bandage = new Item("bandage","Use it to heal yourself.",0.5,20);
		lifeSupportCenter.addItem(bandage);	
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
