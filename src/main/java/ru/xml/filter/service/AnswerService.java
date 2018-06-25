package ru.xml.filter.service;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.xml.filter.model.Answer;
import ru.xml.filter.utils.AttributesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сервис для ответов.
 */
public class AnswerService {

    /**
     * Возвращает спсок ответов.
     *
     * @param node - искомая нода
     * @return - список ответов
     */
    public List<Answer> getAnswerList(Node node) {
        List<Answer> result = new ArrayList<>();
        if (node != null) {
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node property = nodeList.item(i);
                if (Objects.equals("property", property.getNodeName())
                        && !Objects.equals("#text", property.getNodeName())) {
                    Answer answer = new Answer();
                    answer.setValue(AttributesUtil.getAttributesValues(property));
                    result.add(answer);
                }
            }
        }
        return result;
    }
}
