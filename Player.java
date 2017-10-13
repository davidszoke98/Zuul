package com.zuul.game;

import java.util.ArrayList;

public class Player {
	private ArrayList<Item> inventory;
	private double maxWeight;
	private int currentHealth;
	private int maxHealth;
	
	
	public Player() {
		inventory = new ArrayList<>();
		maxWeight = 40.00 
	}
	
	public addItem(Item item) {
		inventory.add(item);
	}
	
	public getInventory() {
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
			if(item.getName.equals(itemName)) {
				inventory.remove(inventory.indexOf(item));
				return item;
			}
		}
		return null;
	}
	
	
	/**
	 * This method checks if the total weight of the current inventory and the weight of the new item that the player has picked up
	 * will be more than the maximum weight for the player. If the weight is more then it returns false, otherwise, it returns true
	 * 
	 * @param newItem This should be the item that the player is trying to pick up
	 * @return Returns true if the total weight with the new item is less than or equal to the maximum weight
	 * @return Returns false if the toatl weight with the new item is more than the maximum weight
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

}
