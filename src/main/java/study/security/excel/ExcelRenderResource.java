package study.security.excel;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ExcelRenderResource {
    private Map<String, String> excelHeaderNames;
    private List<String> dataFieldNames;

    public String getExcelHeaderName(String dataFieldName) {
        return excelHeaderNames.get(dataFieldName);
    }

    public List<String> getDataFieldNames() {
        return dataFieldNames;
    }
}