package service.chapter7.item45;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagrams {
	
	public void defaultMethod(File file, int minGroupSize) throws IOException {
		File dictionary = file;
		
		Map<String, Set<String>> groups = new HashMap<>();
		try(Scanner s = new Scanner(dictionary)) {
			while(s.hasNext()) {
				String word = s.next();
				groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>()).add(word);
			}
		}
		
		for (Set<String> group : groups.values()) {
			if(group.size() >= minGroupSize) {
				System.out.println(group.size() + ": " + group);
			}
		}
	}
	
	public void advanedMethod(String filePath, int minGroupSize) throws IOException {
		Path dictionary = Paths.get(filePath);
		try(Stream<String> words = Files.lines(dictionary)) {
			words.collect(Collectors.groupingBy(word->alphabetize(word)))
					.values().stream()
					.filter(group->group.size() >= minGroupSize)
					.forEach(g->System.out.println(g.size() + ": " + g));
		} 
	}
	
	private String alphabetize(String s) {
		char[] a = s.toCharArray();
		Arrays.sort(a);
		
		return new String(a);
	}
}
