package service.chapter7.item44;

import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.LongFunction;

public class Item44 {

	public static void main(String[] args) {
		
//		EldestEntryRemovalFunction<String, Object> removeFunction = (currentMap, eldestMap)->{return currentMap.size() > 0;};
		
		CustomLinkedHashMap<String, Object> linkedHashMap
		= new CustomLinkedHashMap<String, Object>((currentMap, eldestMap) -> {return currentMap.size() > 0;});
		
		IntPredicate intPred = (intA)-> {return intA > 0;};
		intPred.test(1);
		
		LongFunction<int[]> longFunc = (parameter)->{return new int[] {(int) parameter, (int)parameter+1, (int)parameter+2};};
	}
}
