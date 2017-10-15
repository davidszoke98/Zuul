package com.zuul.game;

public class Alien {
	private int health;
	private int attack;
	
	public Alien(int health, int attack) {
		this.health = health;
		this.attack = attack;
	}
	
	public boolean takeDamage(int damage) {
		this.health -= damage;
		
		if(this.health < 0) {
			return true;
		} else {
			System.out.println("Alien has " + this.health + " health left");
			return false;	
		}
	}
	
	public int attack() {
		int roll = Dice.roll(attack / 10 + 1);
		
		if(roll == 0) {
			return 0;
		}
		
		return roll * 10;
	}
}