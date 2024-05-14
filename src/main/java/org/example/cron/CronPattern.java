package org.example.cron;

import org.example.cron.element.CronElement;
import org.example.cron.level.CronLevel;
import org.example.cron.segment.CronCommandSegment;
import org.example.cron.segment.CronTimeSegment;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public interface CronPattern {
    String CRON_ELEMENT_REGEX = "([0-9,*/-]*)";
    String COMMAND_REGEX = "(.*)";
    Pattern CRON_PATTERN = Pattern.compile(createCronRegex());

    private static String createCronRegex() {
        return Stream.generate(() -> CRON_ELEMENT_REGEX)
                .limit(CronLevel.values().length)
                .collect(joining(" ", "", " " + COMMAND_REGEX));
    }

    static CronPattern compile(String cron) {
        Matcher matcher = CRON_PATTERN.matcher(cron);
        if (matcher.find()) {
            List<CronTimeSegment> cronTimeSegments = extractTimeSegments(matcher);
            CronCommandSegment cronCommandSegment = extractCommandSegment(matcher);
            return new ValidCronPattern(cron, cronTimeSegments, cronCommandSegment);
        } else return new InvalidCronPattern(cron);
    }

    private static List<CronTimeSegment> extractTimeSegments(Matcher matcher) {
        return Arrays.stream(CronLevel.values())
                .map(level -> new CronTimeSegment(CronElement.create(matcher.group(level.ordinal() + 1)), level))
                .toList();
    }

    private static CronCommandSegment extractCommandSegment(Matcher matcher) {
        return new CronCommandSegment(matcher.group(CronLevel.values().length + 1));
    }

    boolean isValid();

    String getCron();

    CronTimeSegment getTimeSegment(CronLevel level);

    List<Integer> getTimes(CronLevel level);

    CronCommandSegment getCommandSegment();

    String getCommand();

}
