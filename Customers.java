import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Customers{
    /*Customer Class for customers
    Customer can perform one of the following:
1----Withdraw Cash
2----Cash Transfer
3----Deposit Cash
4----Display Balance
5----Exit
*/
//    Administrator admin;
    static int accountNumber;
    static String date;
    static double rupees;
    double maxAmount=20000;
    public Customers(){
//        admin=new Administrator();
        Customers.accountNumber=Administrator.accountNumber;
        Customers.date=Administrator.date;
        //this is total balance provided by admin
        Customers.rupees=Administrator.balance;
    }

    //maxAmount is amount a user can withdraw in one day
    Scanner scan=new Scanner(System.in);
    //Scanner class to get input from user


    public void displayData(){
        //Display method to display options to user

        for (int i = 0; i < 30; i++) System.out.println();
        System.out.println("CUSTOMER MENU\n");
        System.out.println("1----Withdraw Cash");
        System.out.println("2----Cash Transfer");
//        System.out.println("3----Deposit Cash");
        System.out.println("3----Display Balance");
        System.out.println("4----Back\n");
        System.out.println("Please select one of the following options: ");

            //We take string because user may encounter any character
            String b=scan.nextLine();
            for(int i=0; i<30;i++) System.out.println();
        switch (b) {
            case "1" -> withdraw();
            case "2" -> transfer();
//            case "3" -> deposit();
            case "3" -> displayBalance();
            case "4" -> ATM_Machine.main(null);
            default -> {
                System.out.println("                                *** Please enter correct input!!  ***" +
                        "\n-----------------------------------------------------------------------------------------------------------------------------");
                displayData();
            }
        }
    }
    public void withdraw(){
        System.out.println("                                     1----Withdraw Cash\n");
        System.out.println("NOTE: A single account holder cannot withdraw more then 20,000 in one day");
        System.out.println("        a)Fast Cash\n        b)Normal Cash\n        c)Back\nPlease select a mode of withdrawal");
        String s=scan.nextLine();
        for (int i = 0; i < 30; i++) System.out.println();
        switch (s) {
            case "a" -> {
                System.out.println("            a) Fast Cash");
                System.out.println("1----500\n2----1000 \n3----2000 \n4----5000 \n5----10000 \n6----15000 \n7----20000 \n");
                System.out.println("Select one of the denominations of money:");
                String st = scan.nextLine();
                String cash = null;
                switch (st) {
                    case "1" -> cash = "500";
                    case "2" -> cash = "1000";
                    case "3" -> cash = "2000";
                    case "4" -> cash = "5000";
                    case "5" -> cash = "10000";
                    case "6" -> cash = "15000";
                    case "7" -> cash = "20000";
                    default -> {
                        for (int i = 0; i < 30; i++) System.out.println();
                        System.out.println("Please select appropriate option");
                        withdraw();
                    }
                }
                if (cash != null) {
                    try{
                        Statement state=ATM_Machine.con.createStatement();
                        String query="select balance from data where id = '"+MyThread2.ID1+"'";
                        ResultSet rs=state.executeQuery(query);
                        while (rs.next()){
                            String balance1=rs.getString("balance");
                            double balance2=Double.parseDouble(balance1);
                            double enteredCash = Double.parseDouble(cash);
                            if(enteredCash>balance2){
                                System.out.println("You do not have sufficient balance");
                                withdraw();
                            }else if(enteredCash>maxAmount) {
                                System.out.println("Transaction limit exceeded");
                                withdraw();
                            }else{
                                System.out.println("Are you sure you want to withdraw Rs." + cash + " (Y/N)?");
                                String yes_no = scan.nextLine();
                                if (yes_no.equals("y")) {
                                    maxAmount = maxAmount - enteredCash;
                                    double updatedBalance=balance2-enteredCash;
                                    String update=String.valueOf(updatedBalance);
                                    try{
                                        String sql="update data set balance = '"+update+"' where id = '"+MyThread2.ID1+"'";
                                        PreparedStatement ps=ATM_Machine.con.prepareStatement(sql);
                                        ps.executeUpdate();
                                        System.out.println("Do you wish to print a receipt (Y/N)?");
                                        String confirm = scan.nextLine();
                                        if (confirm.equals("y")) {
                                            Statement st1=ATM_Machine.con.createStatement();
                                            String query2="select * from data where id = '"+MyThread2.ID1+"'";
                                            ResultSet resultSet=st1.executeQuery(query2);
                                            while(resultSet.next()){
                                                String no=resultSet.getString("id");
                                                System.out.println("Account # "+no);
                                                System.out.println("Date: "+Customers.date);
                                                System.out.println("Withdrawn: "+cash);
                                                System.out.println("Remaining Balance: "+update);
                                                System.out.println("\nPress enter");
                                                scan.nextLine();
                                                displayData();
                                            }

                                        }
                                        else displayData();

                                    }catch (Exception e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                                else displayData();

                                }
                        }
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("Press enter");
                        scan.nextLine();
                        displayData();
                    }
                }
            }
            case "b" -> {
                System.out.println("            b) Normal Cash");
                System.out.println("Enter the withdrawal amount: ");
                String withdraw = scan.nextLine();
                try{
                    Statement state=ATM_Machine.con.createStatement();
                    String query="select balance from data where id = '"+MyThread2.ID1+"'";
                    ResultSet rs=state.executeQuery(query);
                    while (rs.next()){
                        String balance1=rs.getString("balance");
                        double balance2=Double.parseDouble(balance1);
                        double enteredCash = Double.parseDouble(withdraw);
                        if(enteredCash>balance2){
                            System.out.println("You do not have sufficient balance");
                            withdraw();
                        }else if(enteredCash>maxAmount) {
                            System.out.println("Transaction limit exceeded");
                            withdraw();
                        }else{
                            System.out.println("Are you sure you want to withdraw Rs." + withdraw + " (Y/N)?");
                            String yes_no = scan.nextLine();
                            if (yes_no.equals("y")) {
                                maxAmount = maxAmount - enteredCash;
                                double updatedBalance=balance2-enteredCash;
                                String update=String.valueOf(updatedBalance);
                                try{
                                    String sql="update data set balance = '"+update+"' where id = '"+MyThread2.ID1+"'";
                                    PreparedStatement ps=ATM_Machine.con.prepareStatement(sql);
                                    ps.executeUpdate();
                                    System.out.println("Do you wish to print a receipt (Y/N)?");
                                    String confirm = scan.nextLine();
                                    if (confirm.equals("y")) {
                                        Statement st1=ATM_Machine.con.createStatement();
                                        String query2="select * from data where id = '"+MyThread2.ID1+"'";
                                        ResultSet resultSet=st1.executeQuery(query2);
                                        while(resultSet.next()){
                                            String no=resultSet.getString("id");
                                            System.out.println("Account # "+no);
                                            System.out.println("Date: "+Customers.date);
                                            System.out.println("Withdrawn: "+withdraw);
                                            System.out.println("Remaining Balance: "+update);
                                            System.out.println("\nPress enter");
                                            scan.nextLine();
                                            displayData();
                                        }

                                    }
                                    else displayData();

                                }catch (Exception e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            else displayData();

                        }
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("Press enter");
                    scan.nextLine();
                    displayData();
                }
            }
            case "c" -> displayData();
            default -> {
                for (int i = 0; i < 30; i++) System.out.println();
                System.out.println("Please select appropriate option");
                withdraw();
            }
        }
    }
    public void transfer(){
        System.out.println("2----Cash Transfer");
        System.out.println("Enter ID in which you want to transfer: ");
        String ID5=scan.nextLine();
        if(ID5.equals(MyThread2.ID1)){
            System.out.println("You can not transfer to your own account");
            System.out.println("Press enter");
            scan.nextLine();
            displayData();
        }else{

            try{
                int a=0;
                Statement st=ATM_Machine.con.createStatement();
                String query="select * from data where id = '"+ID5+"'";
                ResultSet rs=st.executeQuery(query);
                while (rs.next()){
                    String ID2=rs.getString("id");
                    if(ID2.equals(ID5)){
                        Statement sta=ATM_Machine.con.createStatement();
                        String query1="select balance from data where id = '"+MyThread2.ID1+"'";
                        ResultSet rs1=sta.executeQuery(query1);
                        while (rs1.next()){
                            String balance=rs1.getString("balance");
                            double bal=Double.parseDouble(balance);
                            System.out.println("Enter amount to transfer: ");
                            String am=scan.nextLine();
                            double amount=Double.parseDouble(am);
                            if(amount<=0){
                                System.out.println("Enter positive amount greater than zero");
                                System.out.println("Press enter");
                                scan.nextLine();
                                transfer();
                            }else{
                                if(amount>bal){
                                    System.out.println("You do not have sufficient balance.");
                                    System.out.println("Press enter");
                                    scan.nextLine();
                                    displayData();
                                }else{
                                    double up=bal-amount;
                                    String update=String.valueOf(up);
                                    try{
                                        String que="update data set balance = '"+update+"' where id = '"+MyThread2.ID1+"'";
                                        PreparedStatement ps=ATM_Machine.con.prepareStatement(que);
                                        ps.executeUpdate();
                                    }catch (Exception e){
                                        System.out.println(e.getMessage());
                                    }
                                    try{
                                        Statement statement=ATM_Machine.con.createStatement();
                                        String q1="select balance from data where id = '"+ID5+"'";
                                        ResultSet resultSet=statement.executeQuery(q1);
                                        while (resultSet.next()){
                                            String preBalance=resultSet.getString("balance");
                                            double preBal=Double.parseDouble(preBalance);
                                            double updatedBal=preBal+amount;
                                            String updatedAmount=String.valueOf(updatedBal);
                                            try{
                                                String q2="update data set balance = '"+updatedAmount+"' where id='"+ID5+"'";
                                                PreparedStatement pState=ATM_Machine.con.prepareStatement(q2);
                                                pState.executeUpdate();
                                                System.out.println("Balance successfully transferred.");
                                                System.out.println("Press enter");
                                                scan.nextLine();
                                                displayData();
                                            }catch (Exception e){
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                    }catch (Exception e){
                                        System.out.println(e.getMessage());
                                    }

                                }
                            }

                        }
                        a=10;
                    }
                }
                if(a!=10){
                    System.out.println("Account does not exist");
                    System.out.println("Press enter");
                    scan.nextLine();
                    displayData();
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
        }

        }














        System.out.println("Enter amount in multiples of 500: ");
        String enteredMoney=scan.nextLine();
        try{
            int enteredAmount=Integer.parseInt(enteredMoney);
            if(enteredAmount>0 && enteredAmount%500==0){
                if(enteredAmount>Customers.rupees){
                    System.out.println("You do not have sufficient balance to perform this transaction");
                    System.out.println("Type 'ok'");
                    scan.nextLine();
                    displayData();
                }else{
                    System.out.println("Enter the account number to which you want to transfer: ");
                    String accountNo=scan.nextLine();
                    System.out.println("You wish to deposit Rs "+enteredAmount+" in account held by Mr. ABC ; \nPlease re-enter the account number: ");
                    String confirmAccountNo=scan.nextLine();
                    if(accountNo.equals(confirmAccountNo)){
                        Customers.rupees=Customers.rupees-enteredAmount;
                        Administrator.balance-=enteredAmount;
                        System.out.println("Transaction successful");
                        System.out.println("Do you wish to print a receipt (Y/N)?");
                        String c=scan.nextLine();
                        if(c.equals("y")){
                            System.out.println("Account number: "+accountNo+"\nDate: "+Customers.date+"\nAmount transferred: "+enteredAmount);
                            System.out.println("Remaining Balance: "+Customers.rupees);
                            System.out.println("\nType 'ok'");
                            scan.nextLine();
                            displayData();
                        }
                        else{
                            displayData();
                        }
                    }else{
                        System.out.println("Account number does not match to previous");
                        System.out.println("Type 'ok'");
                        scan.nextLine();
                        displayData();
                    }
                }

            }else{
                System.out.println("Enter correct amount");
                System.out.println("Type 'ok'");
                scan.nextLine();
                displayData();
            }
        }catch(Exception e){
            System.out.println("Please enter correct amount multiple of 500");
            System.out.println("Type 'ok'");
            scan.nextLine();
            displayData();
        }

    }
//    public void deposit(){
//        System.out.println("3----Deposit Cash");
//        System.out.println("Enter the cash amount to deposit: ");
//        String deposit=scan.nextLine();
//        try{
//            double depositingAmount=Double.parseDouble(deposit);
//            if(depositingAmount<=0){
//                System.out.println("Please enter correct amount");
//                deposit();
//            }
//            System.out.println("Cash deposited successfully");
//            Customers.rupees+=depositingAmount;
//            Administrator.balance+=depositingAmount;
//            System.out.println("Do you wish to print a receipt (y/n)? ");
//            String c=scan.nextLine();
//            if(c.equals("y")){
//                System.out.println("Account number # "+Customers.accountNumber+"\nDate: "+Customers.date);
//                System.out.println("Deposited: "+depositingAmount);
//                System.out.println("Total Balance: "+Customers.rupees);
//                System.out.println("Type 'ok'");
//                scan.nextLine();
//                displayData();
//            }else displayData();
//        }catch(Exception e){
//            System.out.println("Please provide valid amount");
//            displayData();
//        }
//    }
    public void displayBalance(){
        try{
            Statement state=ATM_Machine.con.createStatement();
            String query="select balance from data where id = '"+MyThread2.ID1+"'";
            ResultSet rs=state.executeQuery(query);
            while (rs.next()){
                String balance1=rs.getString("balance");
                System.out.println("Your available balance is: "+balance1);
                System.out.println("\nPress enter");
                scan.nextLine();
                displayData();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Press enter");
            scan.nextLine();
            displayData();
        }
    }
}
