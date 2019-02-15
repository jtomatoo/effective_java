# item44. 표준 함수형 인터페이스를 사용하라
<p>람다를 지원하면서 API 작성하는 모범사례가 바뀜.</P>
상위 클래스의 기본 메서드를 재정의해 원하는 동작을 구현하는 템플릿 메서드 패턴이 매력이 줄어듬.

<p>현대적인 해법은 같은 효과의 함수 객체를 받는 정적 팩터리나 생성자를제공</P>
즉, 함수객체를 매개변수로 받는 생성자와 메서드를 더 많이 만들어야됨.

<p>LinkedHashMap, protected method removeEldestEntry</p>
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


<pre>
<code>
@FunctionalInterface
public interface EldestEntryRemovalFunction<K, V> {
	boolean remove(Map<K, V> map, Map.Entry<K, V> eldest);
}

public class CustomLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
	private EldestEntryRemovalFunction<K, V> removeFunction;
	
	public CustomLinkedHashMap(EldestEntryRemovalFunction<K, V> removeFunction) {
		super();
		this.removeFunction = removeFunction;		
	}
	
	@Override
	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return this.removeFunction.remove(this, eldest);
    }
}

public class Item44 {
	public static void main(String[] args) {
		Map<String, Object> map
		= new CustomLinkedHashMap<String, Object>((currentMap, eldestMap) -> {return currentMap.size() > 0;});
	}
}

</code>
</pre>

위와 같이 인터페이스함수를 직접 정의하지 말고, java.util.function 패키지에서 제공하는 표준 함수형 인터페이스를 사용
<pre>
<code>
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
</code>
</pre>

43개의 인터페이스를 전부 기억하긴 어렵겠지만, 기본 인터페이스 6개만 기억하면 충분히 유추 가능
책 P264~265 테이블 참고

기본 인터페이스는 기본 타입인 int, long, double용으로 변형이 생겨남. 대부분 인수타입과 반환타입이 일치함
단, Function 함수형 인터페이스는 반환타입만 매개변수화

표준함수형 인터페이스를 유추하는 기본적인 방법
<p>1. 입력과 결과 타입이 모두 기본 타입이면 접두어로 srcToResult를 사용</p>

<pre>
<code>
@FunctionalInterface
public interface LongToIntFunction {
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    int applyAsInt(long value);
}

</code>
</pre>

<p>2. 입력을 매개변수화하고 접두어로 toResult를 사용</p>

<pre>
<code>
@FunctionalInterface
public interface ToLongFunction<T> {
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    long applyAsLong(T value);
}
</code>
</pre>

<p>3. 기본 함수형 인터페이스 중 3개에는 인수를 2개씩 받음</p>
-> BiPredicate<T, U>, BiFunction<T, U, R>, BiConsumer<T,U>

<pre>
<code>
@FunctionalInterface
public interface BiPredicate<T, U> {
    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     * @return {@code true} if the input arguments match the predicate,
     * otherwise {@code false}
     */
    boolean test(T t, U u);

</code>
</pre>

<p>4.기본타임을 반환하는 세변형</p>
-> ToIntBiFunction<T,U>, ToLongBiFunction<T,U>, ToDoubleBiFunction<T,U>

<pre>
<code>
@FunctionalInterface
public interface ToDoubleBiFunction<T, U> {
    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    double applyAsDouble(T t, U u);
}
</code>
</pre>

<p>5. Consumer 인터페이스 객체 참조와 기본타입 하나를 받는 변형 인터페이스</p>
-> ObjDoubleConsumer<T>, ObjIntConsumer<T>, ObjLongConsumer<T>

<pre>
<code>
@FunctionalInterface
public interface ObjDoubleConsumer<T> {
    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param value the second input argument
     */
    void accept(T t, double value);
}
</code>
</pre>

<p>표준 함수형 인터페이스 대부분은 기본 타입만 지원,</p>
기본 함수형 인터페이스에 박싱된 기본 타입을 넣어 사용하지는 말자
동작은 하지만, 계산량이 많을 때는 성능저하

<p>표준함수형 인터페이스가 존재하더라도 직접 작성해야하는 사례</p>
<p>자주 쓰이며, 이름 자체가 용도를 명확히 설명해준다.</p>
<p>반드시 따라야 하는 규약이 있다.
<p>유용한 디폴트 메서드를 제공할 수 있다.</p>

(책 266 페이지 마지막 단락 참조)

전용함수형 인터페이스를 작성할 경우, 인터페이스임을 명심해야하며 @FUnctionalInterface 애너테이션을 사용
(책 267 페이지 두 번째 단락 참조)

함수형 인터페이스 API 사용 시, 주의 사항으로 서로 다른 함수형 인터페이스를 같은 위치의 인수로 받는 메서드들을 다중정의 금지
<pre>
<code>
<T> Future<T> submit(Callable<T> task);
    /**
     * Submits a Runnable task for execution and returns a Future
     * representing that task. The Future's {@code get} method will
     * return the given result upon successful completion.
     *
     * @param task the task to submit
     * @param result the result to return
     * @param <T> the type of the result
     * @return a Future representing pending completion of the task
     * @throws RejectedExecutionException if the task cannot be
     *         scheduled for execution
     * @throws NullPointerException if the task is null
     */
    <T> Future<T> submit(Runnable task, T result);

    /**
     * Submits a Runnable task for execution and returns a Future
     * representing that task. The Future's {@code get} method will
     * return {@code null} upon <em>successful</em> completion.
     *
     * @param task the task to submit
     * @return a Future representing pending completion of the task
     * @throws RejectedExecutionException if the task cannot be
     *         scheduled for execution
     * @throws NullPointerException if the task is null
     */
    Future<?> submit(Runnable task);
</code>
</pre>
