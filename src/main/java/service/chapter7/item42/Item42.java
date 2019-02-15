package service.chapter7.item42;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Item42 {

	public static void main(String[] args) {
		String[] words = {"hello", "hi", "bye"};
		// 1.8 �̸�
		Collections.sort(Arrays.asList(words), new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.length(), o2.length());
			}
		});
		
		System.out.println(">> anonymous class");
		for (String word : words) {
			System.out.println(word);
		}
		
		Collections.sort(Arrays.asList(words), (s1, s2)-> Integer.compare(s1.length(), s2.length()));
		
		
		System.out.println(">> anonymous class");
		for (String word : words) {
			System.out.println(word);
		}

		List<String> wordsLst = new ArrayList<String>();
		wordsLst.add("hello");
		wordsLst.add("hi");
		wordsLst.add("bye");
//		wordsLst.sort(c);
		
		
//		Collections.sort(list, c);
	}
}
