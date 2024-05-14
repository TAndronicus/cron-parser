package org.example.cron.level;

public enum CronLevel {
    MINUTE(0, 59, "minute"),
    HOUR(0, 23, "hour"),
    DAY_OF_MONTH(1, 31, "day of month"),
    MONTH(1, 12, "month"),
    DAY_OF_WEEK(1, 7, "day of week");

    private final int minValue;
    private final int maxValue;
    private final String name;

    CronLevel(int minValue, int maxValue, String name) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.name = name;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getName() {
        return name;
    }
}
