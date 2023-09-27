package project4;

import java.util.LinkedList;

public class OpenMem 
{

	public OpenMem ()
	{
		
	}
	
	public boolean findOpenMem(PCB PCB_Ready, LinkedList<PCB> QMemOpen,LinkedList<PCB> QMemUsed)
	{
		//Check if PCB already went through Running process
		if(QMemUsed.contains(PCB_Ready))
			return true;
		boolean memFound__B = false ;
			
		int memNeed = PCB_Ready.get_Limit() ;	//@0100
		
		for (int ii = 0; ii < QMemOpen.size(); ii ++)
		{
			PCB memOpen = QMemOpen.get(ii) ;	//@0120
			if (memOpen.get_Limit() > memNeed)	//@0200
			{
				memFound__B = true;
				int base0 = memOpen.get_memBase();
				PCB_Ready.set_memBase(base0);				//@0280	set the base for the process
				
				//@0220	split the open memory	@@ QMemOpen @@
				PCB newOpenMem = new PCB(memOpen.get_Limit() - memNeed, memOpen.get_memBase() + PCB_Ready.get_Limit());	//@0280	set the base for the process
				
				//@0240	replace the open memory
				QMemOpen.remove(ii);
				QMemOpen.add(newOpenMem);
					
				//@0260	allocate the used memory	@@ QMemUsed @@
				QMemUsed.add(PCB_Ready);		//@0300	push the used memory
				System.out.printf("@0300 Used\t%s\n"	,PCB_Ready.showPCB());

				break ;	// exit out of the FOR loop for memory
			}
		}
		return memFound__B ;
	}
}
