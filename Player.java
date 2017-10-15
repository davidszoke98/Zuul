package com.zuul.game;

import java.util.*;
import java.util.ArrayList;

/**
 * TODO
 * @author Nikola Velichkov
 *
 */
public class Player {
	private ArrayList<Item> inventory;
	private double maxWeight;
	private int currentHealth;
	private int maxHealth;
	
	
	public Player(int maxHealth) {
		inventory = new ArrayList<>();
		maxWeight = 40.00;
		this.maxHealth = maxHealth;
		this.currentHealth = 100;
		
	}
	
	// ITEMS SECTION ----------------------------------------------------------------------------------
	
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
	public void useItem(String itemName)
	{
		ArrayList<Item> inv = this.getInventory();
		if(inv.size()>0)
		{
			Iterator<Item> itr = inventory.iterator();
			while(itr.hasNext())
			{
				Item item = itr.next();
				if(item.getName().equals(itemName));
				{
					switch(itemName)
					{
						case "bandage": case "medicine":
						{
							int healingPower = item.getPower();
							if(currentHealth==maxHealth)
							{
								System.out.println("You don't need the bandage, you are on maximum health");
								break;
							}
							else if(this.currentHealth+healingPower>=maxHealth)
							{
								int healedBy=100-currentHealth;
								currentHealth=100;
								System.out.printf("You were healed by %d.\n",healedBy);
								itr.remove();
							}
							else 
							{
								currentHealth+=healingPower;
								System.out.printf("You were healed by %d.\n",healingPower);
								System.out.printf("Now you have %d health.\n",currentHealth);
								itr.remove();
							}
							if(currentHealth==maxHealth)
							{
								System.out.println("You are on maximum health.");
							}
							break;
						}
						default:
						{
							System.out.printf("You have no %s in your inventory.\n",itemName);
							break;
						}
					}
				}
			}
		}
		else System.out.println("You have no items in your inventory");
	}
	
	/**
<<<<<<< HEAD
	 * This method tries to remove an itemName (should be a bandage or another type) and if successful goes onto the healing itself
	 * 
	 * @param itemName the item name that should be used as a healing item
	 * @return A string if there are no bandages in the inventory
	 * @return A string if the heal was successful and the amount that was healed
	 */
	/**
=======
>>>>>>> baf3d8def18a09fdb4e3068d8d14a8a413132cd7
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
	
	// End of items section --------------------------------------------------------------------------
	
	
	// HEALTH SECTION --------------------------------------------------------------------------------
	/**
	 * This method makes the player take some damage and if the player has taken critical damage (makes the health less than 0) it returns a false
	 * to indicate it
	 * @param damage the amount of damage the player should take
	 * @return if the player takes critical damage it returns false indicating that the player has died
	 * @return if the player has more than 0 health, they are still alive, so returned true
	 */
	public void takeDamage(int damage) {
		this.currentHealth -= damage;
		if(checkIfDead()) {
			System.out.println("You have died");
			//Terminate program
		} else {
			System.out.println("You have " + this.currentHealth + " health left");
		}
	}
	
	public boolean checkIfDead() {
		if(this.currentHealth <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getCurrentHealth() {
		return this.currentHealth;
	}
	
	/**
	 * This method tries to remove an itemName (should be a bandage or another type) and if successful goes onto the healing itself
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
		if(this.currentHealth + item.getPower() <= this.maxHealth) {
			this.currentHealth += item.getPower();
			return "Healed by " + item.getPower();
		} else {
			this.currentHealth = this.maxHealth;
			return "Healed to maximum health";
		}
	}
	
	
	// End of health section -------------------------------------------------------------------------
	
	// Battle methods
	
	public int attack(int minimumRoll) {
		int roll = Dice.roll(20);
		if(minimumRoll > roll) {
			return 0;
		}
		
		return roll - minimumRoll;
	}
	

}
