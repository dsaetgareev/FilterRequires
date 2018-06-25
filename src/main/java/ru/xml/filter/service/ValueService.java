package ru.xml.filter.service;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.xml.filter.model.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сервис для значений свойств.
 */
public class ValueService {

    /**
     * Возвращает список значений свойств.
     * @param node - искомая нода
     * @return - список значений свойств
     */
    public List<Value> getValues(Node node) {
        List<Value> result = new ArrayList<>();

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node values = nodeList.item(i);
            if (Objects.equals("values", values.getNodeName())) {
                NodeList list = values.getChildNodes();
                for (int j = 0; j < list.getLength(); j++) {
                    if (!Objects.equals("#text", list.item(j).getNodeName())) {
                        Node valueNode = list.item(j);
                        Value value = new Value();
                        value.setValue(valueNode.getTextContent());
                        result.add(value);
                    }
                }

            }
            if (Objects.equals("value", values.getNodeName())) {
                Value value = new Value();
                value.setValue(values.getTextContent());
                result.add(value);
            }
        }
        return result;
    }

}
