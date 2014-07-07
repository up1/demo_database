package demo.db;

import static org.junit.Assert.*;

import org.junit.Test;

import demo.util.SplitData;

public class SplitDataTest {

	@Test
	public void withSingleTabShouldHaveSizeTwo() {
		SplitData splitData = new SplitData();
		assertEquals(2, splitData.process("1\t2").length);
	}
	
	@Test
	public void withDoubleTabShouldHaveSizeThree() {
		SplitData splitData = new SplitData();
		assertEquals(3, splitData.process("1\t\t").length);
	}

}
