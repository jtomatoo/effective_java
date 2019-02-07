package domain;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

public enum Operation {

	PLUS("+") {
		public double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS("-") {
		public double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES("*") {
		public double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDES("/") {
		public double apply(double x, double y) {
			return x / y;
		}
	};
	
	private final String symbol;
	
	Operation(String symbol) {
		this.symbol = symbol;
	}
	
	public String toString() {
		return symbol;
	}
	
	public abstract double apply(double x, double y);
	
/*	
	private static final Map<String, Operation> stringToEnum = Stream.of(values()).collect(toMap(Object::toString, e->e) );
	
	public static Optional<Operation> fromString(String symbol) {
		return Optional.ofNullable(stringToEnum.get(symbol));
	}

	private static Collector toMap(Object object, Object object2) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	/*
	public double apply(double x, double y) {
		switch(this) {
			case PLUS: return x + y;
			case MINUS: return x - y;
			case TIMES: return x * y;
			case DVIDES: return x / y;
		}
		throw new AssertionError("unknown operation: " + this);
	}
	 */
	
	public static Operation inverse(Operation op) {
		switch (op) {
			case PLUS: return Operation.MINUS;
			case MINUS: return Operation.PLUS;
			case TIMES: return Operation.DIVIDES;
			case DIVIDES: return Operation.TIMES;
		default:
			throw new AssertionError("unknown operation:" + op);
		}
	}
}
