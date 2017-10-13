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
	public ArrayList<String> getCommandList()
	{
		return generalCommands;
	}
	public void getHelp()
	{
		for(String a : generalCommands)
		{
			System.out.println(a);
		}
	}
	
}
