package demo.db;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import demo.util.SplitData;

public class WorkWithFile {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	File file;

	@Before
	public void createCSVFile() throws Exception {
		file = temporaryFolder.newFile("input.txt");
		PrintWriter out = new PrintWriter(file);
		out.print("1,2,3,4,5");
		out.close();
	}
	
	@Test
	public void fileExistInTemporaryFolder() throws Exception {
		FileUtility.read(file);
	}
	
	@Test
	public void readFileThatContentShouldNotNull() throws Exception {
		String output = FileUtility.read(file);
		assertTrue(output.length() > 0);
	}

	@Test
	public void readCSVFileAndSubStringWithCommaShouldHaveSizeEqualFive() throws Exception {
		String output = FileUtility.read(file);
		SplitData splitData = new SplitData();
		assertEquals(5, splitData.process(output).length);
	}

}
