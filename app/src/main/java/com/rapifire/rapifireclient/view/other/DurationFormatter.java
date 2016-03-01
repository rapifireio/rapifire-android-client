package com.rapifire.rapifireclient.view.other;

import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * Created by witek on 01.03.16.
 */
public class DurationFormatter {

    PeriodFormatter formatter;

    public DurationFormatter() {
        formatter = new PeriodFormatterBuilder()
            .appendDays()
            .appendSuffix("d")
            .appendSeparator(" ")
            .appendHours()
            .appendSuffix("h")
            .appendSeparator(" ")
            .appendMinutes()
            .appendSuffix("m")
            .appendSeparator(" ")
            .appendSeconds()
            .appendSuffix("s")
            .toFormatter();
    }

    public String formatDurationInMillis(long duration) {
        Duration dur = Duration.millis(duration);

        return formatter.print(dur.toPeriod().normalizedStandard());
    }

    public String formatDurationInMillisTolastOccurence(Long duration) {
        if(duration == null) {
            return "never";
        }

        return String.format("%s ago", formatDurationInMillis(duration));
    }
}
