package model;

import esper.config;
import events.MixerEvent;
import events.TemperatureSensorEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mixer extends Thread
{
    private int waterLevel = 100;
    private int coffeeLevel = 100;
    private int sugarLevel = 100;
    private int milkLevel = 100;

    private final Screen screen;
    
    public Mixer(Screen scr) 
    {       
        this.screen = scr;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getCoffeeLevel() {
        return coffeeLevel;
    }

    public void setCoffeeLevel(int coffeeLevel) {
        this.coffeeLevel = coffeeLevel;
    }

    public int getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(int sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public int getMilkLevel() {
        return milkLevel;
    }

    public void setMilkLevel(int milkLevel) {
        this.milkLevel = milkLevel;
    }
    
    public void addWater()
    {
        waterLevel -= 5;
        System.out.println("Water added");
        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Water added");
    }
    
    public void addSugar()
    {
        sugarLevel -= 5;
        System.out.println("Sugar added");
        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Sugar added");
    }
    
    public void addCoffee()
    {
        coffeeLevel -= 5;
        System.out.println("Coffee added");
        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Coffee added");
    }
    
    public void addMilk()
    {
        milkLevel -= 5;
        System.out.println("Milk added");
        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Milk added");
    }
    
    public void mix()
    {
        addWater();
        addCoffee();
        addSugar();
        addMilk();
        System.out.println("Mixing");
        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Mixing");
        screen.getDispenser().dispenseCoffee();
    }
    
    @Override
    public void run() 
    {
        while (true) 
        { 
            mix();
            
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mixer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            config.sendEvent(new MixerEvent(waterLevel, coffeeLevel, sugarLevel, milkLevel));
        }
    }
}
