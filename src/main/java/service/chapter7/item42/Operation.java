package service.chapter7.item42;

public enum Operation {

	PLUS("+") {
		@Override
		public double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS("-") {
		@Override
		public double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES("*") {
		@Override
		public double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDE("/") {
		@Override
		public double apply(double x, double y) {
			return x / y;
		}
	};
	
	private final String symbol;

	private Operation(String symbol) {
		this.symbol = symbol;
	}
	
	public abstract double apply(double x, double y);
	
	@Override
	public String toString() {
		return symbol;
	}
}
