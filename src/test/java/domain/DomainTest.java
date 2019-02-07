package domain;

import java.util.EnumSet;

import org.junit.Test;

import domain.Text.Style;

public class DomainTest {

	@Test
	public void simpleText() {
		Text text = new Text();
		text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
//		text.applyStyle(Text.STYLE_BOLD | Text.STYLE_ITALIC);
	}
}
