import exception.TaskNotFoundException;
import model.Employee;
import model.Task;
import model.TaskStatus;
import service.TaskManager;
import service.TaskMonitor;
import util.TaskUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        TaskMonitor monitor = null;
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\n=== Employee Task Tracker Menu ===");
            System.out.println("1. Register Employee");
            System.out.println("2. Create and Assign Task");
            System.out.println("3. Show Tasks by Employee");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Search Tasks by Keyword");
            System.out.println("6. Show Tasks Due Tomorrow");
            System.out.println("7. Show Overdue Tasks");
            System.out.println("8. Show Busy Employees (more than 3 pending tasks)");
            System.out.println("9. Start Task Monitor");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Employee ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Department: ");
                        String dept = scanner.nextLine();

                        Employee emp = new Employee(id, name, dept);
                        taskManager.getAllEmployeeTasks().putIfAbsent(emp, new java.util.ArrayList<>());
                        System.out.println("Employee registered successfully.");
                    }

                    case 2 -> {
                        System.out.print("Enter Employee ID: ");
                        int empId;
                        try {
                            empId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            break;
                        }

                        Employee emp = taskManager.getAllEmployeeTasks().keySet().stream()
                                .filter(e -> e.getId() == empId)
                                .findFirst()
                                .orElse(null);

                        if (emp == null) {
                            System.out.println("Employee not found.");
                            break;
                        }

                        System.out.print("Enter Task ID: ");
                        int taskId;
                        try {
                            taskId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            break;
                        }

                        System.out.print("Enter Description: ");
                        String desc = scanner.nextLine();

                        System.out.print("Enter Priority (1=High, 5=Low): ");
                        int priority;
                        try {
                            priority = Integer.parseInt(scanner.nextLine());
                            if (priority < 1 || priority > 5) {
                                System.out.println("Invalid priority. Please enter a value between 1 and 5.");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            break;
                        }

                        System.out.print("Enter Due Date (yyyy-mm-dd): ");
                        LocalDate dueDate;
                        try {
                            dueDate = LocalDate.parse(scanner.nextLine());
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                            break;
                        }

                        Task task = new Task(taskId, desc, TaskStatus.PENDING, dueDate, priority);
                        taskManager.assignTask(emp, task);
                        System.out.println("Task assigned.");
                    }

                    case 3 -> {
                        System.out.print("Enter Employee ID: ");
                        int empId = Integer.parseInt(scanner.nextLine());

                        Employee emp = taskManager.getAllEmployeeTasks().keySet().stream()
                                .filter(e -> e.getId() == empId)
                                .findFirst()
                                .orElse(null);

                        if (emp == null) {
                            System.out.println("Employee not found.");
                            break;
                        }

                        List<Task> tasks = taskManager.getTaskByEmployee(emp);
                        tasks.forEach(System.out::println);
                    }

                    case 4 -> {
                        System.out.print("Enter Employee ID: ");
                        int empId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Task ID to mark completed: ");
                        int taskId = Integer.parseInt(scanner.nextLine());

                        Employee emp = taskManager.getAllEmployeeTasks().keySet().stream()
                                .filter(e -> e.getId() == empId)
                                .findFirst()
                                .orElse(null);

                        if (emp != null) {
                            taskManager.markTaskAsCompleted(emp, taskId);
                            System.out.println("Task marked as completed.");
                        } else {
                            System.out.println("Employee not found.");
                        }
                    }

                    case 5 -> {
                        System.out.print("Enter Employee ID: ");
                        int empId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter keyword: ");
                        String keyword = scanner.nextLine();

                        Employee emp = taskManager.getAllEmployeeTasks().keySet().stream()
                                .filter(e -> e.getId() == empId)
                                .findFirst()
                                .orElse(null);

                        if (emp != null) {
                            List<Task> result = taskManager.searchTaskByKeyword(emp, keyword);
                            result.forEach(System.out::println);
                        } else {
                            System.out.println("Employee not found.");
                        }
                    }

                    case 6 -> {
                        System.out.println("Tasks due tomorrow:");
                        for (List<Task> tasks : taskManager.getAllEmployeeTasks().values()) {
                            List<Task> tomorrowTasks = TaskUtils.getTasksDueTomorrow(tasks);
                            tomorrowTasks.forEach(System.out::println);
                        }
                    }

                    case 7 -> {
                        System.out.println("Overdue tasks:");
                        List<Task> overdue = taskManager.getAllOverdueTasks();
                        overdue.forEach(System.out::println);
                    }

                    case 8 -> {
                        System.out.println("Employees with more than 3 pending tasks:");
                        Map<Employee, Long> busy = TaskUtils.getEmployeesWithMoreThanXPendingTasks(
                                taskManager.getAllEmployeeTasks(), 3
                        );
                        busy.forEach((e, count) -> System.out.println(e + " → " + count + " pending tasks"));
                    }

                    case 9 -> {
                        if (monitor == null || !monitor.isAlive()) {
                            monitor = new TaskMonitor(taskManager);
                            monitor.start();
                            System.out.println("Task Monitor started.");
                        } else {
                            System.out.println("Monitor is already running.");
                        }
                    }

                    case 10 -> {
                        if (monitor != null) monitor.interrupt();
                        System.out.println("Exiting system. Goodbye!");
                        System.exit(0);
                    }

                    default -> System.out.println("Invalid choice. Try again.");
                }
        }catch (TaskNotFoundException e){
                System.out.println("Error : " + e.getMessage());
            }catch (Exception e) {
                System.out.println("Something went wrong : " + e.getMessage());
            }
        }
    }
}