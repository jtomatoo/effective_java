package domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import domain.Plant.LifeCycle;
import domain.Text.Style;

public class DomainTest {

	@Test
	public void simpleText() {
		Text text = new Text();
		text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
//		text.applyStyle(Text.STYLE_BOLD | Text.STYLE_ITALIC);
	}
	
	@Test
	public void simplePlant() {
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
}
