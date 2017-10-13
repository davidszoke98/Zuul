package com.zuul.game;

import java.util.*;
public class Game {

	public static void main(String[] args) {
		Commands commandline=new Commands();
		String input="";
		boolean finished=false;
		Scanner reader=new Scanner(System.in);
		String command="";
		System.out.print("Welcome to our game.\nYou can type in 'help' to show your available commands.\n");
		while(!finished)
		{
			System.out.print("> ");
			commandline.setCommand();
			if(commandline.getFirstCommand().equals("quit")||commandline.getFirstCommand().equals("exit"))
			{
				finished=true;
			}
			else System.out.println(commandline.getFirstCommand());
			/*{
				for(String a : commandline.getCommand())
				{
					command+=a;
				}
				System.out.println(command);
			}*/
		}
		System.out.println("Thank you for using our product!");
	}

}
