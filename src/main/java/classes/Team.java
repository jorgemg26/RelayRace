package classes;

import classes.Runner;
import relayRace.Main;

public class Team {
    
    private Runner[] runners;
    private Object lock;            //Object shared to synchronize
    private int turn = 0;           //To control who runs in the team
    private int teamNumber;         //Number of the team
    private long totalTime = 0;     //Total time of the team
    private int bestRunnerId;       //Best runner of the team
    private int bestTime = 999999;
    private int numRunners;
    
    public Team (int teamNumber, int numRunners){
        this.teamNumber = teamNumber;
        this.numRunners = numRunners;
        
        lock = new Object();    //Each team has its own lock
        
        runners = new Runner[numRunners];
        
        for (int i=0; i<numRunners; i++){
            runners[i] = new Runner(i+1, lock, this, i);   //The id is the team number + the position, "this" references the team and the lap of the loop is the turn
        }
    }
    
    //Returns the current turn
    public int getTurn(){
        return turn;
    }
    public void nextTurn(){
        turn++;
        //We check if the runners finished
        if (turn == numRunners){
            System.out.println();
            System.out.println("Team " + teamNumber + " finished. Total time: " + totalTime + "ms");
            System.out.println("Best runner of the Team " + teamNumber + ": Runner " + bestRunnerId + " (" + bestTime + "ms)\n");
            Main.teamFinished();    //We tell the Main that the team finished
        }
    }
    
    //Add time to the team
    public void addTime(int time){
        totalTime += time;
    }
    
    //To check if it is the best runner of the team
    public void checkBestRunner (int id, int time){
        if (time < bestTime){   //If the new time is better than the best registered, we change the best time and the best runner
            bestTime = time;
            bestRunnerId = id;
        }
    }
    public long getTotalTime(){
        return totalTime;
    }
    public int getTeamNumber(){
        return teamNumber;
    }
    public int getBestTime(){
        return bestTime;
    }
    public int getBestRunnerId(){
        return bestRunnerId;
    }
    //Start all the threads
    public void startRace(){
        for (Runner r: runners){
            r.start();
        }
    }
    //Tell the main that 
}
