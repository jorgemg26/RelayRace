# 🏁 Relay Race Simulation
A Java app that simulates a relay race using multithreading
Each runner is executed as a thread, and teams are synchronized to ensure runners compete in order

## Features
- Multithreading using `Thread`
- Synchronization with `synchronized`, `wait()` and `notifyAll()`
- Dynamic input for teams and runners
- Random race time (2000–10000 ms)
- Team total time calculation
- Winner team detection
- Best runner per team
- Best runner overall
- Automatic finish detection (no fixed delays)

## How It Works
- Each `Runner` is a thread
- Each `Team` controls execution order using a shared `turn`
- Threads wait using `wait()` until it is their turn
- When a runner finishes:
  _ Updates team stats
  _ Advances the turn
  _ Calls `notifyAll()`
- Each team notifies the `Main` class when it finishes
- When all teams are done, results are calculated automatically

## Constraints (limits ensure stable execution and avoid excessive thread usage)
- Teams: 3 – 10
- Runners per team: 3 – 10
- Time per runner: 2000 – 10000 ms

## How to Run
1. Open the project in NetBeans / IntelliJ / Eclipse
2. Make sure the main class is relayRace.Main
3. Run the program
4. Enter the number of teams and runners

---

## Example Output
Runner 1 from Team 1 starts
Runner 1 from Team 1 finishes (5234 ms)

Team 1 finished. Total time: 24000 ms
Best runner of Team 1: Runner 2 (5100 ms)

WINNER TEAM: Team 3 (23000 ms)
BEST RUNNER: Runner 32 from Team 3 (5050 ms)
