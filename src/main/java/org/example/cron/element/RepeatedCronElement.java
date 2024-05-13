package org.example.cron.element;

import org.example.cron.level.CronLevel;

import java.util.List;
import java.util.stream.IntStream;

public record RepeatedCronElement(int period) implements CronElement {

    @Override
    public List<Integer> generateTimes(CronLevel level) {
        return IntStream.iterate(level.getMinValue(), time -> time < level.getMaxValue(), time -> time + period)
                .boxed()
                .toList();
    }

}
