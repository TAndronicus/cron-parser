package org.example.cron.formatter;

import org.example.cron.pattern.CronPattern;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

class CronFormatterTest {

    @Test
    void givenValidCronPatter_shouldFormatCorrectly() {
        // given
        CronFormatter cronFormatter = CronFormatter.builder()
                .firstColumnWidth(15)
                .commandTitle("cmd")
                .timesSeparator(", ")
                .build();
        String cron = "*/15 0 1,15 * 1-5 /usr/bin/find";
        LinkedHashMap<String, String> expectedResult = Stream.of(
                Map.entry("minute         ", "0, 15, 30, 45"),
                Map.entry("hour           ", "0"),
                Map.entry("day of month   ", "1, 15"),
                Map.entry("month          ", "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12"),
                Map.entry("day of week    ", "1, 2, 3, 4, 5"),
                Map.entry("cmd            ", "/usr/bin/find")
        ).collect(toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (s1, s2) -> s2,
                LinkedHashMap::new
        ));

        // when
        LinkedHashMap<String, String> actualResult = (LinkedHashMap<String, String>) cronFormatter.createDescriptionTable(CronPattern.compile(cron));

        // then
        assertThat(actualResult).containsAllEntriesOf(expectedResult);
        assertThat(actualResult.sequencedKeySet()).containsExactlyElementsOf(expectedResult.sequencedKeySet());
    }

}