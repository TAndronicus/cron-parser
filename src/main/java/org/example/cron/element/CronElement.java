package org.example.cron.element;

import org.example.cron.level.CronLevel;

import java.util.List;

public sealed interface CronElement permits FullRangeCronElement, ListCronElement, RangeCronElement, RepeatedCronElement {

    List<Integer> generateTimes(CronLevel level);
}
