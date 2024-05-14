package org.example.cron.element;

import org.example.cron.level.CronLevel;

import java.util.List;
import java.util.stream.IntStream;

public record RangeCronElement(int from, int to) implements CronElement {

    public RangeCronElement {
        if (from > to) throw new IllegalArgumentException("Range beginning should be larger than range end");
    }

    @Override
    public List<Integer> generateTimes(CronLevel level) {
        validate(level);
        return IntStream.rangeClosed(from, to)
                .boxed()
                .toList();
    }

    private void validate(CronLevel level) {
        if (from < level.getMinValue() || from > level.getMaxValue()) throw new IllegalArgumentException("Start out of range: " + from);
        if (to < level.getMinValue() || to > level.getMaxValue()) throw new IllegalArgumentException("End out of range: " + to);
    }

}
