package util;

import model.Employee;
import model.Task;
import model.TaskStatus;

import java.security.cert.CertPath;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskUtils {

    public static List<Task> sortTaskByDueDate(List<Task> tasks) {
        return tasks.stream().sorted(Comparator.comparing(Task::getDueDate)).collect(Collectors.toList());
    }

    public static List<Task> getTasksDueTomorrow(List<Task> tasks) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return tasks.stream().filter(task -> task.getDueDate().equals(tomorrow)).collect(Collectors.toList());
    }

    public static List<Task> filterTasksByKeyword(List<Task> tasks, String keyword) {
        return tasks.stream().filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
    }

    public static Map<Employee, Long> getEmployeesWithMoreThanXPendingTasks(Map<Employee, List<Task>> employeeTasks, int threshold){
        return employeeTasks.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().filter(task -> task.getStatus() == TaskStatus.PENDING).count()))
                .entrySet().stream().filter(entry -> entry.getValue() > threshold).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
