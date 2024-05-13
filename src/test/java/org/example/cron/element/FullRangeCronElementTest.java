package org.example.cron.element;

import org.example.cron.level.CronLevel;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.cron.level.CronLevel.MINUTE;
import static org.junit.jupiter.api.Assertions.*;

class FullRangeCronElementTest {

    @Test
    void givenFullRange_shouldReturnAllPossibleElementsForLevel() {
        // given
        FullRangeCronElement fullRangeCronElement = new FullRangeCronElement();
        List<Integer> expectedMinutes = IntStream.rangeClosed(0, 59)
                .boxed()
                .toList();

        // when
        List<Integer> actualMinutes = fullRangeCronElement.generateTimes(MINUTE);

        // then
        assertThat(actualMinutes).containsExactlyElementsOf(expectedMinutes);
    }

}