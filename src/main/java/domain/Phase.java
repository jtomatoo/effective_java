package domain;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Phase {

	SOLID, LIQUID, GAS, PLASMA;
	
	public enum Transition {
		MELT(SOLID, LIQUID),
		FREEZE(LIQUID, SOLID),
		BOIL(LIQUID, GAS),
		CONDENSE(GAS, LIQUID),
		SUBLIME(SOLID, GAS),
		DEPOSIT(GAS, SOLID),
		IONIZE(GAS, PLASMA),
		DEIONIZE(PLASMA, GAS);
		
		private final Phase from;
		private final Phase to;
		
		Transition(Phase from, Phase to) {
			this.from = from;
			this.to= to;
		}
		
		private static final Map<Phase, Map<Phase, Transition>> 
			m = Stream.of(values()).collect(Collectors.groupingBy(
					t->t.from, 
					()->new EnumMap<>(Phase.class), 
					Collectors.toMap(t->t.to, t->t, (x,y)->y, ()->new EnumMap<>(Phase.class))));
		
		public static Transition from(Phase from, Phase to) {
			return m.get(from).get(to);
		}
	}
	
	/*
	public enum Transition {
		MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;

		// row�� from�� ordinal��, ���� to�� ordinal�� �ε����� ����.
		private static final Transition[][] TRANSITIONS = {
			{null, MELT, SUBLIME},
			{FREEZE, null, BOIL},
			{DEPOSIT, CONDENSE, null}
		};
		
		// �� ���¿��� �ٸ� ���·��� ���̸� ��ȯ�Ѵ�.
		public static Transition from(Phase from, Phase to) {
			return TRANSITIONS[from.ordinal()][to.ordinal()];
		}
	}
	*/
}
