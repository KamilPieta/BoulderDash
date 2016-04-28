package game;

public class TimeCounter {

	public double time=0.00;
	
	public TimeCounter (Boolean start)
	{
		
		Thread t = new Thread()
    {
    @Override
    public void run()
    {
    while(start)
    {
    	time=time+0.01;
    try {
    Thread.sleep(10);
    } catch (InterruptedException e) {
    e.printStackTrace();
    }}}
    };
    t.start(); 
	}
	
	double retTime()
	{	
		return time;
	}

}