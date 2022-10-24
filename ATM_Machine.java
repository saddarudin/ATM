import java.sql.*;
import java.util.Scanner;

class MyThread1 extends Thread{
    @Override
    public void run() {
        ATM_Machine.connectWithDB();
    }
}
class MyThread2 extends Thread{
    static String ID1;
    @Override
    public void run() {
        Scanner sc=new Scanner(System.in);
        int i=1;
        int a=0;
        int b=0;
        do {
            System.out.println("Enter User ID: ");
            MyThread2.ID1=sc.nextLine();
            System.out.println("Enter PIN: ");
            String PIN=sc.nextLine();
            try{
                Statement st=ATM_Machine.con.createStatement();
                String query="select * from data where id = '"+MyThread2.ID1+"'";
                ResultSet rs=st.executeQuery(query);
                        while(rs.next()){
                            String ID2=rs.getString("id");
                            if(ID2.equals(MyThread2.ID1)){
                                Statement state=ATM_Machine.con.createStatement();
                                String pass="select pin from data where pin = '"+PIN+"'";
                                ResultSet r1=state.executeQuery(pass);
                                while(r1.next()){
                                    String pass1=r1.getString("pin");
                                    if(pass1.equals(PIN)){
                                        System.out.println("Log In Successfully.");
                                        System.out.println("Press enter");
                                        sc.nextLine();
                                        for(int k=0;k<30;k++) System.out.println();
                                        Administrator admin=new Administrator();
                                        Customers customer=new Customers();
                                        System.out.println("\n          1....ADMINISTRATOR\n\n          2....Customers\n\n          3....Exit");
                                        String select=sc.next();
                                        switch (select) {
                                            case "1" -> admin.display();
                                            case "2" -> customer.displayData();
                                            case "3" -> System.exit(0);
                                            default -> {
                                                System.out.println("Please select correct option");
                                                ATM_Machine.main(null);
                                            }
                                        }
                                        b=10;
                                    }
                                }
                                if(b!=10){
                                    System.out.println("Password does not match!!");
                                    i++;
                                }
                                a=10;
                            }
                        }
                        if(a!=10){
                            System.out.println("ID does not match");
                            i++;
                        }
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Press enter");
                sc.nextLine();
            }
        }while (i<=3);
        System.out.println("Login failed wait for 5 seconds");
        try{
            Thread.sleep(5000);
            for(int j=0;j<30;j++) System.out.println();
            MyThread2 t=new MyThread2();
            t.start();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
public class ATM_Machine {
    public static Connection con=null;
    public static void connectWithDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver successfully loaded");
        }catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shahid", "root", "saddarudin338@");
//            System.out.println("Connection Established successfully");
        }  catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        MyThread1 t1=new MyThread1();
        MyThread2 t2=new MyThread2();
        try{
            t1.start();
            t1.join();
            t2.start();
            t2.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
