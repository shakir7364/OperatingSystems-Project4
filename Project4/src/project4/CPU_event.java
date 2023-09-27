package project4;

import java.util.Random;

public class CPU_event // this class returns a result from a range by percentage
{
	private int	event ;		
	private int	rangeResult ;
	final static byte pct08 = 5 ;	final static byte pct10	= 10 ; 
	final static byte pct17	= 3 ;	final static byte pct25 = 0 ;
	final static byte pct40	= 2 ;	
	
	public void CPU_event()
	{
		
	}
	
	public int get_CPU_event()
	{
		Random random__X	= new Random();	//#010 declare random object
		
		event = random__X.nextInt(100)+1 ;	
		//#020 instantiate the object and assign to: event
		
		if ((event % pct10) == 0)	//#100	true if event is evenly divisible by 10
			rangeResult = 2 ; 		// (10, 20, 30, 40, 50, 60, 70, 80, 90, 100)	
		else
		if ((event % pct40) == 0)	//#200	true if event is evenly divisible by 2
			rangeResult = 4 ;		// all even no.'s between 1:100 except for the 10 above
		else
		if ((event % pct17) == 0)	//#300	true if event is evenly divisible by 3
			rangeResult = 3 ;		// (3, 9, 15, 21, 27, 33, 39, 45, 51, 57, 63, 69, 75, 81, 87, 93, 99)
		else		
		if ((event % pct08)	== 0)	//#0400	true if event is evenly divisible by 5
			rangeResult = 1 ;		// (5, 25, 35, 55, 65, 75, 85, 95)
		else		
			rangeResult = 5;		//#0500 the rest
			
		return rangeResult ;
	}
}
