package com.zuul.game;

import java.util.*;
public class Game 
{
	private ArrayList<Room> rooms;
	private Room currentRoom;
	public Game()
	{
		rooms=new ArrayList<Room>();
		Room room1=new Room("First room");
		Room room2=new Room("Second room");
		Room room3=new Room("Third room");
		Room room4=new Room("Fourth room");
		Room room5=new Room("Fifth room");
		
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);
		rooms.add(room5);
		
		room1.setExit("down", room2);
		room1.setExit("right", room3);
		
		room2.setExit("right",room4);
		room2.setExit("up", room1);
		
		room3.setExit("left", room1);
		room3.setExit("down", room4);
		
		room4.setExit("up", room3);
		room4.setExit("left", room2);
		room4.setExit("down", room5);
		
		room5.setExit("up", room4);
		currentRoom=room1;
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

}
