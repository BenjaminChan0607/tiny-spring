package com.cs.tiny.beans;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 上午 11:12
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
