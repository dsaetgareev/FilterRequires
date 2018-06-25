package ru.xml.filter.service;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.xml.filter.model.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сервис для класса документа.
 */
public class DocumentService {

    /**
     * Возвращает список документов.
     * @param node - искомая нода
     * @return - список документов
     */
    public List<Document> getDocuments(Node node) {
        List<Document> result = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (Objects.equals("classes", nodeList.item(i).getNodeName())) {
                NodeList classes = nodeList.item(i).getChildNodes();
                for (int j = 0; j < classes.getLength(); j++) {
                    Node doc = classes.item(j);
                    if (!Objects.equals("#text", doc.getNodeName())) {
                        Document document = new Document();
                        document.setName(classes.item(j).getTextContent());
                        result.add(document);
                    }
                }

            }
        }
        return result;
    }

    /**
     * Возвращает логическое значение содержится ли документ в списке с такими параметрами.
     * @param name - параметр name
     * @param documents - список документов
     * @return - логическое значение
     */
    public boolean getContains(String name, List<Document> documents) {
        boolean result = false;
        if (documents.isEmpty() && "".equals(name)) {
            result = true;
        } else {
            for (Document document : documents) {
                if (Objects.equals(name, document.getName())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

}
