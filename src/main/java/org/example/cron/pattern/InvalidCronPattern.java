package org.example.cron.pattern;

import org.example.cron.level.CronLevel;
import org.example.cron.segment.CronCommandSegment;
import org.example.cron.segment.CronTimeSegment;

import java.util.List;

record InvalidCronPattern(String cron) implements CronPattern {
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getCron() {
        return cron;
    }

    @Override
    public CronTimeSegment getTimeSegment(CronLevel level) {
        throw new UnsupportedOperationException("Operation not supported for cron: " + cron);
    }

    @Override
    public List<Integer> getTimes(CronLevel level) {
        throw new UnsupportedOperationException("Operation not supported for cron: " + cron);
    }

    @Override
    public CronCommandSegment getCommandSegment() {
        throw new UnsupportedOperationException("Operation not supported for cron: " + cron);
    }

    @Override
    public String getCommand() {
        throw new UnsupportedOperationException("Operation not supported for cron: " + cron);
    }

}
