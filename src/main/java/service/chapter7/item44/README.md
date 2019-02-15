# item44. 표준 함수형 인터페이스를 사용하라
람다를 지원하면서 API 작성하는 모범사례가 바뀜.
상위 클래스의 기본 메서드를 재정의해 원하는 동작을 구현하는 템플릿 메서드 패턴이 매력이 줄어듬.

현대적인 해법은 같은 효과의 함수 객체를 받는 정적 팩터리나 생성자를제공
즉, 함수객체를 매개변수로 받는 생성자와 메서드를 더 많이 만들어야됨.

LinkedHashMap, protected method removeEldestEntry
해당 메서드의 경우, put method 호출 시 내부적으로 호출하여 map의 가장 오래된 원소를 삭제하는 매커니즘
이것을 확장한느 클래스를 추가로 구현할 경우, removeEldestEntry method를 해당 확장 클래스에서 용도에 맞게 수정

<pre>
<code>
public class CustomLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

	@Override
	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > 100;
    }
}
</code>
</pre>