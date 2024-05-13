package org.example.cron.element;

import org.example.cron.level.CronLevel;

import java.util.List;
import java.util.stream.IntStream;

public record FullRangeCronElement() implements CronElement {
    @Override
    public List<Integer> generateTimes(CronLevel level) {
        return IntStream.rangeClosed(level.getMinValue(), level.getMaxValue())
                .boxed()
                .toList();
    }
}
