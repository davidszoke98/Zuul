package com.zuul.game;

import java.util.*;

public class Commands {
	private ArrayList<String> generalCommands = new ArrayList<String>();
	private String command;
	private Scanner reader=new Scanner(System.in);
	private String firstCommand="";
	private String secondCommand="";
	public Commands()
	{
		generalCommands.add("go");
		generalCommands.add("quit");
		generalCommands.add("exit");
		generalCommands.add("take");
		generalCommands.add("fire");
		generalCommands.add("help");
		generalCommands.add("look");
		generalCommands.add("use");
	}
	/*public void setCommand(String firstWord, String secondWord)
	{
		String firstCommand="";
		String secondCommand="";
		while(firstCommand!="")
		{
			for(String item : generalCommands)
			{
				if(firstWord.trim().toLowerCase().equals(item))
				{
					firstCommand=firstWord.trim().toLowerCase();
				}
			}
		}
		while(secondCommand!="")
		{
			secondCommand=secondWord.trim().toLowerCase();
		}
		command=firstCommand + " " + secondCommand;
	}
	public String getCommand()
	{
		return command;
	}*/
	public void setCommand()
	{
		String input=reader.nextLine().trim().toLowerCase();
		String[] splitting = input.split(" ");
		firstCommand=splitting[0];
		if(firstCommand!="exit" || firstCommand!="quit")
		{
			secondCommand=splitting[1];
		}
		
	}
	public String getFirstCommand()
	{
		return firstCommand;
	}
	public String getSecondCommand()
	{
		return secondCommand;
	}
}
