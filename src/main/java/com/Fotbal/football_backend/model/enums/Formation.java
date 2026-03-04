package com.Fotbal.football_backend.model.enums;

public enum Formation {
    F_4_3_3("4-3-3"),
    F_4_4_2("4-4-2"),
    F_4_2_4("4-2-4");

    private final String value;

    Formation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
