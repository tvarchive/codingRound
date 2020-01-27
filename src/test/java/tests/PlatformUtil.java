package tests;

public class PlatformUtil {

    public static String getOS() {
        String os = System.getProperty("os.name");
        if (os.equals("SunOS")) {
            return "solaris";
        } else if (os.equals("Linux")) {
            return "linux";
        } else if (os.equals("FreeBSD")) {
            return "bsd";
        } else if (os.equals("NetBSD")) {
            return "bsd";
        } else if (os.equals("OpenBSD")) {
            return "bsd";
        } else if (!os.contains("Darwin") && !os.contains("OS X")) {
            if (os.startsWith("Windows")) {
                return "win32";
            } else {
                throw new RuntimeException("Unsupported OS:" + os);
            }
        } else {
            return "darwin";
        }
    }
}
