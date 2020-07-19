package schedulingAlgo;

import java.util.Arrays;

public class RoundRobin extends SchedulingAlgo{
	int processIDs[] = {1,2,3,4,5};
	int burstTimes[] = {300,125,400,150,100};
	//int arrivalTimes[] = {0,1,2,3};
	int waitingTime[];
	int turnaroundTime[];
	int quantum = 50;
	float aWT = 0; float aTT = 0;
	int remainingTime[];
	/*
	void sortArrivalTimes(){
		for (int i = 0; i<arrivalTimes.length-1; i++){
			for (int j = 0; j<arrivalTimes.length-1; j++){
				if(arrivalTimes[j]>arrivalTimes[j+1]){
					arrivalTimes[j] = swap(arrivalTimes[j+1] , arrivalTimes[j+1] = arrivalTimes[j]);
					burstTimes[j] = swap(burstTimes[j+1] , burstTimes[j+1] = burstTimes[j]);
					processIDs[j] = swap(processIDs[j+1] , processIDs[j+1] = processIDs[j]);
				}
			}
		}
	}
	
	int swap(int a, int b){
		return a;
	}
	*/
	void waitingTimes(){
		int totalTime = 0;
		for (int i = 0; i<processIDs.length; i++){
			totalTime += burstTimes[i];
		}
		waitingTime = new int[processIDs.length];
		Arrays.fill(waitingTime, 0);
		remainingTime = new int[processIDs.length];
		remainingTime = burstTimes.clone();
		int workingTime[] = new int[processIDs.length];
		int endTime[] = new int[processIDs.length];
		int time = 0;
		
		while(true){
			for(int j = 0; j<processIDs.length; j++){
				if(remainingTime[j] > quantum ){
					remainingTime[j] -= quantum;
					workingTime[j] += quantum;
					time += quantum;
				}
				else if(remainingTime[j] != 0){
					workingTime[j] += remainingTime[j];
					time += remainingTime[j];
					remainingTime[j] = 0;	
					endTime[j] = time;
					//System.out.println(endTime[j]);
				}
			}
			if (time>= totalTime){
				break;
			}
		}
		for(int j = 0; j<processIDs.length; j++){
			waitingTime[j] = endTime[j] - workingTime[j] ;
		}

	}
	
	void turnaroundTime(){
		turnaroundTime = new int[processIDs.length];
		for (int i = 0; i<processIDs.length; i++){
			turnaroundTime[i] = waitingTime[i] + burstTimes[i];
		}
	}
	
	void avgWT_TT(){
		for (int i = 0; i<processIDs.length; i++){
			aWT += waitingTime[i];
			aTT += turnaroundTime[i];
		}
		aWT = aWT/processIDs.length;
		aTT = aTT/processIDs.length;
	}
	
	void display(){
		System.out.println("Pid\tBurstTime\tWaitingTime\tTurnAroundTime");
		for (int i = 0; i<processIDs.length; i++){
			System.out.println(processIDs[i]+"\t"+burstTimes[i]+"\t\t"+waitingTime[i]+"\t\t"+turnaroundTime[i]);
		}
		System.out.println("Average Waiting Time = "+aWT);
		System.out.println("Average Turnaround Time = "+aTT);

	}

	
}
