import java.util.Scanner;

public class ATM_Machine {
    public static void main(String[] args) {
        Administrator admin=new Administrator();
        Customers customer=new Customers();
        System.out.println("\n          1....ADMINISTRATOR\n\n           2....Customers\n\n          3....Exit");
        Scanner scan=new Scanner(System.in);
        String select=scan.next();
        switch (select) {
            case "1" -> admin.display();
            case "2" -> customer.displayData();
            case "3" -> System.exit(0);
            default -> {
                System.out.println("Please select correct option");
                ATM_Machine.main(null);
            }
        }
    }
}
