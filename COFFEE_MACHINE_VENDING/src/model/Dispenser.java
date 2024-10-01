package model;

public class Dispenser 
{
    private boolean dispensed;

    private final Screen screen;
    
    public Dispenser(Screen scr) 
    {
        dispensed = false;
        this.screen = scr;
    }
    
    public boolean isDispensed() {
        return dispensed;
    }

    public void setDispensed(boolean dispensed) {
        this.dispensed = dispensed;
    }
    
    public void dispenseCoffee()
    {
        System.out.println("Enjoy your coffee");
        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Enjoy your coffee!");
        screen.displayIngredientsStock();
        screen.getTemperatureSensor().stop();
        screen.getWaterHeater().stop();
//        System.exit(0);
    }
}
