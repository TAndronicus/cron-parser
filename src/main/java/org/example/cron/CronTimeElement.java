package org.example.cron;

import org.example.cron.element.CronElement;
import org.example.cron.level.CronLevel;

import java.util.List;

public record CronTimeElement(CronElement cronElement, CronLevel cronLevel) {

    public List<Integer> generateTimes(CronLevel level) {
        return cronElement.generateTimes(cronLevel);
    }

}
