package org.example;

import org.example.cron.formatter.CronFormatter;
import org.example.cron.pattern.CronPattern;

import java.util.Map;

public class Main {
    private static final CronFormatter CRON_FORMATTER = CronFormatter.builder().build();
    public static void main(String[] args) {
        CronPattern pattern = CronPattern.compile(args[0]);
        if (!pattern.isValid()) System.out.println("Pattern invalid");
        else {
            Map<String, String> descriptionTable = CRON_FORMATTER.createDescriptionTable(pattern);
            descriptionTable.forEach((k, v) -> System.out.println(k + v));
        }
    }
}