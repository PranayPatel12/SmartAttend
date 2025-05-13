import java.util.Scanner;
public class main {
    public static void main(String[] args){
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);
        System.out.println("\u001B[0m");
        System.out.println("==========================================");
        System.out.println("\u001B[1mWelcome To Student Attendance Calculator\u001B[0m");
        System.out.println("==========================================");
        System.out.println("\u001B[0m");

        System.out.print("Enter Username: ");
        String userName = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.next();

        if(!userName.equals("admin") && !password.equals("password")){
            System.out.println("Invalid login. Exiting...");
            System.exit(0);
        }

        manager.loadData();

        while(true){
            System.out.println("Attendance Calculator Menu: \n");
            System.out.println("1. Insert student details");
            System.out.println("2. Display all Students details");
            System.out.println("3. Search student by ID");
            System.out.println("4. Delete/Modify student details");
            System.out.println("5. Save and Exit\n");

            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    manager.insertStudent(sc);
                    break;
                case 2:
                    manager.displayAllStudents();
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    manager.searchStudent(id);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    int regd = sc.nextInt();
                    manager.deleteOrModifyStudent(regd);
                    break;
                case 5:
                    manager.saveData();
                    return;
                default:
                    System.out.println("Invalid choice. Exiting...");
                    System.exit(0);

            }
        }
    }
}
