package domain;

public enum PayrollDay {

	MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY), WEDNESDAY(PayType.WEEKDAY), 
	THURSDAY(PayType.WEEKDAY), FRIDAY(PayType.WEEKDAY), 
	SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);
	
	private final PayType payType;
	
	PayrollDay(PayType payType) {
		this.payType = payType;
	}
	
	int pay(int minutesWorked, int payRate) {
		return payType.pay(minutesWorked, payRate);
	}
	
	enum PayType {
		WEEKDAY {
			@Override
			int overtimePay(int minsWorked, int payRate) {
				return minsWorked <= MINS_PER_SHIFT ? 0 : (minsWorked - MINS_PER_SHIFT) * payRate / 2;
			}
		},
		WEEKEND {
			@Override
			int overtimePay(int minsWorked, int payRate) {
				return minsWorked * payRate / 2;
			}
		};
		
		abstract int overtimePay(int mins, int payRate);
		private static final int MINS_PER_SHIFT = 8*60;
		
		int pay(int minsWorked, int payRate) {
			int basePay = minsWorked * payRate;
			return basePay + overtimePay(minsWorked, payRate);
		}
	}
	
	/*
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	
	private static final int MINUS_PER_SHIFT = 8 * 60;
	
	int pay(int minutesWorked, int payRate) {
		int basePay = minutesWorked * payRate;
		
		int overtimePay;
		switch(this) {
			case SATURDAY: case SUNDAY: // weekend
				overtimePay = basePay / 2;
				break;
			default:
				overtimePay = minutesWorked <= MINUS_PER_SHIFT ?
						0 : (minutesWorked - MINUS_PER_SHIFT) * payRate / 2;
		}
		
		return basePay + overtimePay;
	}
	*/
}
