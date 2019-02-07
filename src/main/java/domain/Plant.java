package domain;

public class Plant {
	enum LifeCycle {
		ANNUAL, PERENNTIAL, BIENNINAL
	}
	
	public final String name;
	public final LifeCycle lifeCycle;
	
	public Plant(String name, LifeCycle lifeCycle) {
		this.name = name;
		this.lifeCycle = lifeCycle;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
