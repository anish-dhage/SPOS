package schedulingAlgo;

import java.util.ArrayList;
import java.util.Arrays;

public class RoundRobinP extends SchedulingAlgo{
	int processIDs[] = {1,2,3};
	int burstTimes[] = {24,3,3};
	int arrivalTimes[] = {0,1,9};
	int waitingTime[];
	int turnaroundTime[];
	int quantum = 4;
	float aWT = 0; float aTT = 0;
	int remainingTime[];
	
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
		int curr_arrive = 0;
		int flag = 0;
		ArrayList<Integer> arrivedEle = new ArrayList<Integer>();
		while(true){
			if(arrivalTimes[curr_arrive] <= time && flag != -1){
				
				if(curr_arrive < arrivalTimes.length-1 && flag != -1){
					arrivedEle.add(curr_arrive);
					curr_arrive++;
				}
				else{
					arrivedEle.add(curr_arrive);
					flag = -1;
				}
				//System.out.println(arrivedEle);

			}			
			
			for(int j = 0; j<arrivedEle.size(); j++){
				if(arrivalTimes[curr_arrive] <= time && flag != -1){
					
					if(curr_arrive < arrivalTimes.length-1 && flag != -1){
						arrivedEle.add(curr_arrive);
						curr_arrive++;
					}
					else{
						arrivedEle.add(curr_arrive);
						flag = -1;
					}
					//System.out.println(arrivedEle);
				}
				
				
				
				if(remainingTime[arrivedEle.get(j)] > quantum ){
					remainingTime[arrivedEle.get(j)] -= quantum;
					workingTime[arrivedEle.get(j)] += quantum;
					time += quantum;
				}
				else if(remainingTime[arrivedEle.get(j)] != 0){
					workingTime[arrivedEle.get(j)] += remainingTime[arrivedEle.get(j)];
					time += remainingTime[arrivedEle.get(j)];
					remainingTime[arrivedEle.get(j)] = 0;	
					endTime[arrivedEle.get(j)] = time;
					//System.out.println(endTime[j]);
				}
			}
			if (time>= totalTime){
				break;
			}
		}
		for(int j = 0; j<processIDs.length; j++){
			//System.out.println(j+" "+endTime[j] + " "+workingTime[j]);
			waitingTime[j] = endTime[j] - workingTime[j] - arrivalTimes[j];
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
		System.out.println("Pid\tBurstTime\tArrival Times\tWaitingTime\tTurnAroundTime");
		for (int i = 0; i<processIDs.length; i++){
			System.out.println(processIDs[i]+"\t"+burstTimes[i]+"\t\t"+arrivalTimes[i]+"\t\t"+waitingTime[i]+"\t\t"+turnaroundTime[i]);
		}
		System.out.println("Average Waiting Time = "+aWT);
		System.out.println("Average Turnaround Time = "+aTT);

	}

	
}
