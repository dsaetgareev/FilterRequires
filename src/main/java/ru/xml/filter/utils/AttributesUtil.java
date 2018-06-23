package ru.xml.filter.utils;


import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Create by dinis of 24.06.18.
 */
public class AttributesUtil {

    public static String getAttributesValues(Node node) {
        StringBuilder sb = new StringBuilder();
        NamedNodeMap map = node.getAttributes();
        if (map != null) {
            for (int i = 0; i < map.getLength(); i++) {
                sb.append(map.item(i).getTextContent() + " ");
            }
        }
        return sb.toString();
    }

}
