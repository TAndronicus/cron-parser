package org.example.cron.element;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.cron.level.CronLevel.DAY_OF_MONTH;
import static org.example.cron.level.CronLevel.HOUR;

class RepeatedCronElementTest {

    @Test
    void givenEveryHour_shouldReturnAllHours() {
        // given
        RepeatedCronElement repeatedCronElement = new RepeatedCronElement(1);
        List<Integer> expectedHours = IntStream.range(0, 23).boxed().toList();

        // when
        List<Integer> actualHours = repeatedCronElement.generateTimes(HOUR);

        // then
        assertThat(actualHours).containsExactlyElementsOf(expectedHours);
    }
    @Test
    void givenEveryOtherDay_shouldReturnOddDays() {
        // given
        RepeatedCronElement repeatedCronElement = new RepeatedCronElement(2);
        List<Integer> expectedDays = IntStream.range(0, 15)
                .map(i -> 2 * i + 1)
                .boxed()
                .toList();

        // when
        List<Integer> actualDays = repeatedCronElement.generateTimes(DAY_OF_MONTH);

        // then
        assertThat(actualDays).containsExactlyElementsOf(expectedDays);
    }

}