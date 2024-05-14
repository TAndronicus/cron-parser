package org.example.cron.formatter;

import org.example.cron.level.CronLevel;
import org.example.cron.pattern.CronPattern;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

public record CronFormatter(int firstColumnWidth, String timesSeparator, String commandTitle) {

    public static class CronFormatterBuilder {

        private int firstColumnWidth = 14;
        private String timesSeparator = " ";
        private String commandTitle = "command";

        public CronFormatterBuilder firstColumnWidth(int firstColumnWidth) {
            this.firstColumnWidth = firstColumnWidth;
            return this;
        }

        public CronFormatterBuilder timesSeparator(String timesSeparator) {
            this.timesSeparator = timesSeparator;
            return this;
        }

        public CronFormatterBuilder commandTitle(String commandTitle) {
            this.commandTitle = commandTitle;
            return this;
        }

        public CronFormatter build() {
            return new CronFormatter(firstColumnWidth, timesSeparator, commandTitle);
        }
    }

    public static CronFormatter.CronFormatterBuilder builder() {
        return new CronFormatterBuilder();
    }

    public Map<String, String> createDescriptionTable(CronPattern cronPattern) {
        if (!cronPattern.isValid()) return Map.of();
        LinkedHashMap<String, String> result = Arrays.stream(CronLevel.values())
                .collect(toMap(
                        level -> pad(level.getName()),
                        level -> cronPattern.getTimes(level).stream().map(Objects::toString).collect(joining(timesSeparator)),
                        (s1, s2) -> s2,
                        LinkedHashMap::new
                ));
        result.put(pad(commandTitle), cronPattern.getCommand());
        return result;
    }

    private String pad(String input) {
        if (input.length() >= firstColumnWidth) return input.substring(0, firstColumnWidth);
        else return input + " ".repeat(firstColumnWidth - input.length());
    }
}
