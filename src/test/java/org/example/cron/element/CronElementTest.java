package org.example.cron.element;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class CronElementTest {

    @Test
    void givenFullRange_shouldCreateFullRangeElement() {
        // given
        String pattern = "*";
        FullRangeCronElement expectedElement = new FullRangeCronElement();

        // when
        CronElement actualElement = CronElement.create(pattern);

        // then
        assertThat(actualElement).isEqualTo(expectedElement);
    }

    @Test
    void givenSingleElement_shouldCreateListElement() {
        // given
        String pattern = "42";
        ListCronElement expectedElement = new ListCronElement(List.of(42));

        // when
        CronElement actualElement = CronElement.create(pattern);

        // then
        assertThat(actualElement).isEqualTo(expectedElement);
    }

    @Test
    void givenListOfElements_shouldCreateListElement() {
        // given
        String pattern = "2,3,5,7,11";
        ListCronElement expectedElement = new ListCronElement(List.of(2, 3, 5, 7, 11));

        // when
        CronElement actualElement = CronElement.create(pattern);

        // then
        assertThat(actualElement).isEqualTo(expectedElement);
    }

    @Test
    void givenRangeOfTimes_shouldCreateRangeElement() {
        // given
        String pattern = "13-17";
        RangeCronElement expectedElement = new RangeCronElement(13, 17);

        // when
        CronElement actualElement = CronElement.create(pattern);

        // then
        assertThat(actualElement).isEqualTo(expectedElement);
    }

    @Test
    void givenRepeatedPattern_shouldCreateRepeatedElement() {
        // given
        String pattern = "*/19";
        RepeatedCronElement expectedElement = new RepeatedCronElement(19);

        // when
        CronElement actualElement = CronElement.create(pattern);

        // then
        assertThat(actualElement).isEqualTo(expectedElement);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "23 29", "31,,37", "41-", "43-47,53", "59 - 61", "*/", "*67", "71/73"})
    void givenWrongPattern_shouldThrow(String pattern) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> CronElement.create(pattern))
                .withMessage("Cannot parse time element: " + pattern);
    }

}