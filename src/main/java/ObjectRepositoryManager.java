import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectRepositoryManager {

	private FileInputStream stream;
	 private String propertiesFileName;
	 private Properties propertyFile = new Properties();
	 
	 public ObjectRepositoryManager(String fileName) throws IOException
	 {
	 this.propertiesFileName = fileName;
	 stream = new FileInputStream(propertiesFileName);
	 propertyFile.load(stream);
	 }
	 
	 public String getObjectLocatorValue(String locatorName)
	 {
		 return propertyFile.getProperty(locatorName);
	 }
}
