package ru.xml.filter.retriever;


import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Класс работает с xml файлом.
 */
public class Retriever {

    private static final Logger LOGGER = Logger.getLogger(Retriever.class);

    /**
     * Метод возвращает корневой элемент xml файла.
     * @return - корневой элемент
     */
    public Element getRootElement() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Ошибка при создании DocumentBuilder.", e);
            throw new RuntimeException();
        }
        Document doc = null;
        try {
            String path = this.getClass().getClassLoader().getResource("EditModeRestrictions.xml").getFile();
            doc = builder.parse(new File(path));
        } catch (SAXException | NullPointerException | IOException e) {
            LOGGER.error("Ошибка при создании документа, при парсинге xml файла.", e);
            throw new RuntimeException();
        }

        return doc.getDocumentElement();
    }

}
