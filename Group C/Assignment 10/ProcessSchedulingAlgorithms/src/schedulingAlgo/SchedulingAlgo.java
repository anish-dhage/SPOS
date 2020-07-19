/*
 * Write a Java program (using OOP features) to implement following scheduling algorithms:
 FCFS , SJF (Preemptive), Priority (Non-Preemptive) and Round Robin (Preemptive)
 * */

package schedulingAlgo;

abstract class SchedulingAlgo {

	public static void main(String[] args) {
		
		System.out.println("FCFS Scheduling");
		fcfs fcfs_obj = new fcfs();
		fcfs_obj.sortArrivalTimes();
		fcfs_obj.waitingTimes();
		fcfs_obj.turnaroundTime();
		fcfs_obj.avgWT_TT();
		fcfs_obj.display();
		
		System.out.println();
		System.out.println("\nPriority Scheduling");
		
		PriorityScheduling priority_obj = new PriorityScheduling();
		priority_obj.sortpriority();
		priority_obj.waitingTimes();
		priority_obj.turnaroundTime();
		priority_obj.avgWT_TT();
		priority_obj.display();
		
		System.out.println();
		System.out.println("\nSRT Scheduling");
		Sjf sjf_obj = new Sjf();
		sjf_obj.sortArrivalTimes();
		sjf_obj.waitingTimes();
		sjf_obj.turnaroundTime();
		sjf_obj.avgWT_TT();
		sjf_obj.display();
		
		
		System.out.println();
		System.out.println("\nRoundRobin Scheduling");
		RoundRobin rr_obj = new RoundRobin();
		rr_obj.waitingTimes();
		rr_obj.turnaroundTime();
		rr_obj.avgWT_TT();
		rr_obj.display();
		
		System.out.println();
		System.out.println("\nRoundRobinPremptive Scheduling");
		RoundRobinP rr_obj1 = new RoundRobinP();
		rr_obj1.waitingTimes();
		rr_obj1.turnaroundTime();
		rr_obj1.avgWT_TT();
		rr_obj1.display();
	}


}
