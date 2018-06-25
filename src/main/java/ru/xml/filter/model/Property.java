package ru.xml.filter.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Create by dinis of 24.06.18.
 */
public class Property {

    private String name;

    private List<Value> values;

    public List<String> fullName() {
        List<String> result = new ArrayList<>();
        values.forEach(value -> result.add(this.name + ": " + value.getValue()));
        if (this.values.isEmpty()) {
            result.add(this.name);
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}
