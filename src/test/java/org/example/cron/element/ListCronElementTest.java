package org.example.cron.element;

import org.example.cron.level.CronLevel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ListCronElementTest {

    @Test
    void givenListOfCorrectTimes_shouldReturnTheSameList() {
        // given
        List<Integer> expectedDays = List.of(1, 3, 6);
        ListCronElement listCronElement = new ListCronElement(expectedDays);

        // when
        List<Integer> actualDays = listCronElement.generateTimes(CronLevel.DAY_OF_WEEK);

        // then
        assertThat(actualDays).containsExactlyElementsOf(expectedDays);
    }

    @Test
    void givenListWithElementsOutOfRange_shouldReportOutstandingElements() {
        // given
        ListCronElement listCronElement = new ListCronElement(List.of(1, 8));

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> listCronElement.generateTimes(CronLevel.DAY_OF_WEEK))
                .withMessage("Times out of range: 8");
    }

}