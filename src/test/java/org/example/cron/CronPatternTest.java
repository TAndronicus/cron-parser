package org.example.cron;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CronPatternTest {

    @Test
    void givenEmptyString_shouldMarkAsInvalid() {
        // given
        String cron = "";

        // when
        CronPattern cronPattern = CronPattern.compile(cron);

        // then
        assertFalse(cronPattern.isValid());
    }

    @Test
    void givenMissingCronExpressions_shouldMarkAsInvalid() {
        // given
        String cron = "0 1 2 echo /etc/passwd";

        // when
        CronPattern cronPattern = CronPattern.compile(cron);

        // when
        assertFalse(cronPattern.isValid());
    }

    @Test
    void givenCorrectCronExpression_shouldMarkAsValid() {
        // given
        String cron = "*/15 0 1,15 * 1-5 /usr/bin/find";

        // when
        CronPattern cronPattern = CronPattern.compile(cron);

        // then
        assertTrue(cronPattern.isValid());
    }

}