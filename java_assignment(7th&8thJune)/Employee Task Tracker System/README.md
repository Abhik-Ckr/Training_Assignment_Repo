# 📋 Employee Task Tracker System

## 🎯 Objective

A **console-based Java application** that allows an admin to:
- Register employees
- Assign tasks
- Track task status (Pending, In Progress, Completed)
- Search and filter tasks
- Automatically monitor overdue tasks using a background thread

---

## ✅ Features Implemented

- 📌 **Employee Registration**  
  Add employees with ID, name, and department.

- 🧾 **Task Creation and Assignment**  
  Create tasks with ID, description, due date, status, and priority.

- 🔍 **Task Viewing & Filtering**
  - View tasks by employee
  - Search tasks by keyword
  - View tasks due tomorrow
  - View overdue tasks

- ✅ **Task Completion**
  Mark tasks as completed by ID.

- ⏱️ **Task Monitor (Multithreading)**
  Background thread that checks and logs overdue tasks every 60 seconds.

- 🧠 **Busy Employee Detection**
  Identify employees with more than 3 pending tasks.

---

## ⚙️ How to Compile and Run

### 📁 Project Structure

```
src/
└──── model/       # Employee, Task, TaskStatus
  ├── service/     # TaskManager, TaskRepository, TaskMonitor
  ├── exception/   # TaskNotFoundException
  ├── util/        # TaskUtils
  └── Main.java    # Entry point
```

### 💻 Compile

From the project root:

```bash
javac -d out src/com/tasktracker/**/*.java
```

### ▶️ Run

```bash
java -cp out com.tasktracker.Main
```

> You must have Java 8 or above installed.

---

## 💬 Sample Input/Output

```
=== Employee Task Tracker Menu ===
1. Register Employee
...
Enter your choice: 1
Enter Employee ID: 101
Enter Name: Abhik
Enter Department: SWE
Employee registered successfully.

Enter your choice: 2
Enter Employee ID: 101
Enter Task ID: 1
Enter Description: Complete Report
Enter Priority (1-5): 2
Enter Due Date (yyyy-mm-dd): 2025-06-10
Task assigned.

Enter your choice: 3
Enter Employee ID: 101
Task{id=1, description='Complete Report', status=PENDING, dueDate=2025-06-10, priority=2}
```

---

## 🔍 Java Concepts Used

| Java Concept              | Application in Project                                  |
|--------------------------|----------------------------------------------------------|
| **OOP / Classes**         | `Employee`, `Task`, `TaskManager`, and `TaskMonitor`     |
| **Enums**                 | `TaskStatus` enum used for task state control           |
| **Collections (Map, List)** | `HashMap<Employee, List<Task>>` to map tasks per employee |
| **Streams & Lambdas**     | Searching/filtering/sorting tasks in `TaskUtils`         |
| **Comparable Interface**  | Natural ordering of `Task` by priority                  |
| **Custom Exception**      | `TaskNotFoundException` when task ID is invalid         |
| **Generics**              | `TaskRepository<T>` handles any type of task            |
| **Multithreading**        | `TaskMonitor` thread checks overdue tasks every minute  |
| **Exception Handling**    | Input validation and graceful error management          |

---


## 📝 Author

Developed by **Abhik** 
