package ru.xml.filter.service;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.xml.filter.model.Property;
import ru.xml.filter.utils.AttributesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Create by dinis of 24.06.18.
 */
public class PropertyService {

    private ValueService valueService = new ValueService();

    public List<Property> getProperties(Node node) {
        List<Property> result = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (Objects.equals("properties", nodeList.item(i).getNodeName())) {
                NodeList properties = nodeList.item(i).getChildNodes();
                for (int j = 0; j < properties.getLength(); j++) {
                    if (!Objects.equals("#text", properties.item(j).getNodeName())) {
                        Node prop = properties.item(j);

                        Property property = new Property();
                        String attr = AttributesUtil.getAttributesValues(prop);
                        property.setName(attr);
                        property.setValues(this.valueService.getValues(prop));
                        result.add(property);
                    }
                }
            }
        }
        return result;
    }

}
