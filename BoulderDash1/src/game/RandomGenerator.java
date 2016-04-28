package game;

import java.util.Random;

public class RandomGenerator {
	public int a,b;
	
	public Random generatorX = new Random();
	public Random generatorY = new Random();
	public RandomGenerator(){
		
		this.a=generatorX.nextInt(33);
		this.b=generatorX.nextInt(24);
		
	}
}
