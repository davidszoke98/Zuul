package com.zuul.game;

public class Battle{
	
	private Player player;
	private Alien alien;
	
	public Battle(Player player, Alien alien) {
		this.player = player;
		this.alien = alien;
	}
	
	public void startBattle() {
		playerRoll = Dice.roll(20);
		alienRoll = Dice.roll(20);
		
		playerOnTurn = true;
		
		if (playerRoll > alienRoll) {
			System.out.println("Player goes first");
		} else {
			playerOnTurn = false;
			System.out.println("Alien goes first");
		}
	}
	
	public void fight(Player player) {
		
	}
	
	public void fight(Alien alien) {
		
	}
}