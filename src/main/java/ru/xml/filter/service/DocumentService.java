package ru.xml.filter.service;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.xml.filter.model.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Create by dinis of 24.06.18.
 */
public class DocumentService {

    public List<Document> getDocuments(Node node) {
        List<Document> result = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (Objects.equals("classes", nodeList.item(i).getNodeName())) {
                NodeList classes = nodeList.item(i).getChildNodes();
                for (int j = 0; j < classes.getLength(); j++) {
                    Document document = new Document();
                    document.setName(classes.item(j).getTextContent());
                    result.add(document);
                }

            }
        }
        return result;
    }

}
