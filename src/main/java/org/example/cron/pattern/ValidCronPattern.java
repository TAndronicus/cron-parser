package org.example.cron.pattern;

import org.example.cron.level.CronLevel;
import org.example.cron.segment.CronCommandSegment;
import org.example.cron.segment.CronTimeSegment;

import java.util.List;

record ValidCronPattern(String cron, List<CronTimeSegment> timeSegments,
                               CronCommandSegment commandSegment) implements CronPattern {

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getCron() {
        return cron;
    }

    @Override
    public CronTimeSegment getTimeSegment(CronLevel level) {
        return timeSegments.stream()
                .filter(segment -> segment.cronLevel() == level)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Missing time segment " + level.name()));
    }

    @Override
    public List<Integer> getTimes(CronLevel level) {
        return getTimeSegment(level).generateTimes();
    }

    @Override
    public CronCommandSegment getCommandSegment() {
        return commandSegment;
    }

    @Override
    public String getCommand() {
        return commandSegment.command();
    }

}
