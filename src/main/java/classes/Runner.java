package classes;

import java.util.Random;

public class Runner extends Thread {
    
    private int id;
    private Object lock;    //Object to synchronize (shared in team)
    private Team team;
    private int position;   //The position of the runner
   
    public Runner (int id, Object lock, Team team, int position){
        this.id = id;
        this.lock = lock;
        this.team = team;
        this.position = position;
    }
    @Override
    public void run(){
        //Only one thread can be here at the same time
        synchronized(lock){
            try{
                while (team.getTurn() != position){
                    lock.wait();    //Waits to its turn
                }
                
                //Starts to run
                System.out.println("Runner " + id + " from Team " + team.getTeamNumber() + " starts");
                
                //Random time from 4000 to 10000
                Random rand = new Random();
                int time = 4000 + rand.nextInt(6001);
                
                //The runner is running the random time
                Thread.sleep(time);
                
                //When it finishes
                System.out.println("Runner " + id + " from Team " + team.getTeamNumber() + " finishes (" + time + "ms)");
                
                //Save the time of the team
                team.addTime(time);
                
                //Check if the runner is the best
                team.checkBestRunner(id, time);
                
                //Pass to the next turn
                team.nextTurn();
                
                //Wake up the other threads
                lock.notifyAll();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
