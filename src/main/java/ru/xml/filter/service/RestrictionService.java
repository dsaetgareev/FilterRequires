package ru.xml.filter.service;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.xml.filter.model.Property;
import ru.xml.filter.restrictions.Restriction;
import ru.xml.filter.retriever.Retriever;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для ограничений.
 */
public class RestrictionService {

    private Retriever retriever = new Retriever();

    private SubjectService subjectService = new SubjectService();

    private DocumentService documentService = new DocumentService();

    private PropertyService propertyService = new PropertyService();

    private AnswerService answerService = new AnswerService();

    /**
     * Возвращает корневой элемент.
     * @return - корневой элемент
     */
    public Element getRootElement() {
        return this.retriever.getRootElement();
    }

    /**
     * Возвращает весь список оргнаничений.
     * @return - список ограничений
     */
    public List<Restriction> getRestrictions() {
        Element element = this.getRootElement();
        List<Restriction> result = new ArrayList<>();
        NodeList restrictionList = element.getElementsByTagName("conditions");
        for (int i = 0; i < restrictionList.getLength(); i++) {
            Node node = restrictionList.item(i);
            Node propertiesNode = node.getNextSibling().getNextSibling();
            Restriction restriction = new Restriction();
            restriction.setSubjects(subjectService.getSubjects(node));
            restriction.setDocuments(this.documentService.getDocuments(node));
            restriction.setProperties(this.propertyService.getProperties(node));
            restriction.setAnswers(this.answerService.getAnswerList(propertiesNode));
            result.add(restriction);
        }
        return result;
    }


    /**
     * Фильтр для списка ограничений.
     * @param type - тип пользователя
     * @param value - значение пользователя
     * @param docName - название класса документа
     * @param name - строка статуса
     * @param restrictions - спсок фильтруемых ограниченийй
     * @return
     */
    public List<Restriction> restrictionsFilter(String type, String value, String docName, String name,
                                                List<Restriction> restrictions) {
        List<Restriction> restrictionList = new ArrayList<>();

        for (Restriction restriction : restrictions) {
            if (this.subjectService.getContains(type, value, restriction.getSubjects())
                    && this.documentService.getContains(docName, restriction.getDocuments())) {
                for (Property property : restriction.getProperties()) {
                    List<String> fullNames = property.fullName();
                    if (this.propertyService.getContains(name, fullNames)) {
                        restrictionList.add(restriction);
                        break;
                    }
                }
                if ("".equals(name)) {
                    restrictionList.add(restriction);
                }

            }
        }
        return restrictionList;
    }

}
