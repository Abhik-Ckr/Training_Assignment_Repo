import model.*;
import service.LibraryService;
import service.OverdueMonitor;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        Scanner scanner = new Scanner(System.in);
        OverdueMonitor monitor = null;

        while (true) {
            System.out.println("\n === Library System Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Add Overdue Record (Manual)");
            System.out.println("6. Start Overdue Monitor");
            System.out.println("7. Show Overdue Records");
            System.out.println("8. Exit");
            System.out.print(" Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Book ID: ");
                        String bookId = scanner.nextLine();
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();

                        Book book = new Book(bookId, title, author);
                        libraryService.addBook(book);
                        System.out.println("Book added.");
                    }

                    case 2 -> {
                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();

                        Member member = new Member(memberId, name, email);
                        libraryService.registerMember(member);
                        System.out.println("Member registered.");
                    }

                    case 3 -> {
                        System.out.print("Enter Book ID to issue: ");
                        String bookId = scanner.nextLine();
                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();

                        libraryService.issueBook(bookId, memberId);
                        System.out.println(" Book issued.");
                    }

                    case 4 -> {
                        System.out.print("Enter Book ID to return: ");
                        String bookId = scanner.nextLine();
                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();

                        libraryService.returnBook(bookId, memberId);
                        System.out.println(" Book returned.");
                    }

                    case 5 -> {
                        System.out.print("Enter Book ID for overdue record: ");
                        String bookId = scanner.nextLine();
                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();

                        Book book = libraryService.searchBooksByTitle("").stream()
                                .filter(b -> b.getBookId().equals(bookId))
                                .findFirst().orElse(null);
                        Member member = libraryService.searchMemberByEmail("").stream()
                                .filter(m -> m.getMemberId().equals(memberId))
                                .findFirst().orElse(null);

                        if (book == null || member == null) {
                            System.out.println(" Book or Member not found.");
                            break;
                        }

                        book.setStatus(BookStatus.ISSUED);
                        LendingRecord overdueRecord = new LendingRecord(
                                UUID.randomUUID().toString(),
                                book,
                                member,
                                LocalDate.now().minusDays(20),
                                LocalDate.now().minusDays(5),
                                null
                        );
                        libraryService.addLendingRecord(overdueRecord);
                        System.out.println("⚠Overdue record added manually.");
                    }

                    case 6 -> {
                        System.out.print("Enter monitor interval in seconds: ");
                        int interval = Integer.parseInt(scanner.nextLine()) * 1000;
                        if (monitor == null || !monitor.isAlive()) {
                            monitor = new OverdueMonitor(libraryService, interval);
                            monitor.start();
                            System.out.println("Overdue Monitor started.");
                        } else {
                            System.out.println("Monitor already running.");
                        }
                    }

                    case 7 -> {
                        var overdue = libraryService.getOverdueRecords();
                        if (overdue.isEmpty()) {
                            System.out.println("No overdue records.");
                        } else {
                            System.out.println("Overdue Records:");
                            overdue.forEach(record -> System.out.println(
                                    " " + record.getBook().getTitle() +
                                            " | Member: " + record.getMember().getName() +
                                            " | Due: " + record.getDueDate()
                            ));
                        }
                    }

                    case 8 -> {
                        System.out.println("Exiting...");
                        System.exit(0);
                    }
                    case 9 -> {
                        System.out.print("Enter Author Name: ");
                        String author = scanner.nextLine();
                        var books = libraryService.searchBooksByAuthor(author);
                        if (books.isEmpty()) {
                            System.out.println("No books found by this author.");
                        } else {
                            books.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
                        }
                    }

                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println(" Error: " + e.getMessage());
            }
        }
    }
}
