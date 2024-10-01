package model;

import java.util.ArrayList;
import java.util.Scanner;
import esper.config;
import events.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.*;

enum PaymentMethod{CASH, COIN, CREDIT};

public class Screen 
{
    //gui
    private CoffeeVendingMachineGUI gui;
    
    private boolean screenStatus = false;
    private boolean acceptance;
    private PaymentMethod paymentMethod;
    private int sugar;
    private int milk;
    private int water;
    private int coffee;
    private int price;
    private int amount = 1000;
    
    private IngredientSensor ing;
    private CashAcceptor cash;
    private CreditCardAcceptor credit;
    private CupSensor cup;
    private Mixer mix;
    private WaterHeater waterHeater;
    private TemperatureSensor temperatureSensor;
    private Dispenser dispenser;
    private MoneyDispenser moneyDispenser;
    private MoneyAcceptor moneyacceptor;
    
    private ArrayList<String> drinks = new ArrayList<>();
    private ArrayList<Integer> bills = new ArrayList<>();
    
    Scanner sc = new Scanner(System.in);
 
    public void initializeGUI()
    {
        gui = new CoffeeVendingMachineGUI();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);

        gui.getjButton1().addActionListener(new screenAction());
        gui.getjButton2().addActionListener(new chooseDrinkAction());
        gui.getjButton3().addActionListener(new choosePaymentAction());
        gui.getjButton4().addActionListener(new cupSensorAction());

        gui.getjRadioButton1().setVisible(false);
        gui.getjRadioButton2().setVisible(false);
        gui.getjRadioButton3().setVisible(false);
        gui.getjRadioButton4().setVisible(false);
        gui.getjRadioButton5().setVisible(false);
        gui.getjRadioButton6().setVisible(false);
        gui.getjRadioButton7().setVisible(false);
        gui.getjRadioButton8().setVisible(false);
        gui.getjRadioButton9().setVisible(false);
        gui.getjRadioButton10().setVisible(false);
        gui.getjRadioButton11().setVisible(false);
        gui.getjRadioButton12().setVisible(false);
        gui.getjRadioButton13().setVisible(false);
        gui.getjLabel2().setVisible(false);
        gui.getjLabel3().setVisible(false);
        gui.getjLabel4().setVisible(false);
        gui.getjTextArea1().setVisible(false);
        gui.getjScrollPane1().setVisible(false);
        gui.getjButton2().setVisible(false);
        gui.getjButton3().setVisible(false);
        gui.getjTextArea1().setEditable(false);
        gui.getjLabel6().setVisible(false);
        gui.getjButton4().setVisible(false);

    }
    
    
    public Screen() 
    {
        initializeGUI();
        cup = new CupSensor(this);
        ing =  new IngredientSensor(this);
        cash = new CashAcceptor(this);
        credit = new CreditCardAcceptor(this);
        mix = new Mixer(this);
        waterHeater = new WaterHeater(this);
        temperatureSensor = new TemperatureSensor(this);
        dispenser = new Dispenser(this);
        moneyDispenser = new MoneyDispenser();
        
        drinks.add("Cappuccino: 20 L.E.");
        drinks.add("Espresso: 15 L.E.");
        drinks.add("Americano: 15 L.E.");
        drinks.add("Macchiato: 25 L.E.");
        drinks.add("Latte: 20 L.E.");
        drinks.add("Mocha: 30 L.E.");
        drinks.add("Flat White: 25 L.E.");
        drinks.add("Turkish coffee: 15 L.E.");
        
    }
    
    class screenAction implements ActionListener 
    {        
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            setScreenStatus(true);
        }    
    }
    
    class chooseDrinkAction implements ActionListener 
    {        
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            placeOrder();
        }    
    }

    class choosePaymentAction implements ActionListener 
    {        
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            selectPaymentMethod();
        }    
    }
    
    class cupSensorAction implements ActionListener 
    {        
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            cup.detectCup();
        }    
    }

        
    public void setScreenStatus(boolean state) 
    {
        this.screenStatus = state;
           
        if(screenStatus)
        {
            gui.getjButton1().setEnabled(!screenStatus);
            displayChoices(screenStatus);
            
            gui.getjTextArea1().setVisible(true);
            gui.getjScrollPane1().setVisible(true);
            gui.getjButton2().setVisible(true);
        }
        
    }

    public MoneyDispenser getMoneyDispenser() {
        return moneyDispenser;
    }

    public WaterHeater getWaterHeater() {
        return waterHeater;
    }

    public IngredientSensor getIng() {
        return ing;
    }

    public CashAcceptor getCash() {
        return cash;
    }

    public CreditCardAcceptor getCredit() {
        return credit;
    }

    public CupSensor getCup() {
        return cup;
    }

    public Mixer getMix() {
        return mix;
    }

    public TemperatureSensor getTemperatureSensor() {
        return temperatureSensor;
    }

    public Dispenser getDispenser() {
        return dispenser;
    }

    public CoffeeVendingMachineGUI getGui() {
        return gui;
    }
 
    int choice;
    
    public void displayChoices(boolean state)
    {
        for(int i = 0; i < 8; i++)
        {
            System.out.println(i + ": " + drinks.get(i));
        }
        
        
        gui.getjRadioButton1().setVisible(state);
        gui.getjRadioButton2().setVisible(state);
        gui.getjRadioButton3().setVisible(state);
        gui.getjRadioButton4().setVisible(state);
        gui.getjRadioButton5().setVisible(state);
        gui.getjRadioButton6().setVisible(state);
        gui.getjRadioButton7().setVisible(state);
        gui.getjRadioButton8().setVisible(state);
        gui.getjRadioButton9().setVisible(state);
        gui.getjRadioButton10().setVisible(state);
        gui.getjRadioButton11().setVisible(state);
        
        gui.getjLabel2().setVisible(state);
        gui.getjLabel3().setVisible(state);
        
    }   

    public void placeOrder()
    { 
        int level = 0;
        
        coffee = mix.getCoffeeLevel();
        sugar = mix.getSugarLevel();
        water = mix.getWaterLevel();
        milk = mix.getMilkLevel();
        
//        System.out.println("Enter your choice: ");
//        choice = sc.nextInt();
//        System.out.println("Enter sugar level: 1, 2, 3");
//        int level = sc.nextInt();
        
        acceptance = ing.detectQuantityOfIng(milk, water, sugar, coffee);
        
        if(gui.getjRadioButton1().isSelected() && gui.getjRadioButton9().isSelected())
        {
            choice = 0;  
            level = 1;
            gui.getjTextArea1().setText("You chose Cappuccino with level 1 sugar");
        }
        else if(gui.getjRadioButton1().isSelected() && gui.getjRadioButton10().isSelected())
        {
            choice = 0;
            level =2; 
            gui.getjTextArea1().setText("You chose Cappuccino with level 2 sugar" );

        }
        else if(gui.getjRadioButton1().isSelected() && gui.getjRadioButton11().isSelected())
        {
            choice = 0;
            level =3; 
            gui.getjTextArea1().setText("You chose Cappuccino with level 3 sugar" );

        }
        else if(gui.getjRadioButton2().isSelected() && gui.getjRadioButton9().isSelected())
        {
            choice = 1;
            level =1; 
            gui.getjTextArea1().setText("You chose Espresso with level 1 sugar" );

        }
        else if(gui.getjRadioButton2().isSelected() && gui.getjRadioButton10().isSelected())
        {
            choice = 1;
            level =2; 
            gui.getjTextArea1().setText("You chose Espresso with level 2 sugar" );
            System.out.println("You chose Espresso with level 2 sugar");

        }
        else if(gui.getjRadioButton2().isSelected() && gui.getjRadioButton11().isSelected())
        {
            choice = 1;
            level =3; 
            gui.getjTextArea1().setText("You chose Espresso with level 3 sugar" );

        }
        else if(gui.getjRadioButton3().isSelected() && gui.getjRadioButton9().isSelected())
        {
            choice = 2;
            level =1; 
            gui.getjTextArea1().setText("You chose Americano with level 1 sugar" );

        } 
        else if(gui.getjRadioButton3().isSelected() && gui.getjRadioButton10().isSelected()){
            choice = 2;
            level =2; 
            gui.getjTextArea1().setText("You chose Americano with level 2 sugar" );
        }
        else if(gui.getjRadioButton3().isSelected() && gui.getjRadioButton11().isSelected()){
            choice = 2;
            level =3; 
            gui.getjTextArea1().setText("You chose Americano with level 3 sugar" );

        }
        else if(gui.getjRadioButton4().isSelected() && gui.getjRadioButton9().isSelected())
        {
            choice = 3;
            level = 1;
            gui.getjTextArea1().setText("You chose Macchiato with level 1 sugar" );

        }
        else if(gui.getjRadioButton4().isSelected() && gui.getjRadioButton10().isSelected())
        {
            choice = 3;
            level = 2;
            gui.getjTextArea1().setText("You chose Macchiato with level 2 sugar" );

        }
        else if(gui.getjRadioButton4().isSelected() && gui.getjRadioButton11().isSelected())
        {
            choice = 3;
            level = 3;
            gui.getjTextArea1().setText("You chose Macchiato with level 3 sugar" );

        }
        else if(gui.getjRadioButton5().isSelected() && gui.getjRadioButton9().isSelected())
        {
            choice = 4;
            level = 1;
            gui.getjTextArea1().setText("You chose Latte with level 1 sugar" );

        }
        else if(gui.getjRadioButton5().isSelected() && gui.getjRadioButton10().isSelected())
        {
            choice = 4;
            level = 2;
            gui.getjTextArea1().setText("You chose Latte with level 2 sugar" );

        }
        else if(gui.getjRadioButton5().isSelected() && gui.getjRadioButton11().isSelected())
        {
            choice = 4;
            level = 3;
            gui.getjTextArea1().setText("You chose Latte with level 3 sugar" );

        }
        else if(gui.getjRadioButton6().isSelected() && gui.getjRadioButton9().isSelected())
        {
            choice = 5;
            level = 1;
            gui.getjTextArea1().setText("You chose Mocha with level 1 sugar" );

        }
        else if(gui.getjRadioButton6().isSelected() && gui.getjRadioButton10().isSelected())
        {
            choice = 5;
            level = 2;
            gui.getjTextArea1().setText("You chose Mocha with level 2 sugar" );

        }
        else if(gui.getjRadioButton6().isSelected() && gui.getjRadioButton11().isSelected())
        {
            choice = 5;
            level = 3;
            gui.getjTextArea1().setText("You chose Mocha with level 3 sugar" );
        }
        else if(gui.getjRadioButton7().isSelected() && gui.getjRadioButton9().isSelected())
        {
            choice = 6;
            level = 1;
            gui.getjTextArea1().setText("You chose Flat White with level 1 sugar" );

        }
        else if(gui.getjRadioButton7().isSelected() && gui.getjRadioButton10().isSelected())
         {
                    choice = 6;
                    level = 2;
                    gui.getjTextArea1().setText("You chose Flat White with level 2 sugar" );

         }       
        else if(gui.getjRadioButton7().isSelected() && gui.getjRadioButton11().isSelected())
                {
                    choice = 6;
                    level = 3;
                    gui.getjTextArea1().setText("You chose Flat White with level 3 sugar" );

                }
        else if(gui.getjRadioButton8().isSelected() && gui.getjRadioButton9().isSelected())
                {
                    choice = 7;
                    level = 1;
                    gui.getjTextArea1().setText("You chose Turkish coffee with level 1 sugar" );

                }
        else if(gui.getjRadioButton8().isSelected() && gui.getjRadioButton10().isSelected())
                {
                    choice = 7;
                    level = 2;
                    gui.getjTextArea1().setText("You chose Turkish coffee with level 2 sugar" );
                }
        else if(gui.getjRadioButton8().isSelected() && gui.getjRadioButton11().isSelected())
                {
                    choice = 7;
                    level = 3;
                    gui.getjTextArea1().setText("You chose Turkish coffee with level 3 sugar" );
                }
        
        acceptOrder(choice, level);
    }
    
    public void acceptOrder(int drinkChoice, int sugarLevel)
    {
        switch(drinkChoice)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7: 
                if((sugarLevel == 1 || sugarLevel == 2 || sugarLevel == 3 || sugarLevel == 0))
                {
                    if(acceptance)
                    {
                        gui.getjTextArea1().setText(gui.getjTextArea1().getText() + "\n Drink is accepted");
                        System.out.println("Drink is accepted");
                        determinePrice(drinkChoice);
                    }
                    else
                    {
                        displayWarning();  
                        gui.getjTextArea1().setText(gui.getjTextArea1().getText() + "\n Drink is not available");
                        System.out.println("Drink is not available");
                        displayIngredientsStock();
                        System.exit(0);
                    }
                }
                else
                {
                    gui.getjTextArea1().setText(gui.getjTextArea1().getText() + "\n Wrong choice");
                    System.out.println("Wrong choice");
                    System.exit(0);
                }
                break;
            default: gui.getjTextArea1().setText(gui.getjTextArea1().getText() + "\n Wrong choice");
                System.out.println("Wrong choice");
            System.exit(0);
        }
        
        JFrame frame = new JFrame("JoptionPane Test");
        int result = JOptionPane.showConfirmDialog(null, "Do you want to change your order?");
        switch (result) 
        {
            case JOptionPane.YES_OPTION:
            System.out.println("Yes");
            changeOrder();
            break;
            
            case JOptionPane.NO_OPTION:
            System.out.println("No");
            break;
        }
    
    }
        
    public void determinePrice(int drinkChoice)
    {
        switch(drinkChoice)
        {
            case 0: price = 20;
            break;
            case 1: price = 15;
            break;
            case 2: price = 15;
            break;
            case 3: price = 25;
            break;
            case 4: price = 20;
            break;
            case 5: price = 30;
            break;
            case 6: price = 25;
            break;
            case 7: price = 15;
            break;
            
        }
        
        System.out.println("The price is: " + price);
        gui.getjTextArea1().setText(gui.getjTextArea1().getText() + "\n The price is: " + price);
        System.out.println("Select payment method:");
        System.out.println("1: Cash");
        System.out.println("2: Credit");
//        int pay = sc.nextInt();
//        if(pay == 1)
//        {
//            selectPaymentMethod(PaymentMethod.CASH);
//        }
//        else if (pay == 2)
//        {
//            selectPaymentMethod(PaymentMethod.CREDIT);
//        }
//        else
//        {
//            System.out.println("Wrong choice");
//            System.exit(0);
//        }

        gui.getjLabel4().setVisible(screenStatus);    
        gui.getjRadioButton12().setVisible(screenStatus);
        gui.getjRadioButton13().setVisible(screenStatus);
        gui.getjButton3().setVisible(screenStatus); 
        

    }
       
    final JFrame parent = new JFrame();
    
    public void selectPaymentMethod() 
    {
        boolean paymentAcceptance =  false;
        
        
        if(gui.getjRadioButton12().isSelected())
        {
            this.paymentMethod = PaymentMethod.CASH;
            System.out.println("Payemnt is cash");
        }
        else if(gui.getjRadioButton13().isSelected())
        {
            this.paymentMethod = PaymentMethod.CREDIT;
            System.out.println("Payemnt is credit");
        }
        
        if(paymentMethod == PaymentMethod.CASH)
        {
            int bill;
            int sum = 0;
            
            do
            {
                String inp = JOptionPane.showInputDialog(parent, "Enter cash bills 5, 10, or 20", null);
                bill = Integer.parseInt(inp);  
                
                bills.add(bill);
                
                sum += bill;
                    
            }while(sum < price);
            
            paymentAcceptance = cash.acceptPayment(bills, price);
        }
        else if(paymentMethod == PaymentMethod.CREDIT)
        {
            
            paymentAcceptance = credit.acceptPayment(amount, price);
        }
        
        if(paymentAcceptance)
        {
            gui.getjLabel6().setVisible(screenStatus);
            gui.getjButton4().setVisible(screenStatus);
//           cup.detectCup();
        }
        else
        {
            System.out.println("Payment is rejected.");
            determinePrice(choice);
        }
    }
    
    public void displayIngredientsStock()
    {
        coffee = mix.getCoffeeLevel();
        sugar = mix.getSugarLevel();
        water = mix.getWaterLevel();
        milk = mix.getMilkLevel();
        
        JOptionPane.showMessageDialog(null,"Coffee stock: " + coffee + "\n Water stock: " + water + "\n Sugar stock: " + sugar + "\n Milk stock: " + milk);
         
        System.out.println("****************************************************");
        System.out.println("Coffee stock: " + coffee);
        System.out.println("Water stock: " + water);
        System.out.println("Sugar stock: " + sugar);
        System.out.println("Milk stock: " + milk);
    }
    
    public void changeOrder()
    {
        displayChoices(screenStatus);
        gui.dispose();
        initializeGUI();

    }
    
    public void displayWarning()
    {
        ing.sendNotificationForEmptyIng();
    }

    public void tempSignal(int temp) throws InterruptedException 
    {
        if(temp <= 100)
        {
            System.out.println("The temp is: " + temp);
            gui.getjTextArea1().setText(gui.getjTextArea1().getText() + "\n The temp is: " + temp);
        }
        
    }
}
