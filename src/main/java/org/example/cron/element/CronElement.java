package org.example.cron.element;

import org.example.cron.level.CronLevel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: rename
public sealed interface CronElement permits FullRangeCronElement, ListCronElement, RangeCronElement, RepeatedCronElement {

    Pattern FULL_RANGE_PATTERN = Pattern.compile("\\*");
    Pattern LIST_PATTERN = Pattern.compile("(\\d+(,\\d+)*)");
    Pattern RANGE_PATTERN = Pattern.compile("(\\d+)-(\\d+)");
    Pattern REPEATED_PATTERN = Pattern.compile("\\*/(\\d+)");

    List<Integer> generateTimes(CronLevel level);

    static CronElement create(String pattern) {
        if (FULL_RANGE_PATTERN.asMatchPredicate().test(pattern)) {
            return new FullRangeCronElement();
        } else if (LIST_PATTERN.asMatchPredicate().test(pattern)) {
            Matcher matcher = LIST_PATTERN.matcher(pattern);
            matcher.find();
            List<Integer> times = Arrays.stream(matcher.group(1).split(","))
                    .map(Integer::valueOf)
                    .toList();
            return new ListCronElement(times);
        } else if (RANGE_PATTERN.asMatchPredicate().test(pattern)) {
            Matcher matcher = RANGE_PATTERN.matcher(pattern);
            matcher.find();
            return new RangeCronElement(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        } else if (REPEATED_PATTERN.asMatchPredicate().test(pattern)) {
            Matcher matcher = REPEATED_PATTERN.matcher(pattern);
            matcher.find();
            return new RepeatedCronElement(Integer.parseInt(matcher.group(1)));
        } else throw new IllegalArgumentException("Cannot parse time element: " + pattern);
    }

}
