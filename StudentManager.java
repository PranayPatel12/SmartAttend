import java.io.*;
import java.lang.classfile.constantpool.IntegerEntry;
import java.util.*;
public class StudentManager {
    private static final String DATA_FILE = "Data.txt";
    private List<Student> students = new ArrayList<>();
    public void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int previousSemAttendance = Integer.parseInt(parts[2]);

                Map<String, Integer> attendance = new HashMap<>();
                for (int i = 3; i < parts.length; i++) {
                    String[] subjectData = parts[i].split(":");
                    if (subjectData.length == 2) {
                        String subject = subjectData[0].trim();
                        int score = Integer.parseInt(subjectData[1].trim());
                        attendance.put(subject, score);
                    }
                }

                students.add(new Student(id, name, attendance, previousSemAttendance));
            }
        } catch (IOException e) {
            System.out.println("No Saved Data.\nPlease Save the Data first.");
        } catch (NumberFormatException e) {
            System.out.println("Error while parsing data. Please check the file format.");
        }
    }


    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Student student : students) {
                writer.write(student.getId() + " " + student.getName() + " " + student.getPreviousSemAttendance());
                for (Map.Entry<String, Integer> entry : student.getAttendance().entrySet()) {
                    writer.write(" " + entry.getKey() + ":" + entry.getValue());
                }
                writer.newLine();
            }
            System.out.println("Data Saved Successfully");
        } catch (IOException e) {
            System.out.println("Unable to save data.\nPlease try again later.");
        }
    }


    public void insertStudent(Scanner sc){
        System.out.print("Enter Registration number: ");
        int regd_no = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.next();
        System.out.print("Enter previous Sem Attendance: ");
        int previous_sem = sc.nextInt();

        Map<String, Integer> attendance = new HashMap<>();
        System.out.print("Enter number of Subjects: ");
        int subject_cnt = sc.nextInt();
        for(int i=0; i<subject_cnt; i++){
            System.out.print("Enter Subject name: ");
            String subject = sc.next();
            System.out.print("Enter number of classes attended for "+subject+": ");
            int att_class = sc.nextInt();
            System.out.print("Enter total number of classes for "+subject+": ");
            int total_class = sc.nextInt();
            int attendance_percentage = (int) (((double) att_class / total_class) * 100);
            attendance.put(subject,attendance_percentage);
        }
        students.add(new Student(regd_no,name,attendance,previous_sem));
        System.out.println("Student data added successfully");
    }

    public void displayAllStudents(){
        if(students.isEmpty()){
            System.out.println("No data found.");
            return;
        }
        for (Student student : students) {
            System.out.println("====================================");
            System.out.printf("Registration number: %s\n", student.getId());
            System.out.printf("Student Name       : %s\n", student.getName());
            System.out.printf("Previous Semester  : %d%%\n", student.getPreviousSemAttendance());
            System.out.printf("Current Semester Aggregate: %.2f%%\n", student.getAggregateAttendance());
            System.out.println("Subjects:");
            for (Map.Entry<String, Integer> entry : student.getAttendance().entrySet()) {
                System.out.printf("  - %-15s : %3d%%\n", entry.getKey(), entry.getValue());
            }
            System.out.println("====================================");
        }
    }

    public void searchStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("====================================");
                System.out.printf("Registration No : %s\n",s.getId());
                System.out.printf("Name            : %s\n",s.getName());
                System.out.printf("Previous Sem    : %d%%\n",s.getPreviousSemAttendance());
                System.out.printf("Aggregate       : %.2f%%\n", s.getAggregateAttendance());
                        System.out.println("Subjects:");
                for (Map.Entry<String, Integer> entry : s.getAttendance().entrySet()) {
                    System.out.printf("  - %-15s : %3d%%\n", entry.getKey(), entry.getValue());
                }
                System.out.println("====================================");
                return;
            }
        }
        System.out.println("Student ID not found.");
    }

    public void deleteOrModifyStudent(int id){
        boolean found = false;
        for(int i=0; i<students.size(); i++){
            Student student = students.get(i);
            if(student.getId() == id){
                found = true;
                System.out.println("1. Delete Student.\n2.Modify Student Data.");
                Scanner sc = new Scanner(System.in);
                int choice1=sc.nextInt();
                if(choice1 == 1){
                    students.remove(i);
                    System.out.println("Student deleted successfully.");
                }
                else if(choice1 == 2){
                    System.out.println("\nSelect an option to modify.\n1. Registration number.\n2. Student name.\n3. Previous Semester Attendance." +
                            "\n4. Current Semester Attendance.\n5. Total Data.");
                    System.out.print("\nEnter your choice: ");
                    int choice2 = sc.nextInt();
                    if(choice2==1){
                        updateRegistrationNumber(i,students);
                    }
                    else if(choice2==2){
                        updateStudentName(i,students);
                    }
                    else if(choice2==3){
                        updatePreviousSemAttendance(i,students);
                    }
                    else if(choice2==4){
                        updateAttendance(i,students);
                    }
                    else if(choice2==5){
                        updateRegistrationNumber(i,students);
                        updateStudentName(i,students);
                        updatePreviousSemAttendance(i,students);
                        updateAttendance(i,students);
                    }
                    else{
                        System.out.println("Invalid choice. Exiting");
                        return;
                    }
                }
                else{
                    System.out.println("Invalid choice. please try again.");
                    return;
                }
            }
        }
        if(!found) {
            System.out.println("Student ID not found.");
        }
    }

    private static void updateRegistrationNumber(int i,List<Student> students){
        Scanner sc = new Scanner(System.in);
        Student student = students.get(i);
        System.out.print("Enter new registration number: ");
        int new_regd_no = sc.nextInt();
        Student update1 = new Student(new_regd_no,student.getName(),student.getAttendance(),student.getPreviousSemAttendance());
        students.set(i,update1);
        System.out.println("Registration number updated successfully.");
    }

    private static void updateStudentName(int i, List<Student> students){
        Scanner sc = new Scanner(System.in);
        Student student = students.get(i);
        System.out.print("Enter Student name: ");
        String name = sc.next();
        Student update2 = new Student(student.getId(),name,student.getAttendance(),student.getPreviousSemAttendance());
        students.set(i,update2);
        System.out.println("Student name updated successfully.");
    }

    private static void updatePreviousSemAttendance(int i, List<Student> students){
        Scanner sc = new Scanner(System.in);
        Student student = students.get(i);
        System.out.print("Enter students updated previous semester attendance: ");
        int newPrevSem = sc.nextInt();
        Student update3 = new Student(student.getId(),student.getName(),student.getAttendance(),newPrevSem);
        students.set(i,update3);
        System.out.println("Previous Semester attendance updated.");
    }

    private static void updateAttendance(int i, List<Student> students){
        Scanner sc = new Scanner(System.in);
        Student student = students.get(i);
        System.out.print("Enter Subject to modify attendance: ");
        String subject = sc.next();
        if(!student.getAttendance().containsKey(subject)){
            System.out.println("Subject does not exist.");
            return;
        }
        System.out.print("Enter number of classes attended for "+subject+": ");
        int att_class = sc.nextInt();
        System.out.print("Enter total number of classes for "+subject+": ");
        int total_class = sc.nextInt();
        int attendance_percentage = (att_class/total_class)*100;
        student.updateAttendance(subject,attendance_percentage);
        System.out.println("Attendance updated with aggregate bonus check.");
    }
}

