package org.example.cron.element;

import org.example.cron.level.CronLevel;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public sealed interface CronElement permits FullRangeCronElement, ListCronElement, RangeCronElement, RepeatedCronElement {

    Pattern FULL_RANGE_PATTERN = Pattern.compile("\\*");
    Pattern LIST_PATTERN = Pattern.compile("(\\d+(,\\d+)*)");
    Pattern RANGE_PATTERN = Pattern.compile("(\\d+)-(\\d+)");
    Pattern REPEATED_PATTERN = Pattern.compile("\\*/(\\d+)");

    List<Integer> generateTimes(CronLevel level);

    static CronElement create(String cron) {
        if (FULL_RANGE_PATTERN.asMatchPredicate().test(cron)) {
            return new FullRangeCronElement();
        } else if (LIST_PATTERN.asMatchPredicate().test(cron)) {
            Matcher matcher = getMatcher(LIST_PATTERN, cron);
            List<Integer> times = Arrays.stream(matcher.group(1).split(","))
                    .map(Integer::valueOf)
                    .toList();
            return new ListCronElement(times);
        } else if (RANGE_PATTERN.asMatchPredicate().test(cron)) {
            Matcher matcher = getMatcher(RANGE_PATTERN, cron);
            return new RangeCronElement(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        } else if (REPEATED_PATTERN.asMatchPredicate().test(cron)) {
            Matcher matcher = getMatcher(REPEATED_PATTERN, cron);
            return new RepeatedCronElement(Integer.parseInt(matcher.group(1)));
        } else throw new IllegalArgumentException("Cannot parse time element: " + cron);
    }

    private static Matcher getMatcher(Pattern pattern, String cron) {
        Matcher matcher = pattern.matcher(cron);
        matcher.find();
        return matcher;
    }

}
