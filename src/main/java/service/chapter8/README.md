# item62. 다른 타입이 적절하다면 문자열 사용을 피하라.
- 문자열은 다른 값 타입을 대신하기에 적합하지 않다.
진짜 문자열을 입력받을 때만 사용하라.
받는 데이터가 수치형일 경우 int, float, BigInteger등 적당한 수치를 사용
예/아니오와 같은 형태는 열거타입이나 boolean을 사용

- 문자열은 열거 타입을 대신하기에 적합하지 않다.

- 문자열은 혼합 타입을 대신하기에 적합하지 않다.
```c
String compoundKey = className + "#" + i.next();

```
단점: 
* 두 요소를 구분해주는 문자 #이 두 요소 중 하나에서 쓰였다면 혼란야기
* 문자열 파싱에 따른 퍼포먼스(느리고,귀찮고,오류가능성) 저하
* 적절한 메서드(eqaulsl, toString, compareTo)를 제공불가
* String 기능에만 의존

대안: 전용 클래스를 새로 만들자.

- 문자열은 권한을 표현하기에 적합하지 않다.
ex) 쓰레드 지역변수 기능을 설계->쓰레드가 자신만의 변수를 갖게 해주는 기능
클라이언트가 제공한 문자열 키를 스레드별 지역변수를 식별.
```c
public class ThreadLocal {
	private ThreadLocal() {}
	
	public static void set(String key, Object value);
	
	public static Object get(String key);
}
```

단점:
* 문자열 키가 전역 이름공간에서 공유
* 클라이언트가 공유한 키를 제공해야함, 만약 클라이언트가 같은 키를 쓰게 되면 의도치 않게 같은 변수를 공유 <- 커뮤니케이션 불발 or 실수
* 보안에 취약 <- 악의적인 클라이언트에 의해서 같은 키를 사용

대안
클라이언트에게 키를 입력받는 것이 아닌 고유키를 생성
```c
public class ThreadLocal {
	private ThreadLocal() {}
	
	public static class Key { // 권한
		Key() {}
	}
	
	// 위조가 불가능한 고유키를 생성
	public static Key getKey() {
		return new Key();
	}
	
	public static void set(Key key, Object value);
	
	public static Object get(Key key);
}
```

리펙토링 1
ThreadLocal 클래스의 static method인 set,get 을 Key 클래스의 인스턴스 method로 이전
Key는 더이상 쓰레드 지역변수 구분이 아니라, 그 자체가 쓰레드 지역변수
```c
public final class ThreadLocal {
	public ThreadLocal();
	public void set(Object value);
	public Object get();
}
```

리펙토링 2
get method의 Object를 실제 타입으로 형변환해서 써야하므로 타입 안전하지 않다.
```c
public final class ThreadLocal<T> {
	public ThreadLocal();
	public void set(T value);
	public T get();
}
```
https://docs.oracle.com/javase/7/docs/api/java/lang/ThreadLocal.html

# item63. 문자열 연결은 느리지 주의하라.
- 문자열 연결 연산자(+)는 여러 문자열을 하나로 합쳐주는 편리한 수단.
- 문자열 연결 연산자로 문자열 n개를 잇는 시간은 n^2에 비례.
```c
public String statement() {
	String result = "";
	for(int i=0;i<numItems();i++) {
		result += lineForItem(i);
	}
	
	return result;
}
```
성능을 포기하고 싶지 않다면 String 대신 StringBuilder 사용
```c
public String statement2() {
	StringBuilder sb = new StringBuilder(numItems() * LINE_WIDTH);
	for(int i=0;i<numItems();i++) {
		b.append(lineForItem(i));
	}
	
	return b.toString();
}
```
성능 수치상 String의 +연산자보다 6.5배 빨랏으며, StringBuilder의 기본값을 사용해도 5.5배 빠름