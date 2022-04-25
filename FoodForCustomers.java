import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class FoodForCustomers {

	public String[] getMostPopularFood(String[] orders) {
		
		if(orders.length == 0) return orders;
		
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
			
		int max_number = 0;
		ArrayList<String> al = new ArrayList<String>();
		
		for(String order: orders) {
			String[] one_order = order.split(" ");
			for(String sub_order: one_order) {
				if(!hm.containsKey(sub_order)) {
					hm.put(sub_order, 1);
				}else {
					hm.put(sub_order, hm.get(sub_order) + 1);
				}
				
				if(hm.get(sub_order) > max_number) {
					al.clear();
					al.add(sub_order);
					max_number = hm.get(sub_order);
				}else if(hm.get(sub_order) == max_number) {
					al.add(sub_order);
				}				
			}				
		}
		
		String[] result = al.toArray(new String[0]);
		Arrays.sort(result, Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
		
		return result;
	}
	
	private int cTest = 1;
	private int outType = 3;
	
	public void test(String[] orders, String[] expected) {
		String[] result = this.getMostPopularFood(orders);
		
		if(outType == 3) {
			System.out.println(String.format("%d \t Gave: %s \t Got: %s \t Expected: %s",
	    			cTest, Arrays.toString(orders), Arrays.toString(result), Arrays.toString(expected)));
		}
		
		cTest++;
	}
	
	public static void main(String[] args) {
		
		FoodForCustomers ffc = new FoodForCustomers();
		//String[] orders = new String[] {"hamburger fries drink", "hamburger tenders waffles drink", "pizza fries drink"};
		//String[] expected = new String[] {"drink"};
		ffc.test(new String[] {"hamburger fries drink", "hamburger tenders waffles drink", "pizza fries drink"}, new String[] {"drink"});
		System.out.println();

		//String[] orders = new String[] {"fries", "hamburger tenders waffles drink", "pizza fries drink"};
		//String[] expected = new String[] {"drink", "fries"};
		ffc.test(new String[] {"fries", "hamburger tenders waffles drink", "pizza fries drink"}, new String[] {"drink", "fries"});
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

		// perform eight more tests
		for(int i = 0; i < 8; i++) {
			//determine how many items would be in the result
			int rand_num = rand.nextInt(3) + 1;

			//determine the number that each item inside results would appear
			int rand_cnt = rand.nextInt(2) + 3;

			// pick items for result
			Collections.shuffle(int_lstfood_list, rand);
			ArrayList<String> result_items = new ArrayList<>();
			int index = 0;
			for(int j = 0; j < rand_num; j++) {
				result_items.add(lstfood.get(int_lstfood_list.get(index)));
				index++;
			}

			//create a hashmap to store the cnts of all food
			HashMap<String, Integer> hm = new HashMap<String, Integer>();

			for(String food:lstfood) {
				hm.put(food, 0);
			}

			// save the result cnts to hashmap
			for(String result_item: result_items) {
				hm.put(result_item, rand_cnt);
			}


			//determine number of preferences
			int rand_order = rand_num * rand_cnt + 3;

			ArrayList<Integer> int_list = new ArrayList<Integer>();
			for(int j = 0; j < rand_order; j++) {
				int_list.add(j);
			}

			ArrayList<String> orders = new ArrayList<String>();
			//initialize all strings to be ""
			for(int j = 0; j < rand_order; j++) {
				orders.add("");
			}

			//random and put result items inside first
			for(int j = 0; j <rand_num; j++) {
				Collections.shuffle(int_list);
				int curr = 0;
				for(int m = 0; m <rand_cnt; m++) {
					orders.set(int_list.get(curr), orders.get(int_list.get(curr)) + " " + result_items.get(j));
					curr++;
				}
			}

			int food_index = 0;

			//loop every order to randomly put items inside
			for(int j = 0; j < rand_order; j++) {
				Collections.shuffle(int_lstfood_list);
				int number_to_add = rand.nextInt(2) + 1;
				int number_added = 0;
				while(number_added < number_to_add) {
					if(hm.get(lstfood.get(food_index)) != rand_cnt && hm.get(lstfood.get(food_index)) < rand_cnt - 1) {
						orders.set(j, orders.get(j) + " " + lstfood.get(food_index));
						number_added += 1;
					}
					food_index++;
					if(food_index == int_lstfood_list.size()) food_index = 0;
				}
			}
			
			
			//shuffle the order of string words and trim all leading and ending space.
			for(int m = 0; m < orders.size(); m++) {
				orders.set(m, orders.get(m).trim());
				String[] temp = orders.get(m).split(" ");
				List<String> templist = Arrays.asList(temp);
				Collections.shuffle(templist);
				String temp_str = "";
				for(String s: templist) {
					temp_str += s + " ";
				}
				String t = temp_str.trim();
				orders.set(m, t);
			}
			
			//Sort lexicographically
			String[] expected = result_items.toArray(new String[0]);
			Arrays.sort(expected, Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
				
			//run the test method
			ffc.test(orders.toArray(new String[0]), expected);
			System.out.println();
			
		}

	}
	
}
