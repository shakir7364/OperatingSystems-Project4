package project4;

import java.util.Random ;
import java.util.concurrent.LinkedBlockingQueue;

public class PCB implements Comparable<PCB>, Runnable
{
	private String 	PCB_state;
	private int		PCB_ID ;
	private String	pgmCounter;
	private int		CPU_used;
	private int		CPU_max;
	private int		timeWaiting;
	private int		memBase ;
	private int		memLimit ;
	
	private LinkedBlockingQueue<PCB>	QReady	;
	private LinkedBlockingQueue<PCB>	QWait	;
	
	private static int	PCB__K;
	@SuppressWarnings("unused")
	private Random random__X ;
		
	// constructor methods
	// default constructor
		
	public PCB ()
	{
		Random random__X	= new Random();

		PCB__K		+= 1 ;	//=====>	Increment the static variable for Process ID
		PCB_state	= "Ready" ;
		PCB_ID		= PCB__K ; 
		pgmCounter	= "" ;
		CPU_used	= 0 ;
		CPU_max		= random__X.nextInt(50) + 1 ;	// Assign max to be between 1:50
		timeWaiting	= 0 ;
		memBase	= 0 ;
		memLimit	= random__X.nextInt(50) + 26 ;	// Assign memory needed between 25:75
	}	
	
	public PCB(LinkedBlockingQueue<PCB> ready, LinkedBlockingQueue<PCB> wait)
	{
		QReady = ready;
		QWait = wait;
		
		Random random__X	= new Random();

		PCB__K		+= 1 ;	//=====>	Increment the static variable for Process ID
		PCB_state	= "Ready" ;
		PCB_ID		= PCB__K ; 
		pgmCounter	= "" ;
		CPU_used	= 0 ;
		CPU_max		= random__X.nextInt(50) + 1 ;	// Assign max to be between 1:50
		timeWaiting	= 0 ;
		memBase	= 0 ;
		memLimit	= random__X.nextInt(50) + 26 ;	// Assign memory needed between 25:75
	}
	
	// constructor for memory tracking
			
	public PCB (int m0)
	{
		PCB__K		= 0 ;	
		PCB_state	= "@" ;
		PCB_ID		= 0 ; 
		pgmCounter	= "" ;
		CPU_used	= 0 ;
		CPU_max		= 0 ; 
		timeWaiting	= 0 ;
		memBase	= 0 ;
		memLimit	= m0 ;
	}
	
	//Constructor that takes memLimit and memBase
	public PCB(int m0, int b0)
	{
		PCB__K		= 0;	
		PCB_state	= "@";
		PCB_ID		= 0; 
		pgmCounter	= "";
		CPU_used	= 0;
		CPU_max		= 0; 
		timeWaiting	= 0;
		memBase	= b0;
		memLimit	= m0;
	}
	
	
	public String showPCB()
	{
		return "state: " + PCB_state
			+ "\tID: "	+ Integer.toString(PCB_ID)
//		//	+ "\tK: "	+ pgmCounter
//			+ "\tCPU used: "	+ Integer.toString(CPU_used)	
//			+ "\tCPU max: "	+ Integer.toString(CPU_max)					
//			+ "\tWait: "	+ Integer.toString(timeWaiting)
			+ "\tmemBase: "	+ Integer.toString(memBase)
			+ "\tmemLimit: "+ Integer.toString(memLimit)
			;
	}

	public int compareTo (PCB pcb0) 
	{
		int mem0	= pcb0.get_memBase();
		return this.memBase - mem0 ;
	}	
	
	//	set methods
	
	public String get_state()
	{
		return PCB_state;
	}
	
	public int get_ID()
	{
		return PCB_ID;
	}
	
	public int get_CPU_used()
	{
		return CPU_used;
	}
	
	public int get_CPU_max()
	{
		return CPU_max;
	}	
	
	public String get_pgmCounter()
	{
		return pgmCounter;
	}	
	
	public int get_timeWaiting()
	{
		return timeWaiting;
	}	
	
	public int get_memBase()
	{
		return memBase;
	}	
	
	public int get_Limit()
	{
		return memLimit;
	}	
	
	// Set methods , void returns no value

	
	public void set_PCB_ID (int id0)
	{
		PCB_ID	= id0 ;
	}
	
	public void set_state(String state0)
	{
		PCB_state	= state0 ;
	}
	
	public void set_CPU_used(int CPU0)
	{
		CPU_used	= CPU0 ;
	}

	public void add_CPU_used(int c0)
	{
		CPU_used	+= c0 ;
	}
	
	public void set_CPU_max(int CPU0)
	{
		CPU_max	= CPU0 ;
	}
	
	public void set_pgmCounter(String pgmCounter0)
	{
		pgmCounter	= pgmCounter0 ;
	}
	
	public void set_timeWaiting(int timeWaiting0)
	{
		timeWaiting	= timeWaiting0 ;
	}
	
	public void add_timeWaiting(int t0)
	{
		timeWaiting	+= t0 ;
	}
	
	public void set_memBase (int m0)
	{
		memBase = m0 ;
	}
	
	public void set_memLimit (int m0)
	{
		memLimit = m0 ;
	}

	@Override
	public void run() 
	{
		try
		{
			System.out.println("Thread Started.");
			Thread.sleep((int)(Math.random() * 2501) + 500);
			QWait.remove(this);
			QReady.add(this);
			System.out.println("Thread Ended.");
			//Thread Terminates when run() ends
		}
		catch(Exception E)
		{
			System.out.println("Exception encountered: " + E.getMessage());
		}
	}
	
	

}