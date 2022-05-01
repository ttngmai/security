package study.security.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface ExcelFile<T> {
    void addRows(List<T> data);

    void write(OutputStream stream) throws IOException;
}
