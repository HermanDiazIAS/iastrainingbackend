package com.co.ias.birds.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class BirdZoneName {
    private final String value;

    public BirdZoneName(String value) {
        value = value.trim();
        Validate.notNull(value,"The common name zone field cannot be empty or null.");
        Validate.isTrue(value.length() <= 20,"The maximum name zone size is 20 characters.");

        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
