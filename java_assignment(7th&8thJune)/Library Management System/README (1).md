# 📚 Library Management System

A simple Java console-based application that allows a librarian to manage books and members, issue and return books, and track overdue borrowings using multithreading and custom exception handling.

---

## ⚙️ Setup Instructions

1. **Clone the repository** or download the source code.
2. Open the project in any IDE (like IntelliJ IDEA or Eclipse).
3. Ensure the project has the following structure:

```
src/
├── model/
│   ├── Book.java
│   ├── Member.java
│   ├── LendingRecord.java
│   └── BookStatus.java
├── repository/
│   ├── Repository.java
│   ├── BookRepository.java
│   ├── MemberRepository.java
│   └── LendingRecordRepository.java
├── exception/
│   ├── BookNotAvailableException.java
│   ├── MemberNotFoundException.java
│   └── OverdueBookException.java
├── service/
│   ├── LibraryService.java
│   └── OverdueMonitor.java
└── Main.java
```

4. Compile and run `Main.java`.

---

## ✅ Features Implemented

| # | Feature |
|---|---------|
| 1️⃣ | Librarian can add books and register members |
| 2️⃣ | Members can borrow and return books |
| 3️⃣ | Book issuance is denied if the member has overdue books |
| 4️⃣ | OverdueMonitor thread continuously checks for overdue records |
| 5️⃣ | Books can be searched by author (implemented in service layer) |
| 6️⃣ | Overdue records are displayed and tracked in real-time |
| 7️⃣ | Custom exceptions are raised for unavailable books or invalid members |

---

## 💡 Java Concepts Used

| Concept | Usage |
|--------|-------|
| **OOP (Classes, Encapsulation)** | Used to define models: `Book`, `Member`, `LendingRecord` |
| **Generics** | `Repository<T>` allows reusable code for different entities |
| **Collections (Map, List)** | `HashMap`, `ArrayList` used for storing books, members, lending records |
| **Custom Exceptions** | `BookNotAvailableException`, `MemberNotFoundException`, `OverdueBookException` improve error handling |
| **Enum** | `BookStatus` enum tracks whether a book is `AVAILABLE` or `ISSUED` |
| **Streams & Lambdas** | Used for searching, filtering, and listing records cleanly |
| **Multithreading** | `OverdueMonitor` thread runs in the background to check overdue books |
| **Console I/O** | `Scanner` is used for user input in an interactive menu-based system |

---

## 🖥 Sample Console Output

```
=== Library System Menu ===
1. Add Book
2. Register Member
3. Issue Book
4. Return Book
5. Add Overdue Record (Manual)
6. Start Overdue Monitor
7. Show Overdue Records
8. Exit
Enter your choice: 1

Enter Book ID: B001
Enter Title: Java Fundamentals
Enter Author: George St. Pierre
✅ Book added.

Enter your choice: 2
Enter Member ID: M001
Enter Name: Abhik
Enter Email: abhik@gmail.com
✅ Member registered.

Enter your choice: 3
Enter Book ID to issue: B001
Enter Member ID: M001
✅ Book issued.

Enter your choice: 4
Enter Book ID to return: B001
Enter Member ID: M001
✅ Book returned.

Enter your choice: 5
Enter Book ID for overdue record: B001
Enter Member ID: M001
⚠ Overdue record added manually.

📢 [Overdue Monitor] Checking for overdue books...
⚠ Overdue books found:
📚 Java Fundamentals | Member: Abhik | Due: 2024-06-01
```

---

## 📝 Author

Developed by **Abhik** 