package service;

import model.LendingRecord;

import java.util.List;

public class OverdueMonitor extends Thread{
    final LibraryService libraryService;
    final long intervalMillis;


    public OverdueMonitor(LibraryService libraryService, long intervalMillis) {
        this.libraryService = libraryService;
        this.intervalMillis = intervalMillis;
    }
    @Override
    public void run() {
        while(true){
            try{
                List<LendingRecord> overdueRecords = libraryService.getOverdueRecords();
                System.out.println("Checking for overdue books......");

                if(overdueRecords.isEmpty()){
                    System.out.println("No overdue books found");
                }
                else{
                    System.out.println("Overdue books found: ");
                    for(LendingRecord record : overdueRecords){
                        System.out.println("Book : " +record.getBook().getTitle() + " | Member : " + record.getMember().getName() + " | Due : " + record.getDueDate());
                    }
                }
                Thread.sleep(intervalMillis);
            }
            catch (InterruptedException e){
                System.out.println("Overdue monitor interrupted :( .");
                break;
            }
        }
    }

}
