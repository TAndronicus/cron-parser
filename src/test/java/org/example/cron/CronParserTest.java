package org.example.cron;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CronParserTest {

    @Test
    void givenEmptyString_shouldMarkAsInvalid() {
        // given
        String cron = "";

        // when
        CronParser cronParser = new CronParser(cron);

        // then
        assertFalse(cronParser.isValid());
    }

    @Test
    void givenMissingCronExpressions_shouldMarkAsInvalid() {
        // given
        String cron = "0 1 2 echo /etc/passwd";

        // when
        CronParser cronParser = new CronParser(cron);

        // when
        assertFalse(cronParser.isValid());
    }

    @Test
    void givenCorrectCronExpression_shouldMarkAsValid() {
        // given
        String cron = "*/15 0 1,15 * 1-5 /usr/bin/find";

        // when
        CronParser cronParser = new CronParser(cron);

        // then
        assertTrue(cronParser.isValid());
    }

}