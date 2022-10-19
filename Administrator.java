import java.util.Scanner;

public class Administrator {
    double balance;
    int accountNumber=15;
//    String accNo=String.valueOf(accountNumber);
    String date="20/10/2022";
    String login;
    String pin;
    String name="X";
    String type;
    String bal;
    String status;
    Scanner scan=new Scanner(System.in);
    public void display(){
        for(int i=0; i<30; i++) System.out.println();
        System.out.println("\n          Administrator Menu\n");
        System.out.println("1----Create New Account.");
        System.out.println("2----Delete Existing Account.");
        System.out.println("3----Update Account Information.");
        System.out.println("4----Search for Account.");
        System.out.println("5----View Reports.");
        System.out.println("6----Exit");
        System.out.println("\nEnter your choice:");
        String choice=scan.nextLine();
        for(int i=0; i<30;i++) System.out.println();
        switch(choice){
            case "1" ->creating_new_account();
            case "2" ->delete_existing_account();
            case "3" ->update_account_information();
            case "4" ->search_for_account();
            case "5" ->view_reports();
            case "6" ->ATM_Machine.main(null);
            default ->{
                System.out.println("                                *** Please enter correct input!!  ***" +
                        "\n-----------------------------------------------------------------------------------------------------------------------------");
                try{
                    Thread.sleep(2000);
                    display();
                }catch(InterruptedException ie){
                    System.out.println("An exception occurred");
                }
            }

        }
    }
    public void creating_new_account(){
        System.out.println("1----Create New Account");
        System.out.println("Enter new login: ");
        login=scan.nextLine();
        if(login.isEmpty()){
            System.out.println("Must enter login");
            creating_new_account();
        }
        for(int i=0;i<15;i++) System.out.println();
        System.out.println("Pin code: ");
        pin=scan.nextLine();
        if(pin.isEmpty()){
            System.out.println("Must enter pin code");
            creating_new_account();
        }
        for(int i=0;i<15;i++) System.out.println();
        System.out.println("Holders name: ");
        name=scan.nextLine();
        if(name.isEmpty()){
            System.out.println("Must enter name!!");
            creating_new_account();
        }
        for(int i=0;i<15;i++) System.out.println();
        System.out.println("Type (savings/current): ");
        type=scan.nextLine();
        if(type.equals("savings")||type.equals("current")){
            for(int i=0;i<15;i++) System.out.println();
            System.out.println("Initial Balance: ");
            bal=scan.nextLine();
            try{
                if(bal.isEmpty()){
                    System.out.println("must enter initial balance");
                    creating_new_account();
                }else{
                    balance= Double.parseDouble(bal);
                    for(int i=0;i<15;i++) System.out.println();
                    System.out.println("Status (active): ");
                    status=scan.nextLine();
                    if(status.equals("active")){
                        for(int i=0;i<15;i++) System.out.println();
                        System.out.println("Account successfully created - the account number assigned is: "+(++accountNumber));
                        System.out.println("Press ok");
                        scan.nextLine();
                        display();
                    }else{
                        System.out.println("Please provide correct status");
                        creating_new_account();
                    }
                }
            }catch(Exception e){
                System.out.println("Please enter correct amount");
                creating_new_account();
            }

        }else{
            System.out.println("must enter savings or current");
            creating_new_account();
        }
    }
    public void delete_existing_account(){
        for(int i=0; i<15;i++) System.out.println();
        System.out.println("2---Delete Existing Account ");
        System.out.println("Enter the account number to which you want to delete: " +
                "\nType 'cancel' to cancel");
        try{
            String acNo=scan.nextLine();
            String accNo=String.valueOf(accountNumber);
            if(accNo.equals(acNo)){
                System.out.println("You wish to delete the account held by Mr " +name+
                        " ; If this information is correct please re-enter the account number: " +
                        "\nType 'cancel' to cancel");
                String accountant=scan.nextLine();
                if(acNo.equals(accountant)){
                    System.out.println("Account deleted successfully");
                    balance=0;
                    accountNumber=0;
                    login="";
                    pin="";
                    name="";
                    type="";
                    status="dead";
                    System.out.println("Press ok");
                    scan.nextLine();
                    display();
                }
                else if(accountant.equals("cancel"))display();
                else{
                    System.out.println("Account number does not match to previous");
                    try{
                        Thread.sleep(2000);
                        delete_existing_account();
                    }catch(InterruptedException ie){
                        System.out.println("An exception occurred");
                    }
                }
            }
            else if(acNo.equals("cancel"))display();
            else{
                System.out.println("Account does not exist");
                try{
                    Thread.sleep(2000);
                    delete_existing_account();
                }catch(InterruptedException ie){
                    System.out.println("An exception occurred");
                }

            }
        }catch(Exception e){
            System.out.println("Please provide valid input");
            try{
                Thread.sleep(2000);
                delete_existing_account();
            }catch(InterruptedException ie){
                System.out.println("An exception occurred");
            }

        }
    }
    public void update_account_information(){
        System.out.println("\n3---Update Account Information \n");
        System.out.println("Enter the Account Number: ");
        String accountNo=scan.nextLine();
        String accNo=String.valueOf(accountNumber);
        if(accountNo.equals(accNo)) {
            System.out.println("Login: " + login);
            System.out.println("Pin Code: " + pin);
            System.out.println("Name: " + name);
            System.out.println("Type: " + type);
            System.out.println("Balance: " + balance);
            System.out.println("Status: " + status);
            System.out.println("\nPlease enter in the fields you wish to update (leave blank otherwise): \n");
            System.out.println("Enter new login: ");
            String login1 = scan.nextLine();
            if (login1.equals("")) System.out.println();
            else login = login1;
            System.out.println("Pin code: ");
            String pin1 = scan.nextLine();
            if (pin1.equals("")) System.out.println();
            else pin = pin1;
            System.out.println("Name: ");
            String name1=scan.nextLine();
            if (name1.equals("")) System.out.println();
            else name = name1;
            System.out.println("Type: ");
            String type1=scan.nextLine();
            if (type1.equals("")) System.out.println();
            else type = type1;

            System.out.println("Balance: ");
            String balance1=scan.nextLine();
            if (balance1.equals("")) System.out.println();
            else{
                bal = balance1;
                balance=Double.parseDouble(bal);
            }

            System.out.println("Status: ");
            String status1=scan.nextLine();
            if (status1.equals("")) System.out.println();
            else status = status1;
            System.out.println("Your account has been successfully updated.");
            try{
                Thread.sleep(2000);
                display();
            }catch(InterruptedException ie){
                System.out.println("An exception occurred");
            }
        }else{
            System.out.println("Account does not exist");
            update_account_information();
        }
    }
    public void search_for_account(){
        System.out.println("4---Search for Account ");
        System.out.println("Search Menu");
        System.out.println("Account ID:");
        String ac=scan.nextLine();
        String accNo=String.valueOf(accountNumber);
        if(ac.equals(accNo)){
            System.out.println("Login: "+login+"\nPin: "+pin+"\nStatus: "+name+"\nType: "+type+"\nBalance: "+bal+"\nStatus: "+status);
            System.out.println("\n\nType 'ok'");
            scan.nextLine();
            display();
        }
        else{
            System.out.println("Account does not exist");
            try{
                Thread.sleep(2000);
                display();
            }catch (InterruptedException ie){
                System.out.println("An exception occurred");
            }
        }

    }
    public void view_reports(){
        for(int i=0;i<15;i++) System.out.println();
        System.out.println("\n      5---View Reports\n");
        System.out.println("1---Accounts By Amount");
        System.out.println("2---Accounts By Date");
        System.out.println("Type 'cancel' to cancel");
        System.out.println("Enter your choice: ");
        String choice=scan.nextLine();
        String bal1,bal2;
        double minBal=0,maxBal=0;
        switch (choice) {
            case "1" -> {
                System.out.println("Enter the minimum amount: ");
                try {
                    bal1 = scan.nextLine();
                    minBal = Double.parseDouble(bal1);
                } catch (Exception e) {
                    System.out.println("Please provide correct input");
                    view_reports();
                }
                System.out.println("Enter the maximum amount:");
                try {
                    bal2 = scan.nextLine();
                    maxBal = Double.parseDouble(bal2);
                } catch (Exception e) {
                    System.out.println("Please provide correct input");
                    view_reports();
                }
                System.out.println("==== SEARCH RESULTS ======");
                if (minBal != balance || maxBal != balance) {
                    try {
                        System.out.println("No result found");
                        Thread.sleep(2000);
                        view_reports();
                    } catch (InterruptedException ie) {
                        System.out.println("An exception occurred");
                    }
                } else {
                    System.out.println("Login: " + login + "\nPin: " + pin + "\nStatus: " + name + "\nType: " + type + "\nBalance: " + bal + "\nStatus: " + status);
                    System.out.println("\n\nType 'ok'");
                    scan.nextLine();
                    display();
                }
            }
            case "2" -> {
                System.out.println("Enter the starting date(DD/MM/YY): ");
                String date1 = scan.nextLine();
                System.out.println("Enter the ending date(DD/MM/YY): ");
                String date2 = scan.nextLine();
                if (date1.equals(date2) && date1.equals(date)) {
                    System.out.println("==== SEARCH RESULTS ======");
                    System.out.println("Login: " + login + "\nPin: " + pin + "\nStatus: " + name + "\nType: " + type + "\nBalance: " + bal + "\nStatus: " + status);
                    System.out.println("\n\nType 'ok'");
                    scan.nextLine();
                    display();
                } else {
                    try {
                        System.out.println("No data found");
                        Thread.sleep(2000);
                        display();
                    } catch (InterruptedException ie) {
                        System.out.println("An exception occurred");
                    }
                }
            }
            case "cancel" -> display();
            default -> {
                try {
                    System.out.println("Please provide correct input");
                    Thread.sleep(2000);
                    view_reports();
                }catch (InterruptedException ie){
                    System.out.println("An exception occurred");
                    System.out.println("Type 'ok'");
                    scan.nextLine();
                    view_reports();
                }
            }
        }
    }
}
