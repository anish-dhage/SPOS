package schedulingAlgo;

public class fcfs {
	
	int processIDs[] = {1,2,3,4,5};
	int burstTimes[] = {300,125,400,150,100};
	int arrivalTimes[] = {0,1,2,3,4};
	int waitingTime[];
	int turnaroundTime[];
	float aWT = 0; float aTT = 0;
	int aggregateBT[];
			
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
		waitingTime = new int[processIDs.length];
		waitingTime[0] = 0;
		aggregateBT = new int[waitingTime.length];
		aggregateBT[0] = burstTimes[0];
		for (int i = 1; i<arrivalTimes.length; i++){
			aggregateBT[i] =  aggregateBT[i-1] + burstTimes[i];
		}
		for (int i = 1; i<arrivalTimes.length; i++){
			waitingTime[i] = aggregateBT[i-1] - arrivalTimes[i];
		}	
	}
	
	void turnaroundTime(){
		turnaroundTime = new int[processIDs.length];
		for (int i = 0; i<arrivalTimes.length; i++){
			turnaroundTime[i] = waitingTime[i] + burstTimes[i];
		}
	}
	
	void avgWT_TT(){
		for (int i = 0; i<arrivalTimes.length; i++){
			aWT += waitingTime[i];
			aTT += turnaroundTime[i];
		}
		aWT = aWT/processIDs.length;
		aTT = aTT/processIDs.length;
	}
	
	void display(){
		System.out.println("Pid\tBurstTime\tArrivalTime\tWaitingTime\tTurnAroundTime");
		for (int i = 0; i<arrivalTimes.length; i++){
			System.out.println(processIDs[i]+"\t"+burstTimes[i]+"\t\t"+arrivalTimes[i]+"\t\t"+waitingTime[i]+"\t\t"+turnaroundTime[i]);
		}
		System.out.println("Average Waiting Time = "+aWT);
		System.out.println("Average Turnaround Time = "+aTT);
		System.out.println();
		for(int i = 0; i<arrivalTimes.length; i++){
			System.out.print(processIDs[i]);
			for(int j = 0; j<burstTimes[i]; j++){
				System.out.print(" ");
			}	
		}
		System.out.println();
		System.out.print("0");
		for(int i = 0; i<arrivalTimes.length; i++){
			for(int j = 0; j<burstTimes[i]-1; j++){
				System.out.print(" ");
			}	
			System.out.print(aggregateBT[i]);
		}
	}
}
