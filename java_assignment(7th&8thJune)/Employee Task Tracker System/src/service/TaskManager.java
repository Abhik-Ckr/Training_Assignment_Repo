package service;

import exception.TaskNotFoundException;
import model.Employee;
import model.Task;
import model.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    final Map<Employee, List<Task>> employeeTask = new HashMap<>();

    public void assignTask(Employee employee, Task task) {
        employeeTask.computeIfAbsent(employee, k -> new ArrayList<>()).add(task);
    }

    public List<Task> getTaskByEmployee(Employee employee) {
        return employeeTask.getOrDefault(employee, new ArrayList<>());
    }

    public void markTaskAsCompleted(Employee employee, int taskId) throws TaskNotFoundException {
        List<Task> tasks = employeeTask.get(employee);
        if (tasks == null)
            throw new TaskNotFoundException("Employee has no tasks assigned");

        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setStatus(TaskStatus.COMPLETED);
                return;
            }
        }
        throw new TaskNotFoundException("Task with ID  " + taskId + " not found for employee");
    }

    public List<Task> searchTaskByKeyword(Employee employee, String keyword) {
        List<Task> result = new ArrayList<>();
        List<Task> tasks = employeeTask.getOrDefault(employee, new ArrayList<>());
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> getAllOverdueTasks() {
        List<Task> overdueTask = new ArrayList<>();
        for (List<Task> tasks : employeeTask.values()) {
            for (Task task : tasks) {
                if (task.getStatus() != TaskStatus.COMPLETED && task.getDueDate().isBefore(LocalDate.now())) {
                    overdueTask.add(task);
                }
            }
        }
        return overdueTask;
    }

    public Map<Employee, List<Task>> getAllEmployeeTasks() {
        return employeeTask;
    }

}
