
public interface Entry {
    String getName();
    int getDimension();
    boolean isFile();
    boolean isDirectory();
    Path getPath();
}
