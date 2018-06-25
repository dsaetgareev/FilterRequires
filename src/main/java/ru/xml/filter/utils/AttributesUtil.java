package ru.xml.filter.utils;


import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Класс работает с атрибутами.
 */
public class AttributesUtil {

    private AttributesUtil() {
    }

    /**
     * Соедеиняет значения атрибутов ноды в отдну строку.
     * @param node - текучая нода
     * @return - строка
     */
    public static String getAttributesValues(Node node) {
        StringBuilder sb = new StringBuilder();
        NamedNodeMap map = node.getAttributes();
        if (map != null) {
            for (int i = 0; i < map.getLength(); i++) {
                sb.append(map.item(i).getTextContent() + " ");
            }
        }
        return sb.toString().trim();
    }

}
