package org.example.cron;

import org.example.cron.segment.CronCommandSegment;
import org.example.cron.segment.CronTimeSegment;

import java.util.List;

public record ValidCronPattern(String cron, List<CronTimeSegment> timeSegments,
                               CronCommandSegment commandSegment) implements CronPattern {

    @Override
    public boolean isValid() {
        return true;
    }

}
