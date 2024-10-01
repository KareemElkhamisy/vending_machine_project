package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CupSensor 
{
    private boolean isPresent;
    private final Screen screen;
    
    public CupSensor(Screen scr) 
    {
        isPresent = false;
        this.screen = scr;
//        screen.getGui().getjButton3().addActionListener(new cupSensorAction());
    }
    
//    class cupSensorAction implements ActionListener 
//    {        
//        @Override
//        public void actionPerformed(ActionEvent ae) 
//        {
//            detectCup();
//        }    
//    }

    public void detectCup()
    {
//        screen.getGui().getjLabel6().setVisible(true);
//        screen.getGui().getjButton4().setVisible(true);
        
        System.out.println("Cup is detected");
        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Cup is detected");
        isPresent = true;
        
        screen.getTemperatureSensor().start();
        screen.getWaterHeater().start();            
    }

    public boolean isIsPresent() {
        return isPresent;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }
}
