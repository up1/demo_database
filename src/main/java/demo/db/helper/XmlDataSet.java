package demo.db.helper;

import java.io.File;

import org.dbunit.dataset.AbstractDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITableIterator;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

public class XmlDataSet extends AbstractDataSet {
	
	private File xmlFile;
	
	public XmlDataSet(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	@Override
	protected ITableIterator createIterator(boolean reversed)
			throws DataSetException {
		try {
			return readDataSet().iterator();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private IDataSet readDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(this.xmlFile);
	}

}
