package project4;

import java.util.Collections;
import java.util.LinkedList ;
import java.util.Random ;

public class MemoryManager 
{

	public static void main(String[] args) 
	{
			Random random__X	= new Random();
			int QREADY__T = 10 ;	final int mem__T = 256;
			
			LinkedList<PCB> QReady	= new LinkedList<PCB>();	//#0010 Create List QReady
			LinkedList<PCB> QMemOpen	= new LinkedList<PCB>();	//#0020 Create List QMemOpen
			LinkedList<PCB> QMemUsed	= new LinkedList<PCB>();	//#0030 Create List QMemUsed
			
			QMemOpen.add(new PCB(mem__T));	//#0032 Set the Open Memory
							
			PCB PCB_Ready	; 	//#0040 Create the field: PCB_Ready

			for (int ii = 0; ii < QREADY__T; ii++)
			{
				PCB pcb0 = new PCB();		//#0050 new PCB STATE:New
				pcb0.set_state("Ready");	//#0060 set PCB STATE:Ready
				QReady.add(pcb0) ;			//#0070 Add PCB object onto QReady 
			}
			
			for (PCB loopI : QReady)
				System.out.printf("%s\n"	,loopI.showPCB()) ;
			
			//	End of Initialization \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	   	
			//#0100	Iterator Interface	\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
			
			OpenMem om = new OpenMem();
			
			//#0120	create the iterator for QReady
			java.util.Iterator<PCB> ix = QReady.iterator();
			
			//#0130	iterate through QReady
			while (ix.hasNext())		
			{
				PCB_Ready = ix.next();	//#0140	
				
				//#0150	Looking for open memory
				boolean memFound__B	= om.findOpenMem(PCB_Ready, QMemOpen, QMemUsed);
				
				for (PCB loopI : QMemOpen)
					System.out.printf("\n@020 QOpen\t%s\n"	,loopI.showPCB());
				
				for (PCB loopI : QMemUsed)
					System.out.printf("@0200 QUsed\t%s\n"	,loopI.showPCB());
							
				if (!memFound__B)	//#0160
				{
					System.out.printf("##### No Memory Available for Process:%d\tneeding:%d\n"
							,PCB_Ready.get_ID() 
							,PCB_Ready.get_Limit()	);
					continue ;	// skip to the next process
				}
				System.out.println("=====");
			}
			
			//#0400	simulate memory free process
			
			while (!QMemUsed.isEmpty())
			{
				int QIndex	= random__X.nextInt(QMemUsed.size()) ;
				
				//	free the memory 
				System.out.printf("@0400 Removing: %d\n", QIndex) ;
				PCB PCB_Done = QMemUsed.remove(QIndex);

				//	add the memory back to the QMemOpen
				QMemOpen.add(PCB_Done);		
				
				for (PCB loopI : QMemOpen)
					System.out.printf("@0420 Open\t%s\n"	,loopI.showPCB());
				
				for (PCB loopI : QMemUsed)
					System.out.printf("@0440 Used\t%s\n"	,loopI.showPCB());
							
				System.out.println("=====");
			}
			
			//#0500 Sort the QMemOpen 
			
			Collections.sort(QMemOpen);
			for (PCB loopI : QMemOpen)
			{
				System.out.printf("@0500 sorted\t%s\n", loopI.showPCB()) ;
			}
	}
			//#0600 Defragmentation processing
}