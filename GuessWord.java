import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GuessWord {
	
	public int getPoint(String word, String target) {
		
		int result = 0;
		
		if(word.length() == 0 || target.length() == 0) {
			return result;
		}
		
		int point = 0;
		int index = 0;
		while(index < word.length() && index < target.length()) {
			if(word.charAt(index) == target.charAt(index)) {
				point++;
			}
			index++;
		}
		
		char[] word_array = word.toCharArray();
		
		HashMap<Character, Integer> word_hm = new HashMap<Character, Integer>();
		
		for(Character word_char: word_array) {
			if(!word_hm.containsKey(word_char)) {
				word_hm.put(word_char, 1);
			}else {
				word_hm.put(word_char, word_hm.get(word_char) + 1);
			}
		}

		char[] target_array = target.toCharArray();
		
		HashMap<Character, Integer> target_hm = new HashMap<Character, Integer>();
		
		for(Character target_char: target_array) {
			if(!target_hm.containsKey(target_char)) {
				target_hm.put(target_char, 1);
			}else {
				target_hm.put(target_char, target_hm.get(target_char) + 1);
			}
		}
		
		for(Map.Entry<Character, Integer> set: target_hm.entrySet()) {
			if(word_hm.containsKey(set.getKey())) {
				point = point + Math.min(set.getValue(), word_hm.get(set.getKey()));
			}
		}
		
		return point;
		
	}
	
	
	
	/*
	public int getPoint(String[] words, String target) {
		
		int result = 0;
		
		if(words.length == 0 || target.length() == 0) {
			return result;
		}
			
		int max_point = 0;
		
		for(String word: words) {
			int word_point = 0;
			int index = 0;
			while(index < word.length() && index < target.length()) {
				if(word.charAt(index) == target.charAt(index)) {
					word_point++;
				}
				index++;
			}
			
			char[] word_array = word.toCharArray();
			
			HashMap<Character, Integer> word_hm = new HashMap<Character, Integer>();
			
			for(Character word_char: word_array) {
				if(!word_hm.containsKey(word_char)) {
					word_hm.put(word_char, 1);
				}else {
					word_hm.put(word_char, word_hm.get(word_char) + 1);
				}
			}

			char[] target_array = target.toCharArray();
			
			HashMap<Character, Integer> target_hm = new HashMap<Character, Integer>();
			
			for(Character target_char: target_array) {
				if(!target_hm.containsKey(target_char)) {
					target_hm.put(target_char, 1);
				}else {
					target_hm.put(target_char, target_hm.get(target_char) + 1);
				}
			}
			
			for(Map.Entry<Character, Integer> set: target_hm.entrySet()) {
				if(word_hm.containsKey(set.getKey())) {
					word_point = word_point + Math.min(set.getValue(), word_hm.get(set.getKey()));
				}
			}
			
			if(word_point > max_point) {
				max_point = word_point;
			}
			
		}
		
		return max_point;
		
	}
	*/
	
	private int cTest = 1;
	private int outType = 3;
	
	/*
	public void test(String[] words, String target, int expected) {
		int result = this.getPoint(words, target);
		
		if(outType == 3) {
			System.out.println(String.format("%d \t Gave: %s \t Target: %s \t Got: %d \t Expected: %d",
	    			cTest, Arrays.toString(words), target.toString(), result, expected));
		}
		
		cTest++;
	}
	*/
	
	public void test(String word, String target, int expected) {
		int result = this.getPoint(word, target);
		
		if(outType == 3) {
			System.out.println(String.format("%d \t Gave: %s \t Target: %s \t Got: %d \t Expected: %d",
	    			cTest, word, target, result, expected));
		}
		
		cTest++;
	}
	
	public static void main(String[] args) {
		
		GuessWord gw = new GuessWord();
		
		System.out.println("Case when considering only one word:");
		
		gw.test("APT", "TPA", 4);
		
		gw.test("ATM", "TMA", 3);
		
		gw.test("AMTH", "APTM", 5);
		
		gw.test("ATM", "LKR", 0);
		

		/*
		System.out.println("Case when considering an array of words");
		
		gw.test(new String[]{"APTX", "AP", "PAT"}, "APT", 6);
		
		gw.test(new String[] {"APP", "APX"}, "ATMP", 3);
		
		gw.test(new String[] {"APP", "APX", ""}, "AMPX", 4);
		
		gw.test(new String[] {"APX", "PMT"}, "", 0);
		*/
		
		System.out.println("Case when random sample cases");
		
		Random rand = new Random();
		
		//generate 6 more random cases
		for(int loop = 0; loop < 6; loop++) {
			
			//generate the length of the target
			int tar_length = rand.nextInt(2) + 6;
		
			//System.out.println("length " + tar_length);
		
			ArrayList<Integer> index_tar = new ArrayList<Integer>();
			for(int i = 0; i < tar_length; i++) {
				index_tar.add(i);
			}
		
			//create two arrays for both target and word
			char[] tar_arr = new char[tar_length];
			char[] word_arr = new char[tar_length];
		
			//generate result point
			int max_point = rand.nextInt(3) + 2;
		
			//System.out.println("max " + max_point);
		
			//generate the number of characters that has the same value and position.
			int two_point_char = rand.nextInt((int)(max_point / 2)) + 1;
		
			//System.out.println("two " + two_point_char);
		
			//calculate the number of characters that has the same value but not the position.
			int one_point_char = max_point - two_point_char * 2;
		
			//System.out.println("one " + one_point_char);
		
			//calculate the number of characters for no point in the string.
			int no_point_char = tar_length - one_point_char - two_point_char;
		
			//System.out.println("no " + no_point_char);

		
			//create char arrays for several characters
			char[] two_point_char_array = new char[] {'A', 'P', 'T'};
			char[] one_point_char_array = new char[] {'C', 'E', 'F', 'G', 'J'};
			char[] no_point_char_array = new char[] {'D', 'N', 'I', 'K', 'Q', 'U', 'W', 'V', 
										'O', 'Z', 'R', 'B', 'H', 'M', 'X', 'Y', 'S', 'L'};
		
			ArrayList<Integer> index_two_point_char_array = new ArrayList<Integer>();
			for(int i = 0; i < two_point_char_array.length; i++) {
				index_two_point_char_array.add(i);
			}
		
			ArrayList<Integer> index_one_point_char_array = new ArrayList<Integer>();
			for(int i = 0; i < one_point_char_array.length; i++) {
				index_one_point_char_array.add(i);
			}
		
			ArrayList<Integer> index_no_point_char_array = new ArrayList<Integer>();
			for(int i = 0; i < no_point_char_array.length; i++) {
				index_no_point_char_array.add(i);
			}
		
			Collections.shuffle(index_two_point_char_array);
			Collections.shuffle(index_one_point_char_array);
			Collections.shuffle(index_no_point_char_array);
		
			Collections.shuffle(index_tar);
		
			int two_index = 0, one_index = 0, no_index = 0, tar_index = 0;
			//determine which char is two point char
			for(int i = 0; i < two_point_char; i++) {
				tar_arr[index_tar.get(tar_index)] = two_point_char_array[two_index];
				word_arr[index_tar.get(tar_index)] = two_point_char_array[two_index];
				tar_index++;
				two_index++;
			}
		
			//System.out.println(Arrays.toString(tar_arr));
			//System.out.println(Arrays.toString(word_arr));
				
			//determine which char is one point char, 
			//put one same one_point char and one different no_point char in both target and word array for one loop sequence
			for(int i = 0; i < one_point_char; i = i + 1) {
				tar_arr[index_tar.get(tar_index)] = one_point_char_array[one_index];
				word_arr[index_tar.get(tar_index)] = no_point_char_array[no_index];
				tar_index++;
				no_index++;

				tar_arr[index_tar.get(tar_index)] = no_point_char_array[no_index];
				word_arr[index_tar.get(tar_index)] = one_point_char_array[one_index];
				one_index++;
				no_index++;
				tar_index++;
			}
		
			//insert all different no_point char into the empty value of both target and word array
			while(tar_index <= tar_length - 1) {
				tar_arr[index_tar.get(tar_index)] = no_point_char_array[no_index];
				no_index++;
				word_arr[index_tar.get(tar_index)] = no_point_char_array[no_index];
				no_index++;
				tar_index++;
			}
		
			//System.out.println(Arrays.toString(tar_arr));
			//System.out.println(Arrays.toString(word_arr));
			
			//convert char arrays to be string
			String target = String.valueOf(tar_arr);
			String word = String.valueOf(word_arr);
			
			//run the test method
			gw.test(word, target, max_point);
			
		}

	}
	
}
