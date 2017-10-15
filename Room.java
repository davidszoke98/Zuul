package com.zuul.game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private String name;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Alien> aliens;
    

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String description)
    {
    	this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.aliens = new ArrayList<>();
        
    }
    
    public void addAlien(Alien alien) {
    	this.aliens.add(alien);
    }
    
    public ArrayList<Alien> getAliens(){
    	return this.aliens;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room
     * (the one that was defined in the constructor).
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * @return The name of the room.
     */
    public String getName()
    {
    	return name;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
   /* public String getLongDescription()
    {
        String completeDescription = "You are " + description + ".\n" + ".\n" + "Items in a room";
        //return getExitString();
    }*/

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
   /* private String getExitString()
    {
        String returnString = "Exits:";
        HashSet<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }*/

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExits(String direction) 
    {
        return exits.get(direction);
    }
    
    /** Adds item to a room
     *  @param item the item that you want to add to the room
     */
    public void addItem(Item item)
    {
        items.add(item);
    }
    public void removeItem(Item item)
    {
    	int index = items.indexOf(item);
    	items.remove(index);
    }
    public int getItemNumber()
    {
    	return items.size();
    }
    public ArrayList<Item> getItems()
    {
    	return items;
    }
}

