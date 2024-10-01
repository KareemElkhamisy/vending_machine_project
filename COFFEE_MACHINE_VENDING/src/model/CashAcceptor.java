package model;

import java.util.ArrayList;

public class CashAcceptor extends MoneyAcceptor
{
    private boolean stat;
    private final Screen screen;
    
    ArrayList<Integer> bills = new ArrayList<>();
    int price;
    


    public CashAcceptor(Screen screen) {
        this.screen = screen;
    }
    
    
    
    public boolean acceptBill()
    {
        setIsAccepted(true);
        calculateChange(price);
        
        return isIsAccepted();
    }

    public ArrayList<Integer> getBills() {
        return bills;
    }

    public boolean acceptPayment(ArrayList<Integer> bills, int price) 
    {
        this.bills = bills;
        this.price = price;
        stat = acceptBill();
        
        return stat;
    }
    
    public void calculateChange(int price)
    {
        int change = 0;
        int total = 0;
        
        for (int i = 0; i < bills.size(); i++)
        {
            total += bills.get(i);
        }
        
        if(total > price)
        {
            change = total - price;
        }
  
        String output = screen.getMoneyDispenser().dispenseChange(change);
        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n" + output);
    }

}
