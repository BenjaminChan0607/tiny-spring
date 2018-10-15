package com.cs.tiny.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 上午 11:13
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    public PropertyValues() {
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValueList;
    }
}