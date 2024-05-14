package org.example.cron.segment;

import org.example.cron.element.CronElement;
import org.example.cron.level.CronLevel;

import java.util.List;

public record CronTimeSegment(CronElement cronElement, CronLevel cronLevel) {

    public List<Integer> generateTimes() {
        return cronElement.generateTimes(cronLevel);
    }

}
