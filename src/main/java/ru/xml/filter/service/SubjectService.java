package ru.xml.filter.service;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.xml.filter.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сервис для субъектов.
 */
public class SubjectService {

    /**
     * Возвращает список субъектов.
     * @param node - искомая нода
     * @return - сисок субъектов
     */
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

    /**
     * Возвращает результат, содержится ли субъект с параметрами в списке.
     * @param type - параметр тип
     * @param value - параметр значение
     * @param subjects - список субъектов
     * @return - логический ответ
     */
    public boolean getContains(String type, String value, List<Subject> subjects) {
        boolean result = false;
        if (subjects.isEmpty() && "".equals(type) && "".equals(value)) {
            result = true;
        } else {
            for (Subject subject : subjects) {
                if (Objects.equals(type, subject.getType())
                        && Objects.equals(value, subject.getValue())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
