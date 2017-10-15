package com.zuul.game;
import java.util.Random;

/**
 * The dice class allows the game to be a little bit more randomized
 * It uses the java.util.Random package to get a random number
 * while the Dice class limits the minimum to 1 and every time you call the static method roll
 * you get to choose a maximum value (4, 6, 8, 12, 20 in most cases)
 *
 * @author Nikola Velichkov
 * @version 12 October 2017
 */
public class Dice
{
    private static Random random =  new Random();
    /**
     * This is the one and only method that this class implemetns, it rolls a dice from one to max and returns the result
     *
     * @param  max The maximum value that you can get from this roll (inclucive)
     * @return     A random number from 1 to max (inclusive)
     */
    public static int roll(int max)
    {
        return Dice.random.nextInt(max);
    }

}