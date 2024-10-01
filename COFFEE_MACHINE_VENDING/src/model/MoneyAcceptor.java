package model;

public class MoneyAcceptor 
{
    private String status;
    private boolean isAccepted = false;
//    private final Screen screen;
//    
//    public MoneyAcceptor(Screen scr) 
//    {
//        this.screen = scr;
//    }

    public MoneyAcceptor() {
    }
    
    
    
    public void acceptPayment()
    {
        System.out.println("Payment is accepted");
//        screen.getGui().getjTextArea1().setText(screen.getGui().getjTextArea1().getText() + "\n Payment is accepted");
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

}
