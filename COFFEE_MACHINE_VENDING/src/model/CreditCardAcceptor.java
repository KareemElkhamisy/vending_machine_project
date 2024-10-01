package model;

public class CreditCardAcceptor extends MoneyAcceptor
{
    private boolean isValid;
    private boolean stat;
    private int price;
    private int amount;

    private final Screen screen;
   
    


    public CreditCardAcceptor(Screen screen) {
        this.screen = screen;
    }
    

    public boolean acceptPayment(int amountInVisa, int price)
    {
        this.price = price;
        this.amount = amountInVisa;
        stat = detectAuthenticationOfCard();
        
        return stat;
    }
    
    public boolean detectAuthenticationOfCard()
    {
        if(amount >= price)
        {
            isValid = true;
            deductAssignedPriceFromCard();
        }
        else
        {
            isValid = false;
            System.out.println("The amount in your card is insufficient.");
            screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n The amount in your card is insufficient.");
        }
        
        return isValid;
    }
    
    public void deductAssignedPriceFromCard()
    {
        if(isValid)
        {
            amount -= price;
            System.out.println(price + " L.E. has been deducted.");   
            screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n" + price + " L.E. has been deducted.");

        }
        
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
