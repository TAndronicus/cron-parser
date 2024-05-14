# cron-parser
## What is it?
It's a library to parse cron strings.
It has no external dependencies in production code.
## How to build it?
Despite being free of dependencies in production code, it's a gradle project to manage test dependencies.

Simply import it as a gradle project in your favorite IDE.
## How to use it?
You can use it as a standalone jar to parse cron. For your convenience a compiled jar resides in lib catalog. You can simply call:

```java -jar cron-parser.jar "<cron>```

For example:

```java -jar cron-parser.jar "*/15 0 1,15 * 1-5 /usr/bin/find"```

You should expect the following output:

```bibtex
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```

You can use it as a library in your project:

```bibtex
CronPattern pattern = CronPattern.compile("*/15 0 1,15 * 1-5 /usr/bin/find");
if (pattern.isValid()) System.out.println(pattern.getCommand);
```

Which should print `/usr/bin/find`.

## Possible future work (and other considerations)
1. Make the standalone jar more extensible by interpreting additional input arguments as parameters for the formatter.
2. Add more validation, make the exception messages more usable.
3. Add "fail-slow" validation to list all the exceptions (all mistakes in cron string). The effect should be similar to Validated in scala-cats / vavr.
4. The CRON_PATTERN in CronPattern is written to be as much readable as possible. The extended regex could provide additional validation now included in classes implementing `CronElement`;
5. A nice idea would be to build a schema in a fluent manner. Something like:
```bibtex
var pattern = CronPattern.builder()
    .timeSegment("minute", 0, 59)
    .timeSegment("hour", 0, 23)
    ...
    .stringSegment("command")
    .stringSegment("command arguments")
    .build()
    .compile("*/15 0 1,15 * 1-5 /usr/bin/find --help");
if (pattern.isValid()) System.out.println(pattern.getSegment("command arguments")); // should print "--help"
```