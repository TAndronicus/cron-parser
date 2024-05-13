package org.example.cron.element;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.example.cron.level.CronLevel.DAY_OF_WEEK;

class RangeCronElementTest {

    @Test
    void givenRange_shouldReturnAllElementsInBetween() {
        // given
        RangeCronElement rangeCronElement = new RangeCronElement(2, 4);
        List<Integer> expectedDays = List.of(2, 3, 4);

        // when
        List<Integer> actualDays = rangeCronElement.generateTimes(DAY_OF_WEEK);

        // then
        assertThat(actualDays).containsExactlyElementsOf(expectedDays);
    }

    @Test
    void givenLevelOutOfRange_shouldThrow() {
        // given
        RangeCronElement rangeCronElement = new RangeCronElement(5, 8);

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> rangeCronElement.generateTimes(DAY_OF_WEEK))
                .withMessage("End out of range: 8");
    }

}