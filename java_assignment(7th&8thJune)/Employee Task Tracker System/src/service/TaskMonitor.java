package service;

import model.Task;

public class TaskMonitor extends Thread{
    final TaskManager taskManager;

    public TaskMonitor(TaskManager taskManager){
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while(true){
            try{
                System.out.println("Checking for overdue task.....");
                for(Task task : taskManager.getAllOverdueTasks()){
                    System.out.println("Overdue Task : " + task);
                }
                Thread.sleep(60000);//1minute
            }
            catch (InterruptedException e){
                System.out.println("Task Monitor interrupted");
                break;
            }
        }
    }
}
