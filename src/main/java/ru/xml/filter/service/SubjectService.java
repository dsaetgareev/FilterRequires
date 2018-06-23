package ru.xml.filter.service;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.xml.filter.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Create by dinis of 24.06.18.
 */
public class SubjectService {

    public List<Subject> getSubjects(Node node) {
        List<Subject> subjectList = new ArrayList<>();
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (Objects.equals("subjects", list.item(i).getNodeName())) {
                NodeList subjects = list.item(i).getChildNodes();
                for (int j = 0; j < subjects.getLength(); j++) {
                    if (subjects.item(j).getAttributes() != null) {
                        Subject subject = new Subject();
                    subject.setType(subjects.item(j).getAttributes().getNamedItem("type").getTextContent());
                    subject.setValue(subjects.item(j).getTextContent());
                    subjectList.add(subject);
                    }
                }
            }
        }
        return subjectList;
    }

}
