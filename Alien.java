package com.zuul.game;

public class Alien {
	private int health;
	private int attack;
	
	public Alien(int health, int attack) {
		this.health = health;
		this.attack = attack;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getAttack() {
		return this.attack;
	}
}