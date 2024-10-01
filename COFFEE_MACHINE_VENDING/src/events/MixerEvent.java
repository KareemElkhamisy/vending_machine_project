/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author reyam alshini
 */
public class MixerEvent 
{
    private int waterLevel;
    private int coffeeLevel;
    private int sugarLevel;
    private int milkLevel;

    public MixerEvent(int waterLevel, int coffeeLevel, int sugarLevel, int milkLevel) {
        this.waterLevel = waterLevel;
        this.coffeeLevel = coffeeLevel;
        this.sugarLevel = sugarLevel;
        this.milkLevel = milkLevel;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public int getCoffeeLevel() {
        return coffeeLevel;
    }

    public int getSugarLevel() {
        return sugarLevel;
    }

    public int getMilkLevel() {
        return milkLevel;
    }
    
    

    
}
