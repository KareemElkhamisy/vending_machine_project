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
public class TemperatureSensorEvent 
{
    private final int temp;
    
    public TemperatureSensorEvent(int temp)
    {
        this.temp = temp;
    }
    
    public int getTemp() {
        return temp;
    }
}
