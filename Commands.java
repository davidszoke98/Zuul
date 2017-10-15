package com.zuul.game;

import java.util.*;
/**
 * This class stores and makes the commands.
 * 
 * @author David Szoke
 * @version 2017.10.13
 */
public class Commands {
	private ArrayList<String> generalCommands = new ArrayList<String>();
	private Scanner reader=new Scanner(System.in);
	private String firstCommand="";
	private String secondCommand="";
	/**
	 * Constructor of the class.
	 * Adds items to generalCommands ArrayList.
	 */
	public Commands()
	{
		generalCommands.add("go - go to specific direction");
		generalCommands.add("quit - quit the game");
		generalCommands.add("take - take an item");
		generalCommands.add("help - bring up this menu");
		generalCommands.add("look - look over the room and get a description of it");
		generalCommands.add("use - use an item that you have in your inventory");
		generalCommands.add("inventory - check your inventory");
		generalCommands.add("examine - examine the room and search for items");
	}
	/**
	 * Reads the input from the user and stores it in variables firstCommand and secondCommand.
	 * The secondCommand variable is not necessary to have a value because we have one-word commands too.
	 */
	public void setCommand()
	{
		String input=reader.nextLine().trim().toLowerCase();
		String[] splitting = input.split(" ");
		if(splitting.length>1)
		{
			firstCommand=splitting[0];
			secondCommand=splitting[1];
		}
		else firstCommand=input;
		
	}
	public String getFirstCommand()
	{
		return firstCommand;
	}
	public String getSecondCommand()
	{
		return secondCommand;
	}
	public void getHelp()
	{
		for(String a : generalCommands)
		{
			System.out.println(a);
		}
	}
	
}
