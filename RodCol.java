import java.util.*;

public class RodCol
{
	//Store values and weights
	public static int[] values;
	public static long[] weights;
	//Infinite value for answer array
	public static final long INFINITY = Integer.MAX_VALUE - 1;
	
	public static void main(String[] args)
	{
		//Store capacity, rods, and weight sums
		int c, r;
		int sum = 0;
		long[] answer;
		Scanner sc = new Scanner(System.in);
		
		//Read in capacity and rods
		c = sc.nextInt() * 1000;
		r = sc.nextInt();
		values = new int[r];
		weights = new long[r];
		
		//Store inputs, turn weights into longs
		for(int i = 0; i < r; i++)
		{
			int v = sc.nextInt();
			double w = sc.nextDouble() * 1000;
			values[i] = v;
			weights[i] = (long) w;
		}
		
		//Calculate total possible sums
		for(int i = 0; i < r; i++)
		{
			sum += values[i];
		}
		
		//Use value sums as states
		answer = new long[sum + 1];
		
		//Fill the array with large numbers
		for(int i = answer.length - 1; i > 0; i--)
		{
			answer[i] = INFINITY;
		}
		
		//0 state has 0 weight
		answer[0] = 0;
		
		//Loop through each item
		for(int i = 0; i < r; i++)
		{
			//Loop through each value sum
			for(int j = sum; j >= values[i]; j--)
			{
				//Weight of rod + best weight without 
				long possibleWeight = weights[i] + answer[j - values[i]];
				//If the weight < current min weight replace
				if(possibleWeight < answer[j])
					answer[j] = possibleWeight;
			}
		}
		//Find the highest value sum without exceeding capacity
		for(int i = answer.length - 1; i >= 0; i--)
		{
			if(answer[i] <= c)
			{
				System.out.println(i);
				break;
			}
		}
	}
}