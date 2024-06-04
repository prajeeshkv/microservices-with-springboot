package com.rakbank.student.enums;

public enum SchoolNames {

    SKIPLY_SCHOOL("Skiply School of Excellence - Integrated Model");

    private final String value;

    SchoolNames(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getValueByKey(String key) {
        for (SchoolNames e : SchoolNames.values()) {
            if (e.name().equals(key)) {
                return e.value;
            }
        }
        return null; // Or throw exception if key not found
    }
}
