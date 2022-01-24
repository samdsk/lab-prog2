package FileSystem;

import java.util.List;

public interface FileSystem{
    Entry getEntry(Path p);
    void createDirectory(Path p);
    void createFile(Path p, int dim);
    List<Entry> getDirectory(Path p);
    int getSize(Path p);

}