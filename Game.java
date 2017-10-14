package com.zuul.game;

import java.util.*;
public class Game 
{
	private ArrayList<Room> rooms;
	private Room currentRoom;
	private int turns=0;
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
			if(turns==20)
			{
				
			}
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
						turns++;
					}
					break;
				}
				case "look":
				{
					System.out.println(currentRoom.getDescription());
					break;
				}
				case "examine":
				{
					if(currentRoom.getItemNumber()>0)
					{
						System.out.println("You examined the room and found the following things:");
						for(Item a : currentRoom.getItems())
						{
							System.out.println(a.getName());
						}
					}
					else System.out.println("You found nothing in this room");
					turns++;
					break;
				}
				case "take":
				{
					if(currentRoom.getItemNumber()>0)
					{
						String itemName=commandline.getSecondCommand();
						Iterator<Item> itr = currentRoom.getItems().iterator();
						while(itr.hasNext())
						{
							Item item = itr.next();
							if(item.getName().equals(itemName))
							{
								player.addItem(item);
								itr.remove();
								turns++;
								System.out.printf("You took the %s\n",itemName);
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
							System.out.printf("%s\n",a.getLongDescription());
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
				
		Room entrance = new Room("Entrance","This is the entrance. You just docked your spaceship here and got off.\nThere are two doors. One to west and one to east.");
		Room escapePod = new Room("Escape pod","You reached your goal, this is the room that has the return spaceship.");
		
		Room storageRoom = new Room("Storage room","");
		Room airlock = new Room("Airlock","");
		Room lab = new Room("Laboratory","");
				
		Room livingQuarters = new Room("Living quarters","");
		Room controlCenter = new Room("Control center","");
		Room lifeSupportCenter = new Room("Life support center","This is the life support center.\nThere are three doors. One to east, one to north and one to west.");
				
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
				
		entrance.setExit("west", lifeSupportCenter);
		entrance.setExit("east", controlCenter);
				
		lifeSupportCenter.setExit("east", entrance);
		lifeSupportCenter.setExit("north", medBay);
		lifeSupportCenter.setExit("west", airlock);
				
		airlock.setExit("east", lifeSupportCenter);
		airlock.setExit("north", medBay);
		airlock.setExit("west", lab);
				
		lab.setExit("west", escapePod);
		lab.setExit("north", medBay);
		lab.setExit("east", airlock);
				
		escapePod.setExit("west", storageRoom);
		escapePod.setExit("east", lab);
				
		storageRoom.setExit("west", escapePod);
		storageRoom.setExit("south", medBay);
		storageRoom.setExit("east", livingQuarters);
				
		livingQuarters.setExit("west", storageRoom);
		livingQuarters.setExit("south", medBay);
		livingQuarters.setExit("east", controlCenter);
		
		controlCenter.setExit("east", entrance);
		controlCenter.setExit("south", medBay);
		controlCenter.setExit("west", livingQuarters);
				
		medBay.setExit("northwest", storageRoom);
		medBay.setExit("southwest", lab);
		medBay.setExit("north", livingQuarters);
		medBay.setExit("south", airlock);
		medBay.setExit("northeast", controlCenter);
		medBay.setExit("southeast", lifeSupportCenter);
		// End of creation of rooms -----------------------------------------
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
