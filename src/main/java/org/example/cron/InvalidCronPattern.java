package org.example.cron;

public record InvalidCronPattern(String cron) implements CronPattern {
    @Override
    public boolean isValid() {
        return false;
    }
}
