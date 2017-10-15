package com.zuul.game;

import java.util.Scanner;


/**
 * TODO
 * @author nikola
 *
 */
public class Battle{
	private Player player;
	private Alien alien;
	private boolean playing;
	//private final String[] commands = {"attack", "run"};
	private Scanner reader = new Scanner(System.in);
	
	public Battle(Player player, Alien alien) {
		this.player = player;
		this.alien = alien;
		
		this.playing = true;
	}
	
	public void startBattle() {
		// Introduction to battles
		System.out.println("A battle started");
		System.out.println("Possible commands:");
		System.out.println("attack - you try to attack the alien with your weapon/fists");
		System.out.println("run - you try to run from the battle to a nearby room");
		
		//Decide if the alien would go before you
		int playerRoll = Dice.roll(20);
		int alienRoll = Dice.roll(20);
		
		// If aliens are going to be before you (50/50) they attack you once and then it's player's turn
		if(alienRoll > playerRoll) {
			System.out.println("Aliens go first");
			this.alienDamage();
		}
		
		// Start of turn based battle
		while(playing) {
			System.out.println("Your turn!");
			System.out.print(">");
			String command = reader.nextLine().split(" ")[0]; // this makes the command variable take only the first word that the user has typed in
			
			if(command.equals("attack")) {
				// Simple attack from the player
				boolean alienKilled = this.playerDamage();
				if(alienKilled) {
					System.out.println("You have killed the alien!");
					break;
				}
				
			} else if(command.equals("run")) {
				// The player will try to run and if successful will break out of while loop
				boolean successful = this.playerRun();
				if(successful) {
					System.out.println("You got out successfuly, good job!");
					break;
				} else {
					System.out.println("You couldn't escape, the alien trips you over");
					continue;
				}
				
			} else {
				// Program doesn't understand the command, returns to top of while loop
				System.out.println("I'm sorry, I don't know this command, please try again");
				continue;
			}
			System.out.println("Alien attacks you");
			this.alienDamage();
		}
	}
	
	private boolean playerRun() {
		int roll = Dice.roll(15);
		if(roll > 12) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean playerDamage() {
		int damageDone = player.attack();
		System.out.println("You have done " + damageDone + " damage to the alien");
		boolean killed = alien.takeDamage(damageDone);
		return killed;
	}
	
	private void alienDamage() {
		int damageDone = alien.attack();
		System.out.println("You have taken " + damageDone + " damage");
		boolean isDead = player.takeDamage(damageDone);
		
		if(isDead) {
			System.out.println("You have died in battle");
			this.playing = false;
		}
		
	}
}