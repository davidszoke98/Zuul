package com.zuul.game;

import java.util.ArrayList;

public class Player {
	private ArrayList<Item> inventory;
	private double maxWeight;
	private int currentHealth;
	private int maxHealth;
	
	
	public Player(int maxHealth) {
		inventory = new ArrayList<>();
		maxWeight = 40.00;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		
	}
	
	public void addItem(Item item) {
		inventory.add(item);
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	
	/**
	 * This method removes an item from the player's inventory. It can only remove one item per method call. With a search pattern,
	 * once the item name equals the parameter string, it removes the item from the players inventory and breaks out of the method
	 * and returns the item if needed for further use
	 * 
	 * @param itemName This should be the name of the item from the Item instance
	 * @return The item that was removed from the player's inventory
	 * @return If no such items in the inventory null is returned
	 */
	public Item removeItem(String itemName) {
		for (Item item : inventory) {
			if(item.getName().equals(itemName)) {
				inventory.remove(inventory.indexOf(item));
				return item;
			}
		}
		return null;
	}
	
	
	/**
	 * This method tryes to remove an itemName (should be a bandage or another type) and if successful goes onto the healing itself
	 * 
	 * @param itemName the item name that should be used as a healing item
	 * @return A string if there are no bandages in the inventory
	 * @return A string if the heal was successful and the amount that was healed
	 */
	public String heal(String itemName) {
		Item item = this.removeItem(itemName);
		if(item == null) {
			return "You don't have any bandages in your inventory";
		}
		this.currentHealth += 2; //TODO Fix this with a variable, depending on the item's fields etc.
		return "Healed by " + "HEAL AMOUNT HERE";
	}
		
	/**
	 * This method checks if the total weight of the current inventory and the weight of the new item that the player has picked up
	 * will be more than the maximum weight for the player. If the weight is more then it returns false, otherwise, it returns true
	 * 
	 * @param newItem This should be the item that the player is trying to pick up
	 * @return Returns true if the total weight with the new item is less than or equal to the maximum weight
	 * @return Returns false if the total weight with the new item is more than the maximum weight
	 */
	public boolean checkWeight(Item newItem) {
		double currentWeight = 0.0;
		
		for(Item i : this.inventory) {
			currentWeight += i.getWeight();
		}
		
		currentWeight += newItem.getWeight();
		
		if (currentWeight > this.maxWeight) {
			return false;
		}
		
		return true;
	}
	
	public void startBattle(Alien alien) {
		Battle battle = new Battle(this, alien);
	}
}
