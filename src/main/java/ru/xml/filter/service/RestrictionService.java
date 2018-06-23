package ru.xml.filter.service;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.xml.filter.restrictions.Restriction;
import ru.xml.filter.retriever.Retriever;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by dinis of 24.06.18.
 */
public class RestrictionService {

    private Retriever retriever = new Retriever();

    private SubjectService subjectService = new SubjectService();

    private DocumentService documentService = new DocumentService();

    private PropertyService propertyService = new PropertyService();

    public Element getRootElement() {
        return this.retriever.getRootElement();
    }

    public List<Restriction> getRestrictions() {
        Element element = this.getRootElement();

        List<Restriction> result = new ArrayList<>();

        NodeList restrictionList = element.getElementsByTagName("conditions");
        for(int i = 0; i < restrictionList.getLength(); i++) {
            Node node = restrictionList.item(i);
            Restriction restriction = new Restriction();
            restriction.setSubjects(subjectService.getSubjects(node));
            restriction.setDocuments(this.documentService.getDocuments(node));
            restriction.setProperties(this.propertyService.getProperties(node));
            result.add(restriction);
        }
        return result;
    }

}
