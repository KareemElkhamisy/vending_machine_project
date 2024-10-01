package model;

import esper.config;
import events.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TemperatureSensor extends Thread
{
    private int Temperature;
    private final Screen screen;
    
    
    
    public TemperatureSensor(Screen scr) 
    {
        Temperature = 10;
        this.screen = scr;
    }

    private int random(int min, int max) {
        
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    public void detectTemperatureChange() 
    {
        Temperature += random(5, 10);
        
       if(Temperature >= 100)
       {
           System.out.println("Water is heated");
           screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Water is heated");
           screen.getWaterHeater().setIsHeated(true);
           screen.getWaterHeater().setWaterHeaterStatus(false);
           screen.getMix().mix();
       }
       
    }

    public double getTemperature() {
        return Temperature;
    }

    public void setTemperature(int Temperature) {
        this.Temperature = Temperature;
    }
    
    public void idle() {
        if (Temperature > 20) {
            Temperature -= random(2, 5);
        } else {
            Temperature += random(2, 5);
        }
    }
    
    @Override
    public void run() 
    {
        while (true) 
        {
            
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TemperatureSensor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            config.sendEvent(new TemperatureSensorEvent(Temperature));
//            System.out.println("The temp is: " + Temperature);
            if(Temperature >= 100)
            {
                idle();
            }
        }
    }
}
