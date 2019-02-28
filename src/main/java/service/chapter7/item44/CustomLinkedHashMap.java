package service.chapter7.item44;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class CustomLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

	private BiPredicate<Map<K, V>, Map.Entry<K, V>> removeFunction;
	
	public CustomLinkedHashMap(BiPredicate<Map<K, V>, Map.Entry<K, V>> removeFunction) {
		super();
		this.removeFunction = removeFunction;
		
	}
	
	@Override
	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
		return this.removeFunction.test(this, eldest);
    }
}
