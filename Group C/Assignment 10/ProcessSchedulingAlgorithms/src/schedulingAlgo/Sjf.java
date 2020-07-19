package schedulingAlgo;

import java.util.ArrayList;
import java.util.Arrays;


public class Sjf {
	int processIDs[] = {1,2,3,4,5,6};
	int burstTimes[] = {300,125,400,150,100,50};
	int arrivalTimes[] = {0,1,2,3,4,150};
	int waitingTime[];
	int turnaroundTime[];
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
		for (int i = 0; i<arrivalTimes.length; i++){
			totalTime += burstTimes[i];
		}
		waitingTime = new int[processIDs.length];
		Arrays.fill(waitingTime, 0);
		remainingTime = new int[processIDs.length];
		remainingTime = burstTimes.clone();
		
		int curr = -1;
		int j = 0;
		
		ArrayList<Integer> arrivedEle = new ArrayList<Integer>();
		for (int i = 0; i<totalTime; i++){
			
			if(i == arrivalTimes[j]){
				arrivedEle.add(j);
				if(j < arrivalTimes.length - 1){
					j++;
				}
			}
			if(arrivedEle.isEmpty()){
				continue;
			}
			
			curr = shortest(arrivedEle);

			remainingTime[curr] -= 1;
			
			//increment waiting time for all arrived elements except current element	
			for (int k = 0; k<arrivedEle.size(); k++){
				if((Integer) arrivedEle.get(k) != curr && remainingTime[(Integer) arrivedEle.get(k)] != 0){
					waitingTime[(Integer) arrivedEle.get(k)] += 1;
				}
			}	
		}
	}

	//return arrived element with shortest remaining time
	private int shortest(ArrayList<Integer> arrivedEle) {
		int shortest = 0;
		for (int i = 0; i<arrivedEle.size(); i++){
			if(remainingTime[(Integer) arrivedEle.get(i)] != 0){
				shortest = (Integer) arrivedEle.get(i);
				break;
			}
		}
		for (int i = 0; i<arrivedEle.size(); i++){
			if(remainingTime[(Integer) arrivedEle.get(i)] < remainingTime[shortest]){
				if(remainingTime[(Integer) arrivedEle.get(i)] != 0){
					shortest = (Integer) arrivedEle.get(i);
				}
			}
		}
		return shortest;
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
		System.out.println("Pid\tBurstTime\tArrivalTimes\tWaitingTime\tTurnAroundTime");
		for (int i = 0; i<processIDs.length; i++){
			System.out.println(processIDs[i]+"\t"+burstTimes[i]+"\t\t"+arrivalTimes[i]+"\t\t"+waitingTime[i]+"\t\t"+turnaroundTime[i]);
		}
		System.out.println("Average Waiting Time = "+aWT);
		System.out.println("Average Turnaround Time = "+aTT);

	}

}
