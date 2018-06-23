package ru.xml.filter;


import ru.xml.filter.model.Property;
import ru.xml.filter.restrictions.Restriction;
import ru.xml.filter.service.RestrictionService;

import java.util.List;

/**
 * Create by dinis of 24.06.18.
 */
public class Test {

    public static void main(String[] args) {
        Restriction restriction = new RestrictionService().getRestrictions().get(34);
        if (!restriction.getDocuments().isEmpty()) {
            restriction.getDocuments().stream()
                    .forEach(document -> System.out.println(document.getName()));
        }
        if (!restriction.getSubjects().isEmpty()) {
            restriction.getSubjects().stream()
                    .forEach(subject -> System.out.println(subject.getType() + " " + subject.getValue()));
        }
        if (!restriction.getProperties().isEmpty()) {
            List<Property> properties = restriction.getProperties();
            for (Property property: properties) {
                System.out.println(property.getName());
                property.getValues().forEach(value -> System.out.println(value.getValue() + ", "));
            }
        }
    }

}
