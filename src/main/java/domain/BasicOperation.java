package domain;


public enum BasicOperation implements domain.interf.Operation {
	PLUS("+") {
		public double apply(double x, double y) {
			return x + y;
		}
	},
	MINUIS("-") {
		public double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES("*") {
		public double apply(double x, double y) {
			return x - y;
		}
	},
	DIVIDE("/") {
		public double apply(double x, double y) {
			return x - y;
		}
	};
	
	private final String symbol;

	private BasicOperation(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}
	
}
