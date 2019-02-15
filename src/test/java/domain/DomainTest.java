package domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import domain.Plant.LifeCycle;
import domain.Text.Style;
import domain.interf.Operation;

public class DomainTest {

	@Test
	public void simpleText() {
		Text text = new Text();
		text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
//		text.applyStyle(Text.STYLE_BOLD | Text.STYLE_ITALIC);
	}
	
	@Test
	public void simplePlant() {
		System.out.println("simplePlant >>");
		Plant p1 = new Plant("a", LifeCycle.ANNUAL);
		Plant p2 = new Plant("b", LifeCycle.BIENNINAL);
		Plant p3 = new Plant("c", LifeCycle.PERENNTIAL);
		
		Plant[] garden = new Plant[]{p1, p2, p3};
		
		System.out.println(Arrays.stream(garden).collect(
							Collectors.groupingBy(
									p->p.lifeCycle,
									()-> new EnumMap<>(LifeCycle.class), Collectors.toSet()
							)));
		
		System.out.println(Arrays.stream(garden).collect(Collectors.groupingBy(p->p.lifeCycle)));
		
		Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);
		for (Plant.LifeCycle lc : Plant.LifeCycle.values()) {
			plantsByLifeCycle.put(lc, new HashSet<>());
		}
		for (Plant p : garden) {
			plantsByLifeCycle.get(p.lifeCycle).add(p);
		}
		
		System.out.println(plantsByLifeCycle);
	}
	
	@Test
	public void simplExtendOperation() {
		System.out.println("simpleExtendOperation>>");
		double x = Double.parseDouble("2");
		double y = Double.parseDouble("4");
		
		test1(ExtendedOperation.class, x, y);
		test2(Arrays.asList(ExtendedOperation.values()), x, y);
	}
	
	private <T extends Enum<T> & Operation> void test1(Class<T> opEnumType, double x, double y) {
		for (Operation op : opEnumType.getEnumConstants()) {
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
		}
	}
	
	private void test2(Collection<? extends Operation> opSet, double x, double y) {
		for (Operation op : opSet) {
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
		}
	}
	
	@Test
	public void simpleBigram() {
		System.out.println("simpleBigram >>");
		Set<Bigram> s = new HashSet<Bigram>();
		for(int i=0;i<10;i++) {
			for(char ch='a';ch<='z';ch++) {
				s.add(new Bigram(ch, ch));
			}
		}
		System.out.println(s.size());
	}
}
