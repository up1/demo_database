package demo.util;

public class SplitData {

	public String[] process(String input) {
		return input.split("\\t", -1);
	}

}
