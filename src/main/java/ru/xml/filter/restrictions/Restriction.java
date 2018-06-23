package ru.xml.filter.restrictions;


import org.w3c.dom.Node;
import ru.xml.filter.model.Document;
import ru.xml.filter.model.Property;
import ru.xml.filter.model.Subject;

import java.util.List;

/**
 * Create by dinis of 23.06.18.
 */
public class Restriction {

    private List<Subject> subjects;

    private List<Document> documents;

    private List<Property> properties;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
