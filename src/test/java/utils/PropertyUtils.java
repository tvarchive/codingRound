package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
    private static PropertyUtils INSTANCE = null;
    private final Properties props = new Properties();

    private PropertyUtils() {
        this.loadProperties("configuration.properties");
        this.props.putAll(System.getProperties());
    }

    private static PropertyUtils getInstance() {
        if (INSTANCE == null) {
            PropertyUtils.INSTANCE = new PropertyUtils();
        }
        return PropertyUtils.INSTANCE;
    }

    private void loadProperties(final String propFilePath) {
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(propFilePath)) {
            System.out.println("Input Stream : " + inputStream);
            if (inputStream != null) {
                this.props.load(inputStream);
            } else {
                throw new UnableToLoadPropertiesException(propFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(final String key) {
        return PropertyUtils.getInstance().props.getProperty(key);
    }

    public static String getProperty(final String key, final String defaultValue) {
        return PropertyUtils.getInstance().props.getProperty(key, defaultValue);
    }

    public static int getIntegerProperty(final String key, final int defaultValue) {
        final String value = PropertyUtils.getInstance().props.getProperty(key, String.valueOf(defaultValue));
        return Integer.parseInt(value);
    }

    public static Properties getProps() {
        return PropertyUtils.getInstance().props;
    }

    private class UnableToLoadPropertiesException extends RuntimeException {
        UnableToLoadPropertiesException(final String pathString) {
            super(pathString);
        }

        public UnableToLoadPropertiesException(final String pathString, final Exception exception) {
            super(pathString, exception);
        }
    }
}
