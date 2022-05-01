package study.security.excel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static study.security.excel.SuperClassReflectionUtils.getAllFields;

public final class ExcelRenderResourceFactory {
    public static ExcelRenderResource prepareRenderResource(Class<?> type) {
        Map<String, String> headerNamesMap = new LinkedHashMap<>();
        List<String> fieldNames = new ArrayList<>();

        for (Field field : getAllFields(type)) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);

                fieldNames.add(field.getName());
                headerNamesMap.put(field.getName(), annotation.headerName());
            }
        }

        return new ExcelRenderResource(headerNamesMap, fieldNames);
    }
}
