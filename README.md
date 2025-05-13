# SmartAttend

SmartAttend is a Java-based console application designed to manage student attendance efficiently. It allows administrators to insert, view, search, modify, and delete student records while also calculating aggregate attendance with bonus adjustments based on previous semester performance.

## 🚀 Features

- Secure login for admin access
- Add new student details with subject-wise attendance
- Automatically calculate aggregate attendance
- Bonus attendance logic based on previous semester performance
- Modify or delete existing student records
- Persistent data storage using a local file (`Data.txt`)
- User-friendly console interface

## 📂 Project Structure

SmartAttend/
├── main.java # Entry point with login and menu
├── Student.java # Student model with attendance logic
├── StudentManager.java # Handles data operations and file I/O
├── Data.txt # Saved student records (created at runtime)


## 🛠️ How to Run

1. **Compile the code:**
   ```bash
   javac main.java Student.java StudentManager.java
   
2. Run the application:
   java main
   
3. Login credentials:
   Username: admin
   Password: password

📝 Attendance Logic
  Aggregate attendance is calculated as the average of all subject attendance percentages.

  If a student had ≥85% attendance in the previous semester but has <75% currently, a bonus is applied:

  95%+ → +10%

  90–94% → +7%

  87–89% → +5%

  85–86% → +3%


📌 Requirements
  Java 8 or higher

  No external libraries required
