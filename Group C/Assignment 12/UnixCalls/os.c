

#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>

void main()
{
	pid_t pid,pid1;
	int status,op,res;
	char *argv[]={"NULL"};

	do{
	printf("\nSelect option: \n1. PS Command\n2. Join Command\n3. System Calls(fork,wait,execv)\n4. Exit\n");
	scanf("%d",&op);
	switch(op)
	{
	case 1:printf("\n\n{ps}: processes related to this terminal including bash\n");
		system("ps");
		sleep(2);
		printf("\n\n{ps -a}: related to all open terminals\n");
		system("ps -a");
		sleep(2);
		printf("\n\n{ps r}: processes related to this terminal including bash\n");
		system("ps r");
		sleep(2);
		printf("\n\n{ps -A}: running process related to this terminal except bash with Status\n");
		system("ps -A");
		sleep(2);
		printf("\n\n{top}: display Linux processes\n");
		system("top");
		sleep(2);
		break;
	case 2:printf("\n\n{join first second}: joins contents(values) of two files based on key\n");
		system("join first.txt second.txt");
		sleep(2);
		break;
	case 3: pid=fork();	//system call
	if(pid<0)
	{
		printf("\nUnsuccessful execution\n");
		exit(1);	//system call
	}
	else if(pid>0)
	{
		printf("\nI am parent(started) and spawned new child with PID=%d\n",pid);
		pid1=wait(&status);	//system call
		printf("\nParent terminating.. & saying: child with PID=%d is terminated\n",pid1);
	}
	else if(pid==0)
	{
		printf("\nI am child(started) with PID=%d\n",pid);
		sleep(3);
		execv("/usr/bin/firefox",argv);	//system call
		printf("\nChild terminating..\n");
	}
		break;
	case 4: break;
	default: printf("\nInvalid option\n");

	}
	}while(op!=4);
	
	printf("\n\nBYE..!!\n\n");
}
