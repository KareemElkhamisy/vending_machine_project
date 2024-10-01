package model;

public class MoneyDispenser 
{
    private Screen screen;
    private Boolean isDispensed;

    public MoneyDispenser() {
    }
 
   public MoneyDispenser(Screen screen, Boolean isDispensed){
        this.screen= screen;
        this.isDispensed = isDispensed;
   }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Boolean getIsDispensed() {
        return isDispensed;

    }

    public void setIsDispensed(Boolean isDispensed) {
        this.isDispensed = isDispensed;
    }

    public String dispenseChange(int change)
    {
        String output = "Your change is: " + change;
        System.out.println("Your change is: " + change);
//        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Your change is: " + change);
        isDispensed = true;
        
        return output;
    }
    
}
