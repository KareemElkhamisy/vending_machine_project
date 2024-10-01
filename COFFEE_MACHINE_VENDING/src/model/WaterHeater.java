package model;

import esper.config;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WaterHeater extends Thread
{
    private boolean isHeated = false;
    private boolean waterHeaterStatus = false; //false = off, true = on
    private final Screen screen;
    
    public WaterHeater(Screen scr) 
    {
        this.screen = scr;
    }
  
    public void heatWater()
    {
        waterHeaterStatus = true;  
//        screen.getTemperatureSensor().detectTemperatureChange();
        
        System.out.println("Water is heating");
        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Water is heating");
   
    }
    
    public boolean isIsHeated() {
        return isHeated;
    }

    public void setIsHeated(boolean isHeated) {
        this.isHeated = isHeated;
    }

    public boolean isWaterHeaterStatus() {
        return waterHeaterStatus;
    }

    public void setWaterHeaterStatus(boolean waterHeaterStatus) {
        this.waterHeaterStatus = waterHeaterStatus;
    }
    
    @Override
    public void run() 
    {
        while (true) 
        {
            System.out.println("Water heater is running");
            screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Water heater is running");
            screen.getTemperatureSensor().detectTemperatureChange();
                
            try
            {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaterHeater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
