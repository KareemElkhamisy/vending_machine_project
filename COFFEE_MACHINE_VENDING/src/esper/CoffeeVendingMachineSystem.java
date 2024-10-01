package esper;

import model.Screen;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class CoffeeVendingMachineSystem 
{
    public static void main(String[] args) 
    {
        Logger.getRootLogger().setLevel(Level.OFF);

        // Register events
        config.registerEvents();

        final Screen controller = new Screen();
        
//        controller.displayChoices(true);
        
        
        config.createStatement("select temp from TemperatureSensorEvent")
                .setSubscriber(new Object() {
                    public void update(int temp) throws InterruptedException {
                        controller.tempSignal(temp);
                    }
                });
        

        
    }
    
}
