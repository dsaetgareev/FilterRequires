package ru.xml.filter;

import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.xml.filter.retriever.Retriever;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


public class Service {

    public Element getRootElement() {
        return new Retriever().getRootElement();
    }

    /**
     * Возвращает список нод по его тегу.
     * @param tag тег ноды
     * @return список нод
     */
    public NodeList getNodeList(String tag) {
        return this.getRootElement().getElementsByTagName(tag);
    }

    /**
     * Возвращает спсок значений атрибута по атрибуту.
     *
     * @param attr - наименование атрибута
     * @param list - список нод
     * @return список значений атрибута
     */
    public List<String> getAttributesValue(String attr, NodeList list) {
        ArrayList<String> attrList = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            attrList.add(list.item(i).getAttributes().getNamedItem(attr).getTextContent());
        }
        return new ArrayList<>(new HashSet<>(attrList));
    }

    /**
     * Возвращает значеня нод по его тегу.
     * @param tag - наименование тега
     * @return список значени нод
     */
    public List<String> parser(String tag) {
        NodeList list = this.getNodeList(tag);

        ArrayList<String> nodeList = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            System.out.println(node.getAttributes().getNamedItem("type").getTextContent());
            nodeList.add(list.item(i).getTextContent());
        }
        nodeList = new ArrayList<>(new HashSet<>(nodeList));

        return nodeList;
    }

    public NodeList getNodeListByAttrValue(String attr, String attrValue, NodeList list) {
        NodeList result = null;
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if(Objects.equals(attrValue, node.getAttributes().getNamedItem(attr).getTextContent())) {
                result = this.getRootElement().getElementsByTagName(node.getLocalName());

            }
        }
        return result;
    }


    /**
     * Возвращает список значений нод по значению атрибута.
     * @param attr - наименование атрибута
     * @param attrValue - значение атрибута
     * @return список нод
     */
    public List<String> getValueNodeListByAttrValue(String attr, String attrValue, NodeList list) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if(Objects.equals(attrValue, node.getAttributes().getNamedItem(attr).getTextContent())) {
                result.add(node.getTextContent());
            }
        }
        return result;
    }

    public List<String> getValueNodeListBySiblingChild(String nodeValue, NodeList list) {
        List<String> result = new ArrayList<>();
        NodeContainer container = new NodeContainer();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            String value = node.getTextContent();
            if (Objects.equals(nodeValue, value)) {
//                System.out.println(node.getParentNode().getNextSibling().getNextSibling().getNodeName());
                if ("classes".equals(node.getParentNode().getNextSibling().getNextSibling().getNodeName())) {
                    container.setClasses(node.getParentNode().getNextSibling().getNextSibling().getChildNodes());
                    for (int j = 0; j < container.getClasses().getLength(); j++) {
                        System.out.println(container.getClasses().item(j).getTextContent());
                    }
                }

                Node node1 = node.getParentNode().getNextSibling().getNextSibling();

                if ("properties".equals(node1.getNodeName())) {
                    System.out.println(node1.getNodeName());
                    if ("Status".equals(node1.getFirstChild().getNextSibling().getAttributes().getNamedItem("name").getTextContent())) {
                        Node child = node1.getFirstChild().getNextSibling().getFirstChild().getNextSibling();
                        System.out.println(child.getNodeName());
                        container.setProperties(child.getChildNodes());

                        for (int t = 0; t < container.getProperties().getLength(); t++) {
                            result.add(container.getProperties().item(t).getTextContent());
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Service service = new Service();
        NodeList list = service.getNodeList("subject");
        System.out.println(service.getAttributesValue("type", list));
        System.out.println(service.getValueNodeListByAttrValue("type", "role", list));

        System.out.println(service.getValueNodeListBySiblingChild("Регистратор ХБиД", list));

    }
}
