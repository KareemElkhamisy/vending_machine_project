package model;

public class IngredientSensor 
{
    private int milkQuantity;
    private int waterQuantity;
    private int sugarQuantity;
    private int coffeeQuantity;

    private final Screen screen;
    
    public IngredientSensor(Screen scr) 
    {
        this.screen = scr;
    }
    
    public boolean detectQuantityOfIng(int mQ, int wQ, int sQ, int cQ)
    {
        if((mQ >= 5) &&  (wQ >= 5) && (sQ >= 5) && (cQ >= 5))
        {
            return true;
        }
        
        return false;
    }
    
    public void sendNotificationForEmptyIng()
    {
        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Sorry, insufficient ingredients.");
        System.out.println("Sorry, insufficient ingredients.");
    }

    public int getMilkQuantity() {
        return milkQuantity;
    }

    public void setMilkQuantity(int milkQuantity) {
        this.milkQuantity = milkQuantity;
    }

    public int getWaterQuantity() {
        return waterQuantity;
    }

    public void setWaterQuantity(int waterQuantity) {
        this.waterQuantity = waterQuantity;
    }

    public int getSugarQuantity() {
        return sugarQuantity;
    }

    public void setSugarQuantity(int sugarQuantity) {
        this.sugarQuantity = sugarQuantity;
    }

    public int getCoffeeQuantity() {
        return coffeeQuantity;
    }

    public void setCoffeeQuantity(int coffeeQuantity) {
        this.coffeeQuantity = coffeeQuantity;
    }
}
