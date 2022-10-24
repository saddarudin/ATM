import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Administrator {
    static double balance;
    static int accountNumber;
    String newId;
//    String accNo=String.valueOf(accountNumber);
    static String date="20/10/2022";
    static String login;
    static String pin;
    static String name;
    static String type;
    static String bal;
    static String status;
    Scanner scan=new Scanner(System.in);
    public void display(){
        for(int i=0; i<30; i++) System.out.println();
        System.out.println("\n          Administrator Menu\n");
        System.out.println("1----Create New Account.");
        System.out.println("2----Delete Existing Account.");
        System.out.println("3----Update Account Information.");
        System.out.println("4----Search for Account.");
        System.out.println("5----Add Balance.");
        System.out.println("6----Back");
        System.out.println("\nEnter your choice:");
        String choice=scan.nextLine();
        for(int i=0; i<30;i++) System.out.println();
        switch(choice){
            case "1" ->creating_new_account();
            case "2" ->delete_existing_account();
            case "3" ->update_account_information();
            case "4" ->search_for_account();
            case "5" ->addBalance();
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
        System.out.println("Enter username: ");
        Administrator.login=scan.nextLine();
        if(Administrator.login.isEmpty()){
            System.out.println("Must enter user name");
            creating_new_account();
        }
        for(int i=0;i<15;i++) System.out.println();
        System.out.println("Pin code: ");
        Administrator.pin=scan.nextLine();
        if(Administrator.pin.isEmpty()){
            System.out.println("Must enter pin code");
            creating_new_account();
        }
        for(int i=0;i<15;i++) System.out.println();
        System.out.println("Holders name: ");
        Administrator.name=scan.nextLine();
        if(Administrator.name.isEmpty()){
            System.out.println("Must enter name!!");
            creating_new_account();
        }
        for(int i=0;i<15;i++) System.out.println();
        System.out.println("Type (savings/current): ");
        Administrator.type=scan.nextLine();
        if(Administrator.type.equals("savings")||Administrator.type.equals("current")){
            for(int i=0;i<15;i++) System.out.println();
            System.out.println("Initial Balance: ");
            Administrator.bal=scan.nextLine();
            try{
                if(Administrator.bal.isEmpty()){
                    System.out.println("must enter initial balance");
                    creating_new_account();
                }else{
                    Administrator.balance= Double.parseDouble(Administrator.bal);
                    for(int i=0;i<15;i++) System.out.println();
                    System.out.println("Status (active): ");
                    Administrator.status=scan.nextLine();
                    if(Administrator.status.equals("active")){

                        try{
                            Statement statement=ATM_Machine.con.createStatement();
                            String query1="SELECT * FROM data WHERE id=(SELECT MAX(id) FROM data)";
                            ResultSet rs=statement.executeQuery(query1);
                            while(rs.next()){
                                String oldId=rs.getString("id");
                                int id1=Integer.parseInt(oldId);
//                                int id2=id1+1;
                                newId=String.valueOf(++id1);
                                String sql="insert into data (user,pin,name,type,balance,status,id) values(?,?,?,?,?,?,?)";
                                PreparedStatement ps=ATM_Machine.con.prepareStatement(sql);
                                ps.setString(1,Administrator.login);
                                ps.setString(2,Administrator.pin);
                                ps.setString(3,Administrator.name);
                                ps.setString(4,Administrator.type);
                                ps.setString(5,Administrator.bal);
                                ps.setString(6,Administrator.status);
                                ps.setString(7,newId);
                                ps.executeUpdate();
                                for(int i=0;i<15;i++) System.out.println();
                                System.out.println("Account successfully created - the account number assigned is: "+newId);
                                System.out.println("Press ok");
                                scan.nextLine();
                                display();
                            }


                        }catch(Exception e){
                            System.out.println(e.getMessage());
                        }


                    }else{
                        System.out.println("Please provide correct status");
                        creating_new_account();
                    }
                }
            }catch(Exception e){
                System.out.println("Please enter correct amount");
                creating_new_account();
            }

        }
        else{
            System.out.println("must enter savings or current");
            creating_new_account();
        }
    }
    public void delete_existing_account(){
        for(int i=0; i<15;i++) System.out.println();
        System.out.println("2---Delete Existing Account ");
        System.out.println("Enter the account number(ID) to which you want to delete: " +
                "\nType 'cancel' to cancel");
        try{
            String acNo=scan.nextLine();
                int i=0;
                Statement st=ATM_Machine.con.createStatement();
                String query="select id from data";
                ResultSet rs=st.executeQuery(query);
                while(rs.next()){
                    String ID1=rs.getString("id");
                    if(ID1.equals(acNo)){
                        String query2="select name from data where id = '"+ID1+"'";
                        ResultSet r1=st.executeQuery(query2);
                        while(r1.next()){
                            String name1=r1.getString("name");
                            System.out.println("You wish to delete the account held by Mr " +name1+
                                    " ; If this information is correct please re-enter the account number: " +
                                    "\nType 'cancel' to cancel");
                            String accountant=scan.nextLine();
                            if(accountant.equals(ID1)){
                                try{
                                    Statement statement=ATM_Machine.con.createStatement();
                                    String sql="delete from data where id='"+ID1+"'";
                                    statement.executeUpdate(sql);
                                    System.out.println("Account deleted successfully");
                                    System.out.println("Press enter");
                                    scan.nextLine();
                                    display();
                                }catch (Exception e){
                                    System.out.println(e.getMessage());
                                }

                            }else if(accountant.equals("cancel"))display();
                            else{
                                System.out.println("Account number does not match to previous");
                                System.out.println("Press enter");
                                scan.nextLine();
                                display();
                            }

                        }
                        i=10;
                    }
                }
                if(i!=10){
                    System.out.println("Account does not exist.");
                    System.out.println("Press enter");
                    scan.nextLine();
                    display();
                }

            else if(acNo.equals("cancel"))display();
            else{
                System.out.println("Account does not exist");
                    System.out.println("Press enter");
                    scan.nextLine();
                    display();
            }
        }catch(Exception e){
            System.out.println("Please provide valid input");
            System.out.println("Press enter");
            scan.nextLine();
        }
    }
    public void update_account_information(){
        System.out.println("\n3---Update Account Information \n");
        System.out.println("Enter the Account Number (ID): ");
        String accountNo=scan.nextLine();
        try{
            int i=0;
            Statement st=ATM_Machine.con.createStatement();
            String query="select id from data";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                String ID=rs.getString("id");
                if(ID.equals(accountNo)){
                    System.out.println("Name: ");
                    String name1=scan.nextLine();
                    System.out.println("Type: ");
                    String type1=scan.nextLine();
                    System.out.println("Status: ");
                    String status1=scan.nextLine();
                    String update="update data set name = '"+name1+"'," +
                            "type = '"+type1+"',status = '"+status1+"' where id = '"+ID+"'";
                    PreparedStatement ps=ATM_Machine.con.prepareStatement(update);
                    ps.executeUpdate();
                    System.out.println("Your account has been successfully updated.");
                    System.out.println("Type 'ok'");
                    scan.nextLine();
                    display();
                    i=10;
                }
            }
            if(i!=10){
                System.out.println("Account does not exist");
                System.out.println("Press enter");
                scan.nextLine();
                display();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void search_for_account(){
        System.out.println("4---Search for Account ");
        System.out.println("Search Menu");
        System.out.println("Account ID:");
        String ac=scan.nextLine();
        try{
            int i=0;
            Statement st=ATM_Machine.con.createStatement();
            String query="select * from data where id = '"+ac+"'";
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                String id1=rs.getString("id");
                if(id1.equals(ac)){
                    String user=rs.getString("user");
                    String pin=rs.getString("pin");
                    String name=rs.getString("name");
                    String type=rs.getString("type");
                    String balance=rs.getString("balance");
                    String status=rs.getString("status");
                    System.out.println("User : "+user);
                    System.out.println("PIN: "+pin);
                    System.out.println("Name: "+name);
                    System.out.println("Type: "+type);
                    System.out.println("Balance: "+balance);
                    System.out.println("Status: "+status);
                    System.out.println("\nPress enter");
                    scan.nextLine();
                    display();
                    i=10;
                }
            }
            if(i!=10){
                System.out.println("Account does not exist");
                System.out.println("Press enter");
                scan.nextLine();
                display();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Enter space");
            scan.nextLine();
            display();
        }
        String accNo=String.valueOf(Administrator.accountNumber);
        if(accNo.equals(ac)){
            System.out.println("Login: "+Administrator.login+"\nPin: "+Administrator.pin+"\nName: "+Administrator.name+"\nType: "+Administrator.type+"\nBalance: "+Administrator.bal+"\nStatus: "+Administrator.status);
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
    public void addBalance(){
        for(int i=0;i<15;i++) System.out.println();
        System.out.println("Enter ID: ");
        String id1=scan.nextLine();
        try{
            int i=0;
            Statement st=ATM_Machine.con.createStatement();
            String query="select id from data";
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                String id2=rs.getString("id");
                if(id2.equals(id1)){
                    String query1="select balance from data where id = '"+id2+"'";
                    ResultSet r1=st.executeQuery(query1);
                    while (r1.next()){
                        String balance1=r1.getString("balance");
                        System.out.println("Enter amount to add: ");
                        String balance2=scan.nextLine();
                        try{
                            double bal2=Double.parseDouble(balance2);
                            double bal1=Double.parseDouble(balance1);
                            if(bal2<=0){
                                System.out.println("Balance must be positive and greater than 0.");
                                System.out.println("Press enter");
                                scan.nextLine();
                                addBalance();
                            }else{
                                double bal3=bal1+bal2;
                                String balance3=String.valueOf(bal3);
                                try{
                                    String sql="update data set balance = '"+balance3+"' where id = '"+id2+"'";
                                    PreparedStatement ps=ATM_Machine.con.prepareStatement(sql);
                                    ps.executeUpdate();
                                    System.out.println("Balance added successfully \nYour new Balance is: "+balance3);
                                    System.out.println("Press enter");
                                    scan.nextLine();
                                    display();
                                }catch (Exception e){
                                    System.out.println(e.getMessage());
                                    System.out.println("Press enter");
                                    scan.nextLine();
                                    display();
                                }
                            }
                        }catch(Exception e){
                            System.out.println("Provide correct amount!!");
                            System.out.println("Press enter");
                            scan.nextLine();
                            addBalance();
                        }
                    }
                    i=10;
                }
            }
            if(i!=10){
                System.out.println("Account does not exist");
                System.out.println("Press enter");
                scan.nextLine();
                display();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
