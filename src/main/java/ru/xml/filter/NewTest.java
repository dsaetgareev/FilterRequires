package ru.xml.filter;

import ru.xml.filter.model.Answer;
import ru.xml.filter.model.Document;
import ru.xml.filter.model.Property;
import ru.xml.filter.model.Subject;
import ru.xml.filter.model.Value;
import ru.xml.filter.restrictions.Restriction;
import ru.xml.filter.service.PropertyService;
import ru.xml.filter.service.RestrictionService;

import java.util.List;
import java.util.Scanner;

public class NewTest {

    public static void main(String[] args) {
        RestrictionService restrictionService = new RestrictionService();
        System.out.println("Введите Ваш тип: ");
        String type = new Scanner(System.in).nextLine();
        System.out.println("Введите значение типа:");
        String typeValue = new Scanner(System.in).nextLine();
        System.out.println("Введите название документа:");
        String docName = new Scanner(System.in).nextLine();
        System.out.println("Введите статус:");
        String statusName = new Scanner(System.in).nextLine();

        List<Restriction> restrictions = restrictionService.getRestrictions();
        List<Restriction> newRestrs = restrictionService.restrictionsFilter(type, typeValue,
                docName,statusName, restrictions);
        System.out.println(newRestrs);
        System.out.println("Найдено " + newRestrs.size() + " документ(а).");
        for (Restriction restriction : newRestrs) {
            if (!restriction.getDocuments().isEmpty()) {
                System.out.println("Документы:");

                restriction.getDocuments().stream()
                        .forEach(document -> System.out.println("   " + document.getName()));
            }
            if (!restriction.getSubjects().isEmpty()) {
                System.out.println("Субъекты:");
                restriction.getSubjects().stream()
                        .forEach(subject -> System.out.println("   " + subject.getType() + " " + subject.getValue()));
            }
            if (!restriction.getProperties().isEmpty()) {
                List<Property> properties = restriction.getProperties();
                for (Property property: properties) {

                    property.fullName().forEach(value -> System.out.println(value));
                }
            }
            int count = 1;
            System.out.println("Рузультат:");
            for (Answer answer : restriction.getAnswers()) {
                System.out.println(count++ + "." + answer.getValue());
            }
        }
    }
}
