package project;

public class TIMER
{
	
	private float millisPerCycle;
	
	private long lastUpdate;
	
	private int elapsedCycles;
	
	private float excessCycles;
	
	private boolean isPaused;
	
	public TIMER(float cyclesPerSecond) 
        {
		setCyclesPerSecond(cyclesPerSecond);
		reset();
	}
	
	public void setCyclesPerSecond(float cyclesPerSecond) 
        {
		this.millisPerCycle = (1.0f / cyclesPerSecond) * 1000;
	}
	
	public void reset() 
        {
		this.elapsedCycles = 0;
		this.excessCycles = 0.0f;
		this.lastUpdate = getCurrentTime();
		this.isPaused = false;
	}
	
	public void update() 
        {
		long currUpdate = getCurrentTime();
                //The integer value of the number of elapsed cycles
		float delta = (float)(currUpdate - lastUpdate) + excessCycles;
		
		if(!isPaused) 
                {
                        //The integer value of the number of elapsed cycles
			this.elapsedCycles += (int)Math.floor(delta / millisPerCycle);
                        //The fraction value of the number of elapsed cycles
			this.excessCycles = delta % millisPerCycle;
		}

		this.lastUpdate = currUpdate;
	}
	
	public void setPaused(boolean paused) 
        {
		this.isPaused = paused;
	}
	

	public boolean isPaused() 
        {
		return isPaused;
	}
	

	public boolean hasElapsedCycle() 
        {
		if(elapsedCycles > 0) 
                {
			this.elapsedCycles--;
			return true;
		}
		return false;
	}
	
	public boolean peekElapsedCycle() 
        {
		return (elapsedCycles > 0);
	}
	
	private static final long getCurrentTime() 
        {
            //from nano to milli
            
		return (System.nanoTime() / 1000000L);
	}

}
