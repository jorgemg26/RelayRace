package relayRace;

import classes.Team;
import java.util.Scanner;

public class Main {
    
    //Global vars to calculate when the teams finish
    static int finishedTeams = 0;
    static int totalTeams;
    static Team[] teams;

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        //Ask for the number of teams
        int numTeams;
        do{
            System.out.println("Enter the number of teams (3-10): ");
            numTeams = scan.nextInt();
        }
        while (numTeams<3 || numTeams>10);
        totalTeams = numTeams;  //To use it in the method teamFinished
        
        //Ask for the number of runners of each team
        int numRunners;
        do{
            System.out.println("Enter the number of runners for each team (3-10): ");
            numRunners = scan.nextInt();
        }
        while (numRunners<3 || numRunners>10);
        
        //Create the teams
        teams = new Team[numTeams];
        for (int i=0; i<numTeams; i++){
            teams[i] = new Team(i+1, numRunners);
        }
        
        //Start the race
        for (Team t : teams){
            t.startRace();
        }
    }
    
    
    //This method calls each team when it finishes
    public static synchronized void teamFinished(){
        finishedTeams++;    //We add 1 finished team
        //If all teams finished, we execute the showResults() method
        if (finishedTeams == totalTeams){
            showResults();
        }
    }
    //This method calculates and shows the results when all the teams finished
    public static void showResults(){
        //Look for the winner team
        Team winner = teams[0];
        for (Team t : teams){
            if (t.getTotalTime() < winner.getTotalTime()){
                winner = t;
            }
        }
        System.out.println("WINNER TEAM: Team " + winner.getTeamNumber() + " (" + winner.getTotalTime() + " ms)");
        
        //Look for the best global runner
        Team bestTeam = teams[0];
        for (Team t : teams){
            if (t.getBestTime() < bestTeam.getBestTime()){
                bestTeam = t;
            }
        }
        System.out.println("BEST RUNNER: Runner " + bestTeam.getBestRunnerId() + " from Team " + bestTeam.getTeamNumber() + " (" + bestTeam.getBestTime() + "ms)");
    }
}
