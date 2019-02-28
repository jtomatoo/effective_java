package service.chapter7.item44;

import java.util.Map;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.LongFunction;

public class Item44 {

	public static void main(String[] args) {

		Map<String, Object> map = new CustomLinkedHashMap<String, Object>((currentMap, eldestMap) -> {return currentMap.size() > 0;}); 
		
		CustomLinkedHashMap<String, Object> linkedHashMap
		= new CustomLinkedHashMap<String, Object>((currentMap, eldestMap) -> {return currentMap.size() > 0;});
	}
}
