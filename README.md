#  Study Tracker

##  Project Overview
**Marvellous Study Tracker** is a Java-based project designed to help students track and analyze their study activities.  
It demonstrates concepts of **Object-Oriented Programming (OOP)**, **JDBC (MySQL Database Connectivity)**, **File Handling**, and **Data Structures**.  

This project is part of **Pre-Placement Activities by Marvellous Infosystems** and provides both **practical learning** and **hands-on coding experience**.

---

##  Key Features
-  **Add Study Logs** → Insert date, subject, duration, and description of your study session.  
-  **View Logs** → Display all study logs stored in the MySQL database.  
-  **Export to CSV** → Save study data into a CSV file for external analysis.  
-  **Summary by Date** → Get total study hours grouped by date.  
-  **Summary by Subject** → Get subject-wise total study hours.  
-  **Database Integration** → Uses **MySQL** with JDBC for persistent storage.  
-  **File Handling** → Generates structured CSV reports.  
-  **OOP Principles** → Classes like `StudyLog`, `StudyTracker`, and `DBHelper` designed with clean abstractions.

---

##  Learning Outcomes
By working on this project, you will learn:  
-  How to use **Java Collections (ArrayList, TreeMap)**.  
-  Implementation of **JDBC with MySQL**.  
-  **CRUD operations** using SQL (`INSERT`, `SELECT`).  
-  How to design classes with **Encapsulation & Abstraction**.  
-  File handling in Java using `FileWriter`.  
-  Generating reports (`CSV export`).  
-  Writing clean, documented, and reusable code.  

---

##  Example Usage

### 1 Insert a Study Log
```java
StudyTracker tracker = new StudyTracker();
StudyLog log = new StudyLog(LocalDate.of(2025, 9, 17), "Java", 2.5, "Revised JDBC concepts");
tracker.InsertLog(log);
