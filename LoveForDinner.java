import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class LoveForDinner {
	
	public String[] getFavoriteFood(String[] preferences) {
				
		if(preferences.length == 0) return preferences;
		
		Set<String> pre = new TreeSet<String>(Arrays.asList(preferences[0].split(" ")));
		
		for(int i = 1; i < preferences.length; i++) {
			pre.retainAll(Arrays.asList(preferences[i].split(" ")));
		}
		
		int i = 0;
		String[] preArr = new String[pre.size()];
		for(String s: pre) {
			preArr[i] = s;
			i++;
		}
		
		Arrays.sort(preArr, Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
		
		return preArr;
	}
	
	private int cTest = 1;
	private int outType = 3;
	
	public void test(String[] preferences, String[] expected) {
		String[] result = this.getFavoriteFood(preferences);
		
		if(outType == 3) {
			System.out.println(String.format("%d \t Gave: %s \t Got: %s \t Expected: %s",
	    			cTest, Arrays.toString(preferences), Arrays.toString(result), Arrays.toString(expected)));
		}
		
		cTest++;
	}
	
	
	public static void main(String[] args) {
		
		LoveForDinner lfd = new LoveForDinner();
		
		lfd.test(new String[]{"pizza ramen sushi", "sushi hotpot spaghetti", "salad ice-cream sushi"}, new String[]{"sushi"});
		System.out.println();

		lfd.test(new String[] {"pizza ramen sushi", "", "sushi hotpot spaghetti"}, new String[] {""});
		System.out.println();

		lfd.test(new String[]{"pizza ramen sushi", "sushi ramen spaghetti", "ramen ice-cream sushi"}, new String[]{ "ramen", "sushi"});
		System.out.println();

		
		//create a list of food
		ArrayList<String> lstfood = new ArrayList<>(Arrays.asList("pizza", "ramen", "sushi", "spaghetti", "ice-cream", "hotpot", "salad", 
																"steak", "fruit", "vegetable", "sandwich", "hamburger",
																"chocolate", "milkshake", "puff", "rice", "chowfun", "shrimp", "pie",
																"cookie", "yogurt", "cheese", "cake", "salmon", "egg"));
		
		// create a list to store the index of lstfood for better shuffling.
		ArrayList<Integer> int_lstfood_list = new ArrayList<Integer>();
		for(int j = 0; j < lstfood.size(); j++) {
			int_lstfood_list.add(j);
		}
		
		Random rand = new Random();
		rand.setSeed(1);
		
		// perform seven more tests
		for(int i = 0; i < 7; i++) {
			
			//determine how many items would be in the result
			int rand_num = rand.nextInt(3) + 1;
			
			//determine the number that each item inside results would appear 
			//in this problem this equal to the number of preferences, while in the next problem it's not
			int rand_cnt = rand.nextInt(3) + 4;
			
			// pick items for result
			Collections.shuffle(int_lstfood_list, rand);
			ArrayList<String> result_items = new ArrayList<>();
			int index = 0;
			for(int j = 0; j < rand_num; j++) {
				result_items.add(lstfood.get(int_lstfood_list.get(index)));
				index++;
			}
						
			//create a hashmap to store the cnts of all food to make sure the results do not change
			HashMap<String, Integer> hm = new HashMap<String, Integer>();
					
			for(String food:lstfood) {
				hm.put(food, 0);
			}
			
			// save the result cnts to hashmap
			for(String result_item: result_items) {
				hm.put(result_item, rand_cnt);
			}
			
			//System.out.println(hm.toString());
						
			//determine the number of preferences. 
			//Again, for this problem rand_pre = rand_cnt
			int rand_pre = rand_cnt;
			
			//create a list for all preferences and initialize all strings to be ""
			ArrayList<String> preferences = new ArrayList<String>();
			for(int j = 0; j < rand_pre; j++) {
				preferences.add("");
			}
						
			//put result items inside every preference
			for(int m = 0; m < preferences.size(); m++) {
				for(int n = 0; n < result_items.size(); n++) {
					preferences.set(m, preferences.get(m) + " " + result_items.get(n));
				}
			}
			
			//loop all preference to add random items
			int food_index = 0;
			for(int j = 0; j < rand_pre; j++) {
				Collections.shuffle(int_lstfood_list);
				//random the number of items to add for each preference
				int number_to_add = rand.nextInt(2) + 1;
				//number of items that have been added.
				int number_added = 0;
				while(number_added < number_to_add) {
					//make sure the result would not change and the result item would not be added once again.
					if(hm.get(lstfood.get(food_index)) != rand_cnt && hm.get(lstfood.get(food_index)) < rand_cnt - 1) {
						preferences.set(j, preferences.get(j) + " " + lstfood.get(food_index));
						number_added += 1;
					}
					food_index++;
					if(food_index == int_lstfood_list.size()) food_index = 0;
				}
			}
			
			//shuffle the order of string words and trim all leading and ending space.
			for(int m = 0; m < preferences.size(); m++) {
				preferences.set(m, preferences.get(m).trim());
				String[] temp = preferences.get(m).split(" ");
				List<String> templist = Arrays.asList(temp);
				Collections.shuffle(templist);
				String temp_str = "";
				for(String s: templist) {
					temp_str += s + " ";
				}
				String t = temp_str.trim();
				preferences.set(m, t);
			}
			
			//Sort lexicographically
			String[] expected = result_items.toArray(new String[0]);
			Arrays.sort(expected, Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
			
			//run the test method
			lfd.test(preferences.toArray(new String[0]), expected);
			System.out.println();

			
		}
		
		
		
	}

}
