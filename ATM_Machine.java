import java.util.Scanner;

public class ATM_Machine {
    public static void main(String[] args) {
        Administrator admin=new Administrator();
        Customers customer=new Customers();
//        customer.setAdmin(admin);
        System.out.println("\n          1....ADMINISTRATOR\n\n           2....Customers\n\n          3....Exit");
        Scanner scan=new Scanner(System.in);
        String select=scan.next();
        if(select.equals("1"))admin.display();
        else if(select.equals("2"))customer.displayData();
        else if(select.equals("3"))System.exit(0);
        else{
            System.out.println("Please select correct option");
            ATM_Machine.main(null);
        }
    }
}
