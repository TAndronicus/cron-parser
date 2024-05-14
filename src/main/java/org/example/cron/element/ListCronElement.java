package org.example.cron.element;

import org.example.cron.level.CronLevel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public record ListCronElement(Collection<Integer> times) implements CronElement {

    @Override
    public List<Integer> generateTimes(CronLevel level) {
        validate(level);
        return new ArrayList<>(times);
    }

    private void validate(CronLevel level) {
        String wrongElements = times.stream()
                .filter(time -> time < level.getMinValue() || time > level.getMaxValue())
                .map(Objects::toString)
                .collect(joining(", "));
        if (!wrongElements.isBlank()) throw new IllegalArgumentException("Times out of range for level %s: %s".formatted(level.getName(), wrongElements));
    }

}
