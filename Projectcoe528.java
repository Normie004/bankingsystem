
package projectcoe528;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.geometry.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.*;


/**
 *
 * @author v2mathur
 */
public class Projectcoe528 extends Application {
    private static TextField TextfldUN = new TextField();
    private static TextField TextfldPass = new TextField();
    private Button btLogin = new Button("Login");
    private Button btClear = new Button("Clear");
    public static int cost = 0;
 
        public static void deletef(String name) throws FileNotFoundException, IOException {
        File file = new File(name);
        file = new File(name + ".txt");
        String f = file.getName();
        if(!f.equals("admin.txt")){
        if(file.delete()){          
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            throw new NullPointerException();
        } 
    }
        else{
           throw new NullPointerException(); 
        }}
            public static Customer read(String name, String pass) throws FileNotFoundException {
            Customer c = null;
            Account account = null;
            int b;
            File f2 = new File(name + ".txt");
            String userName = TextfldUN.getText();
            String password = TextfldPass.getText();
            Scanner read2 = new Scanner(f2);
            while(read2.hasNextLine()) {     
         if (read2.nextLine().equals(userName)) {
             if (read2.hasNextLine() && read2.nextLine().equals(password) && read2.nextLine().equals("Customer")) {
              b = parseInt(read2.nextLine());
              c = new Customer(name, pass, account, b);
             }
         }
        }
            return c;
        }
    public static void write(Profile a) throws FileNotFoundException, IOException {
        BufferedWriter out;;

            try {
                String user  = a.getUsername();
                File file = new File(user);
                file = new File(user + ".txt");
                if (file.exists() || file.getName().equals("admin.txt")){
                    throw new NullPointerException();
                }
                file.createNewFile();
                out = new BufferedWriter(new FileWriter(file, true));

                out.write("\n" + a.getUsername() + System.getProperty("line.separator"));
                out.write(a.getPass() + System.getProperty("line.separator"));
                out.write(a.getRole()+ System.getProperty("line.separator"));
                if (a.getRole().equals("Customer") ) {
                    Customer b = (Customer) a;
                    out.write(b.getBankAccount().getAmount()+ System.getProperty("line.separator"));
                }

                out.newLine();
                out.close();

            } catch (IOException e) {
                System.out.println("There was a problem writing to file:" + e);
            }
        

        
    }
   
    
        
    @SuppressWarnings("resource")
    @Override // Override the start method in the Application class
    public void start(final Stage primaryStage) {
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Username:"), 0, 0);
        gridPane.add(TextfldUN, 1, 0);
        gridPane.add(new Label("Password:"), 0, 1);
        gridPane.add(TextfldPass, 1, 1);
        gridPane.add(btLogin, 1, 3);
        gridPane.add(btClear, 1, 3);
 
      
        gridPane.setAlignment(Pos.CENTER);
        TextfldUN.setAlignment(Pos.BOTTOM_RIGHT);
        TextfldPass.setAlignment(Pos.BOTTOM_RIGHT);
 
        GridPane.setHalignment(btLogin, HPos.LEFT);
        GridPane.setHalignment(btClear, HPos.RIGHT);
 
        final Text text = new Text("               Welcome to the Bank!");
       
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        text.setTextAlignment(TextAlignment.CENTER);

        
        
        
        
        
        btClear.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    TextfldUN.clear();
            TextfldPass.clear();
                }
        }
        );   
         
        btLogin.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
            boolean grantAccess = false;
            boolean grantAccess2 = false;
        String userName = TextfldUN.getText();
        String password = TextfldPass.getText();
        File f = new File("admin.txt");
        File f2 = new File(TextfldUN.getText() + ".txt");

        try { 
        Scanner read = new Scanner(f); 
        Scanner read2 = new Scanner(f2);
        while(read.hasNextLine()) {     
         if (read.nextLine().equals(userName)) {
             if (read.hasNextLine() && read.nextLine().equals(password) && read.nextLine().equals("Manager")) {
              grantAccess=true;
              break;
             }
         }
        }
                while(read2.hasNextLine()) {     
         if (read2.nextLine().equals(userName)) {
             if (read2.hasNextLine() && read2.nextLine().equals(password) && read2.nextLine().equals("Customer")) {
              grantAccess2=true;
              break;
             }
         }
        }
        if(grantAccess) {
         primaryStage.close();
         
         Label label = new Label("Login Successful");
         VBox pane = new VBox(10);
         Button logout = new Button("Logout");
         Button addCustomer = new Button("Add Customer");
         Button deleteCustomer = new Button("Delete Customer");
         pane.setAlignment(Pos.CENTER);
         pane.getChildren().addAll(label,addCustomer,deleteCustomer,logout);
         
         Scene scene2 = new Scene(pane,600,500);
         
         final Stage newWindow = new Stage();
         newWindow.setTitle("Welcome " + TextfldUN.getText());
         newWindow.setScene(scene2);
         
         newWindow.show();
         
        logout.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    TextfldUN.clear();
                    TextfldPass.clear();
                    newWindow.close();
                    primaryStage.show();
                    text.setText("Logout Successful");
                }
        }
        );
                    deleteCustomer.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
          newWindow.close();   
         Label customer = new Label("Enter Customer Name: ");
         final TextField tf6 = new TextField();
         Button DeleteButton = new Button("Delete Customer");
         final Label deletion = new Label("Confirm Deletion: ");
         Button back = new Button("Return");
         tf6.setPrefWidth(60);
         tf6.setMaxWidth(150);
         VBox pane = new VBox(10);
         pane.setAlignment(Pos.CENTER);
         pane.getChildren().addAll(customer, tf6,deletion, DeleteButton,back);
         
         Scene scene7 = new Scene(pane,600,500);
         
         final Stage newWindow4 = new Stage();
         newWindow4.setTitle("Delete Customer");
         newWindow4.setScene(scene7);
         
         newWindow4.show();  
         
                  back.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    newWindow4.close();
                    newWindow.show();
                }
        }
        );
         
          DeleteButton.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                   String temp1 = null;
                    temp1 = tf6.getText();
                    tf6.clear();
                    deletion.setText("Customer Successfully Deleted");
                    try {
                        deletef(temp1);
                    deletion.setText("Customer Successfully Deleted");                        
                    } catch (NullPointerException ex) {                       
                        deletion.setText("Kindly input a Valid Username");
                    } catch (IOException ex) {
                        Logger.getLogger(Projectcoe528.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
        );        
                         }
        }
        );   
        
                    
         
           addCustomer.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
         newWindow.close();
         Label customer = new Label("Enter Customer Name: ");
         final TextField tf1 = new TextField();
         Label password = new Label("Enter Password: ");
         final TextField tf2 = new TextField();
         Label amount = new Label("Enter Account Opening Amount: ");
         final TextField tf3 = new TextField();
         final Label addition = new Label("Confirm Addition: ");
         final Label blank = new Label("");
         Button addbtn = new Button("Add Customer");
         Button back = new Button("Return");
         tf1.setPrefWidth(60);
         tf1.setMaxWidth(150);
         tf2.setPrefWidth(60);
         tf2.setMaxWidth(150);
         tf3.setPrefWidth(60);
         tf3.setMaxWidth(150);
         VBox pane = new VBox(10);
 
         pane.setAlignment(Pos.CENTER);
         pane.getChildren().addAll(customer, tf1, password, tf2, amount, tf3,addition, blank, addbtn, back);
         
         Scene scene6 = new Scene(pane,600,500);
         
         final Stage newWindow3 = new Stage();
         newWindow3.setTitle("Add Customer");
         newWindow3.setScene(scene6);
         
         newWindow3.show();                
         
         back.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    newWindow3.close();
                    newWindow.show();
                }
        }
        );
         
         addbtn.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                         String name, password1;
                int amount1;  
                name = null;
                password1 = null;
                amount1 = -1;
                Account account = null;
                String i = "";
         name = tf1.getText();
         password1 = tf2.getText();
         try{
         if(name.equals("")){
             throw new NumberFormatException();
         }
         amount1 = Integer.parseInt(tf3.getText());

         }catch (NumberFormatException ex){
           i = "Enter valid inputs";
           blank.setText(i);
           addition.setText("Confirm Addition: ");
         }
         tf1.clear();
         tf2.clear();
         tf3.clear();
         
         Customer cust  = new Customer(name,password1,account,amount1);
                    try {
                        if(amount1 > 99){
                        write(cust);
                        if(i.equals("")){
                        addition.setText("Successfully Added!");
                        blank.setText("");
                        }
                        if(i.equals("Enter valid input")){
                           blank.setText("");
                           addition.setText("Confirm Addition: ");
                        }
                    }
                        else{
                                       i = "Enter valid money, amount must be at least $100";
           blank.setText(i);
           addition.setText("Confirm Addition: ");
                        }
                    
                    } catch (NullPointerException ex1){
                    addition.setText("Confirm Addition: ");
                    blank.setText("Username has been taken, kindly change.");
                } 
                     catch (IOException ex2) {
                        Logger.getLogger(Projectcoe528.class.getName()).log(Level.SEVERE, null, ex2);
                    }
                             catch (NumberFormatException ex){
           i = "Enter valid inputs";
           blank.setText(i);
           addition.setText("Confirm Addition: ");
         }
                }}
        
        );     
                
                
                
                }
        }
        ); 
         
         
        }
        
         if(grantAccess2) {
         primaryStage.close();
         final Customer c1 = read(TextfldUN.getText(),TextfldPass.getText());
         final Label label = new Label("Login Successful");
         VBox pane = new VBox(10);
         Button logout = new Button("Logout");
         final TextField tf = new TextField();
         tf.setPrefWidth(60);
         tf.setMaxWidth(150);
       
         Button deposit = new Button("Deposit");
         final TextField tf2 = new TextField();
         tf2.setPrefWidth(60);
         tf2.setMaxWidth(150);
        
         Button withdraw = new Button("Withdraw");
         Button getbalance = new Button("Get Balance");
         Button onlinepurchase = new Button("Online Purchase");
         pane.setAlignment(Pos.CENTER);
         pane.getChildren().addAll(label,tf,deposit,tf2,withdraw,getbalance,onlinepurchase,logout);
                
         Scene scene2 = new Scene(pane,600,500);
         
         final Stage newWindow = new Stage();
         newWindow.setTitle("Welcome " + TextfldUN.getText());
         newWindow.setScene(scene2);
         
         newWindow.show();
                  
                  logout.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    TextfldUN.clear();
                    TextfldPass.clear();
                    newWindow.close();
                    primaryStage.show();
                }
        }
        );
                  getbalance.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    label.setText("Balance: " + c1.getBankAccount().getAmount() );
                }
                    
                });
                   deposit.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    try{
                        int i = Integer.parseInt(tf.getText());
                        c1.getBankAccount().deposit(i);
                        label.setText("Deposit Successful");
                        tf.clear();
                    }catch (NumberFormatException |NullPointerException ex){
                        label.setText("Please Enter Valid Deposit");
                        tf.clear();
                    }

                    
                }});                 
                  
                
                   withdraw.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    try{
                        int w = Integer.parseInt(tf2.getText());
                        c1.getBankAccount().withdraw(w);
                        label.setText("Withdrawal Successful");
                        tf2.clear();
                    }catch (NumberFormatException | NullPointerException ex){
                        label.setText("Please Enter Valid amount");
                        tf2.clear();
                    }

                    
                }});                        
                 onlinepurchase.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    newWindow.close();
         String a = null;
         if (c1.getBankAccount().getAmount() < 10000){
             a = "Silver: YOU WILL BE CHARGED 20 DOLLARS FOR PURCHASES.";
             cost +=20;
         }
         if (c1.getBankAccount().getAmount() >= 10000 && c1.getBankAccount().getAmount() < 20000){
             a = "Gold: YOU WILL BE CHARGED 10 DOLLARS FOR PURCHASES.";
             cost += 10;
         }
         if (c1.getBankAccount().getAmount() >= 20000){
             a = "Platinum: YOU WILL NOT BE CHARGED FOR PURCHASES.";
             cost +=0;
         }
         
         final Label label2 = new Label("Current Level: " + a);
         Label label3 = new Label("Louis Vuitton Cologne: $480");
         Label label4 = new Label("Zara Pants: $99");
         Label label5 = new Label("Chocolate $1");
         Label label6 = new Label("Confirm Purchase");
         final Label label7 = new Label("Order: \n");
         final Label label8 = new Label("Total Cost: " + cost);
         Button btn3 = new Button("Buy");
         Button btn4 = new Button("Buy");
         Button btn5 = new Button("Buy");
         Button btn6 = new Button("Proceed to Checkout");
         Button btn7 = new Button("Return");
         VBox pane = new VBox(10);

         pane.setAlignment(Pos.CENTER);
         pane.getChildren().addAll(label2,label3,btn3,label4,btn4,label5,btn5,label6,label7,label8,btn6,btn7);
         
         Scene scene2 = new Scene(pane,600,500);
         
         final Stage newWindow2 = new Stage();
         newWindow2.setTitle("Online Purchase");
         newWindow2.setScene(scene2);
         
         newWindow2.show();

                  btn7.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    cost = 0;
                    label.setText("Balance: " + c1.getBankAccount().getAmount() );
                    newWindow.show();
                    newWindow2.close();
                }
                    
                });
         btn3.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    label7.setText("Cost: ");
                    label7.setText(label7.getText()+ "Louis Vuitton Cologne: $480\n");
                    
                    cost += 480;
                    label8.setText("Total Cost: ");
                    label8.setText(label8.getText() + cost);
                }
                    
                });
         btn4.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    label7.setText("Cost: ");
                    label7.setText(label7.getText()+ "Zara Pants: $99\n");
                    cost += 99;
                    label8.setText("Total Cost: ");
                    label8.setText(label8.getText() + cost);
                }
                    
                });  
         btn5.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    label7.setText("Cost: ");
                    label7.setText(label7.getText()+ "Chocolate $1\n");
                    cost += 1;
                    label8.setText("Total Cost: ");
                    label8.setText(label8.getText() + cost);
                }
                    
                });  
         btn6.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    if(cost > c1.getBankAccount().getAmount()){
                    label8.setText("Not Enough Money in your Account");
                    label7.setText("Restart Order?");
                    cost = 0;
         if (c1.getBankAccount().getAmount() < 10000){
             cost +=20;
         }
         if (c1.getBankAccount().getAmount() >= 10000 && c1.getBankAccount().getAmount() < 20000){
             cost += 10;
         }
         if (c1.getBankAccount().getAmount() >= 20000){
             cost +=0;
         }

                    }
                    if(cost < 50){
                    label7.setText("Purchase must be at least $50");
                    }
                    if(cost >=50 && cost < c1.getBankAccount().getAmount()){
                    label8.setText("Total Cost: ");
                    
                    c1.getBankAccount().getLevel().onlinePurchase(c1.getBankAccount(),cost);
                    label7.setText("Purchase Successful. Money spent: $" + cost);
                    cost = 0;
                    String b = null;
      if (c1.getBankAccount().getAmount() < 10000){
            c1.getBankAccount().getLevel().changeState(c1.getBankAccount());
            b ="Silver: YOU WILL BE CHARGED 20 DOLLARS FOR PURCHASES.";
            cost += 20;
         }
         if (c1.getBankAccount().getAmount() >= 10000 && c1.getBankAccount().getAmount() < 20000){
            c1.getBankAccount().getLevel().changeState(c1.getBankAccount());
            b = "Gold: YOU WILL BE CHARGED 10 DOLLARS FOR PURCHASES.";
            cost += 10;
         }
         if (c1.getBankAccount().getAmount() >= 20000){
            c1.getBankAccount().getLevel().changeState(c1.getBankAccount());
            b = "Platinum: YOU WILL NOT BE CHARGED FOR PURCHASES.";

         }
         label2.setText("Current Level: " + b);
                }}
                    
                });           
                }
                    
                
                 }
                         );      
                   
                  
                  
        }
        else {text.setText("Invalid username or password");
        }
        } catch (FileNotFoundException e1) {
         text.setText("Invalid username or password");
         TextfldUN.clear();
         TextfldPass.clear();
        }
        }});
        
         
        VBox vbox = new VBox(2);
        vbox.getChildren().addAll(text, gridPane);
         
          
        Scene scene = new Scene(vbox, 600, 300);
        primaryStage.setTitle("Bank Account"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
        
        
        }
     
    
    public static void main(String[] args) {
        launch(args);
    }
}   
