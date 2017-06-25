package smb.service.impl;

public class LiftOff implements Runnable{   
    protected int countDown = 10; //Default   
    private static int taskCount = 0;   
    private final int id = taskCount++;    
    public LiftOff() {}   
    public LiftOff(int countDown) {   
        this.countDown = countDown;   
    }   
    public String status() {   
        return "#" + id + "(" +   
            (countDown > 0 ? countDown : "LiftOff!") + ") ";   
    }   
    @Override   
    public void run() {
        YyService service = new YyService();
        service.getYiyuanlistFromUrl();
        while(countDown-- > 0) {   
            System.out.print(status());   
            Thread.yield();   
        }   
           
    }      
}   