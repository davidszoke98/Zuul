package com.zuul.game;

import java.util.*;
public class Game 
{
	public Game(){}
	public void play() 
	{
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
				}
			}
		}
		System.out.println("Thank you for using our product!");
	}

}
