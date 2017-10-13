package com.zuul.game;

public class Item
{
    private String name;
    private String description;
    private double weight;
    private int power;
    
    public Item(String name, String description, int weight, int power)
    {
    	this.name = name;
        this.description = description;
        this.weight = weight;
        this.power = power;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getShortDescription()
    {
        return description;
    }
    
    public double getWeight()
    {
        return weight;
    }
    
    
    
    public String getLongDescription()
    {
        return "Item name: " + name + "\nDescription: " + description + "\nWeight: " + getWeight();
    }
}