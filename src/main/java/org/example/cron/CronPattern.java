package org.example.cron;

import org.example.cron.element.CronElement;
import org.example.cron.level.CronLevel;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class CronPattern {
    private static final String CRON_ELEMENT_REGEX = "([0-9,*/-]*)";
    private static final String COMMAND_REGEX = "(.*)";
    private static final Pattern CRON_PATTERN = Pattern.compile(createCronRegex());

    private final String cron;
    private final List<CronTimeElement> timeElements;
    private final CronCommandElement commandElement;

    private static String createCronRegex() {
        return Stream.generate(() -> CRON_ELEMENT_REGEX)
                .limit(CronLevel.values().length)
                .collect(joining(" ", "", " " + COMMAND_REGEX));
    }

    private CronPattern(String cron, List<CronTimeElement> timeElements, CronCommandElement commandElement) {
        this.cron = cron;
        this.timeElements = timeElements;
        this.commandElement = commandElement;
    }

    public static CronPattern compile(String cron) {
        Matcher matcher = CRON_PATTERN.matcher(cron);
        if (matcher.find()) {
            List<CronTimeElement> cronTimeElements = extractTimeElements(matcher);
            CronCommandElement cronCommandElement = extractCommandElement(matcher);
            return new CronPattern(cron, cronTimeElements, cronCommandElement);
        } else return new CronPattern(cron, null, null);
    }

    private static List<CronTimeElement> extractTimeElements(Matcher matcher) {
        return Arrays.stream(CronLevel.values())
                .map(level -> new CronTimeElement(CronElement.create(matcher.group(level.ordinal() + 1)), level))
                .toList();
    }

    private static CronCommandElement extractCommandElement(Matcher matcher) {
        return new CronCommandElement(matcher.group(CronLevel.values().length + 1));
    }

    public boolean isValid() {
        return CRON_PATTERN.asMatchPredicate().test(cron);
    }

}
