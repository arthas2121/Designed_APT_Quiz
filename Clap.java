import java.util.Random;

public class Clap{
	public int getNumOfClap(int num) {
			
		int numOfClap = 0;
			
		for(int i = 1; i <= num; i++) {
			if(i % 10 == 7 || i % 7 == 0) {
				numOfClap++;
			}
		}

			
		return numOfClap;
	}
		
	private int cTest = 1;
	private int outType = 3;
	    
	public void test(int num, int expect) {
	    int result = this.getNumOfClap(num);
	    	
	    if(outType == 3){
	    	System.out.println(String.format("%d \t Gave: %d \t Got: %d \t Expected: %d",
	    			cTest, num, result, expect));
	    }
	    	
	    cTest++;
	}
	    
	
	public static void main(String[] args) {
			
		Clap c = new Clap();
		c.test(0, 0);
		c.test(32, 6);
		c.test(43, 9);
		
		Random rand = new Random();
		rand.setSeed(1);
		
		//create 7 more tests
		for(int i = 0; i < 7; i++) {
			//randomize the number of claps from 0 to 22, for example 3 claps, f = 17, s = 21
			int rand_num = rand.nextInt(23);
			
			//index to count the current clap
			int index = 0;
			
			//first_num and second_num to determine the boundary of the possible number
			int first_num = 0, second_num = 0;
			
			//find the lower boundary for the possible number of people according to the generated clap number
			for(int j = 1; j <= 100; j++) {
				if(j % 10 == 7 || j % 7 == 0) index++;			
				if(index == rand_num) {
					first_num = j;
					break;
				}
			}
			
			index = 0;
				
			//find the upper boundary for the possible number of people
			for(int m = 1; m <= 100; m++) {
				if(m % 10 == 7 || m % 7 == 0) index++;
				if(index == (rand_num + 1) || m == 100) {
					second_num = m;
					break;
				}
			}
			
			int rand_numOfPeople = first_num + rand.nextInt(second_num - first_num);
			c.test(rand_numOfPeople, rand_num);
		}
		
	}
	    
}
	