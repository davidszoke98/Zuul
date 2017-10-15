package com.zuul.game;

import java.util.*;
public class Game 
{
	private ArrayList<Room> rooms;
	private boolean locked;
	private boolean wasInLab;
	private Room currentRoom;
	private int turns=0;
	private Player player=new Player(100);
	public Game()
	{
		this.setupGame();
	}
	public void play() 
	{
		Commands commandLine=new Commands();
		boolean finished=false;
		System.out.print("Space Jungle\u00AE 2017\nThis game is written by Nikola Velichkov, Piotr Gzubiczki and David Szoke.\nVersion 1.0.0\nYou can type 'help' to show your available commands.\n\n");
		System.out.println("You are an astronaut who just found a space station near Earth.");
		System.out.println("You are chased by aliens from Mars because you have kidnapped one of their children to examine it.");
		System.out.println("Your only way to get back on Earth is to dock to the space station and find a way to the return spaceship.\n");
		System.out.printf("You are in the %s\n",currentRoom.getName());
		while(!finished)
		{
			if(currentRoom.getName().equals("Laboratory"))
			{
				wasInLab=true;
			}
			if(turns==6 && !locked)
			{
				System.out.println("The aliens just broke in.");
			}
			else if(locked && turns==15)
			{
				System.out.println("The aliens just broke in.");
			}
			if(player.getCurrentHealth()<=0)
			{
				System.out.println("You died. Game over");
				break;
			}
			if(currentRoom.getName().equals("Escape pod"))
			{
				System.out.println(currentRoom.getDescription());
				System.out.println("You won the game!");
				break;
			}
			System.out.print("> ");
			commandLine.setCommand();
			if(commandLine.getFirstCommand().equals("quit"))
			{
				finished=true;
			}
			switch(commandLine.getFirstCommand())
			{
				case "help":
				{
					System.out.println("Here are the commands that you can use in this game:");
					commandLine.getHelp();
					break;
				}
				case "go":
				{
					String direction=commandLine.getSecondCommand();
					if(currentRoom.getExits(direction)==null)
					{
						System.out.println("I can't go there");
					}
					else if(currentRoom.getName().equals("Laboratory") && direction.equals("north") && locked)
					{
						System.out.println("This door is locked due to an error in the system. Go to the Control center to fix it.");
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
					if(currentRoom.getName().equals("Control center") && wasInLab==true)
					{
						System.out.println("You are in the control center. There are three doors in this room: west, south, southwest.\nHere you can fix the laboratory's north door. Type in 'fix' to fix the door.");
					}
					else
					{
						System.out.println(currentRoom.getDescription());
					}
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
						String itemName=commandLine.getSecondCommand();
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
				case "quit":
				{
					break;
				}
				case "use":
				{
					String itemName = commandLine.getSecondCommand();
					player.useItem(itemName);
					turns++;
					break;
				}
				case "fix":
				{
					if(currentRoom.getName().equals("Control center"))
					{
						locked=false;
						turns++;
						System.out.println("You fixed the errors of the doors.");
					}
					else System.out.println("There is nothing to fix in this room.");
					break;
				}
				default:
				{
					System.out.printf("I don't know how to %s.\n",commandLine.getFirstCommand());
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
		locked=true;
		wasInLab=false;
		// Start of creation of rooms -----------------------------------
		rooms = new ArrayList<Room>();
				
		Room entrance = new Room("Entrance","This is the entrance. You just docked your spaceship here and got off.\nThere are two doors. One to north and one to south.");
		Room escapePod = new Room("Escape pod","You reached your goal, this is the room that has the return spaceship.");
		Room storageRoom = new Room("Storage room","You are in the storage room. Make sure you examine this place because here you might find very useful things.\nThere are two doors. One to east and one to southeast.");
		Room airlock = new Room("Airlock","You are in the airlock. There are three doors here. One to west, one to north and one to east.");
		Room lab = new Room("Laboratory","This is the laboratory. There are three doors. One to north, one to northeast and one to east.");
		Room livingQuarter = new Room("Living quarter","This is the living quarter. There are three doors: west, east and south.");
		Room controlCenter = new Room("Control center","You are in the control center.\nThere are three doors in this room: west, south, southwest");
		Room lifeSupportCenter = new Room("Life support center","This is the life support center.\nThere are three doors. One to west, one to northwest and one to north.");
		Room medBay = new Room("Medbay","This is the medbay. There are 6 doors.\nThe doors are in the following directions: northwest, north, northeast, southeast, south, southwest.");
				
		rooms.add(entrance);
		rooms.add(escapePod);	
		rooms.add(storageRoom);
		rooms.add(airlock);
		rooms.add(lab);	
		rooms.add(livingQuarter);
		rooms.add(controlCenter);
		rooms.add(lifeSupportCenter);
		rooms.add(medBay);
				
		entrance.setExit("south", lifeSupportCenter);
		entrance.setExit("north", controlCenter);
				
		lifeSupportCenter.setExit("north", entrance);
		lifeSupportCenter.setExit("northwest", medBay);
		lifeSupportCenter.setExit("west", airlock);
				
		airlock.setExit("east", lifeSupportCenter);
		airlock.setExit("north", medBay);
		airlock.setExit("west", lab);
				
		lab.setExit("north", escapePod);
		lab.setExit("northeast", medBay);
		lab.setExit("east", airlock);
				
		escapePod.setExit("south", lab);
				
		storageRoom.setExit("southeast", medBay);
		storageRoom.setExit("east", livingQuarter);
				
		livingQuarter.setExit("west", storageRoom);
		livingQuarter.setExit("south", medBay);
		livingQuarter.setExit("east", controlCenter);
		
		controlCenter.setExit("south", entrance);
		controlCenter.setExit("southwest", medBay);
		controlCenter.setExit("west", livingQuarter);
				
		medBay.setExit("northwest", storageRoom);
		medBay.setExit("southwest", lab);
		medBay.setExit("north", livingQuarter);
		medBay.setExit("south", airlock);
		medBay.setExit("northeast", controlCenter);
		medBay.setExit("southeast", lifeSupportCenter);
		// End of creation of rooms -------------------
		// Creating items --------------------------------------------------- 
		Item bandage = new Item("bandage","Use it to heal yourself.",0.5,20);
		Item medicine = new Item("medicine","Use it to heal yourself.",0.2,40);
		Item rifle = new Item("rifle","You can use this item in the battle.",3.6,20);
		Item laserGun = new Item("lasergun","A very powerful weapon. Use it carefully.",6.4,60);
		// End of creating items ---------------------------------------------------------------
		// Adding items to the rooms -----------------------------------------------------------
		int laserRarity = Dice.roll(10);
		if(laserRarity==4)
		{
			storageRoom.addItem(laserGun);
		}
		storageRoom.addItem(rifle);
		medBay.addItem(medicine);
		lifeSupportCenter.addItem(bandage);
		// End of adding items ------------
		// Set up current room 
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
