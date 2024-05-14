package org.example.cron.pattern;

import org.example.cron.element.FullRangeCronElement;
import org.example.cron.element.ListCronElement;
import org.example.cron.element.RangeCronElement;
import org.example.cron.element.RepeatedCronElement;
import org.example.cron.level.CronLevel;
import org.example.cron.segment.CronCommandSegment;
import org.example.cron.segment.CronTimeSegment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ValidCronPatternTest {

    @Test
    void givenCorrectCron_shouldReturnValidCronPattern() {
        // given
        String cron = "*/15 0 1,15 * 1-5 /usr/bin/find --help";
        ValidCronPattern expectedPattern = new ValidCronPattern(
                cron,
                List.of(
                        new CronTimeSegment(new RepeatedCronElement(15), CronLevel.MINUTE),
                        new CronTimeSegment(new ListCronElement(List.of(0)), CronLevel.HOUR),
                        new CronTimeSegment(new ListCronElement(List.of(1, 15)), CronLevel.DAY_OF_MONTH),
                        new CronTimeSegment(new FullRangeCronElement(), CronLevel.MONTH),
                        new CronTimeSegment(new RangeCronElement(1, 5), CronLevel.DAY_OF_WEEK)
                ),
                new CronCommandSegment("/usr/bin/find --help"));

        // when
        CronPattern actualPattern = CronPattern.compile(cron);

        // then
        assertThat(actualPattern.isValid()).isTrue();
        assertThat(actualPattern).isEqualTo(expectedPattern);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "*/15 0 1,15 * /usr/bin/find",
            "*/15 0 1,15 * 1-5",
            "cron"
    })
    void givenIncorrectCron_shouldReturnInValidCronPattern(String cron) {
        // when
        CronPattern actualPattern = CronPattern.compile(cron);

        // then
        assertThat(actualPattern.isValid()).isFalse();
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(actualPattern::getCommand)
                .withMessage("Operation not supported for cron: " + cron);
    }

}