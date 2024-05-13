package org.example.cron;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class CronParser {
    private static final String CRON_ELEMENT_REGEX = "([0-9,*/-]*)";
    private static final String COMMAND_REGEX = "(.*)";
    private static final Pattern CRON_PATTERN = Pattern.compile(
            Stream.generate(() -> CRON_ELEMENT_REGEX)
                    .limit(5) // TODO: remove magic constant
                    .collect(joining(" ", "", " " + COMMAND_REGEX))
    );
    private final String cron;

    public CronParser(String cron) {
        this.cron = cron;
    }

    public boolean isValid() {
        return CRON_PATTERN.asMatchPredicate().test(cron);
    }

}
