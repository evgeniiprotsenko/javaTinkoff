package edu.hw5;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tasks {

    //Task1
    public String getAverageTime(List<String> input) {
        String regex = " - ";
        Duration duration;
        List<Long> list = new ArrayList<>();

        for (var visitor : input) {
            var beforeRegex = visitor.split(regex)[0];
            var afterRegex = visitor.split(regex)[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
            LocalDateTime in = LocalDateTime.parse(beforeRegex, formatter);
            LocalDateTime out = LocalDateTime.parse(afterRegex, formatter);
            duration = Duration.between(in, out);
            list.add(duration.toMinutes());
        }

        Duration averageDuration;

        if (list.size() > 1) {
            averageDuration = Duration.ofMinutes(list.stream().mapToInt(Long::intValue).sum() / list.size());
        } else if (list.size() == 1) {
            averageDuration = Duration.ofMinutes(list.get(0));
        } else {
            return null;
        }

        return averageDuration.toMinutesPart() == 0 ? averageDuration.toHoursPart() + "ч"
            : averageDuration.toHoursPart() + "ч " + averageDuration.toMinutesPart() + "м";

    }

    //Task2.1
    private static final int NECESSARY_DAY_OF_MONTH = 13;

    public List<LocalDate> getAllFriday13th(int year) {
        List<LocalDate> result = new ArrayList<>();
        LocalDate localDate = LocalDate.of(year, 1, 1);
        LocalDate endLocalDate = localDate.plusYears(1);

        while (localDate.isBefore(endLocalDate)) {
            if (localDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)
                && localDate.getDayOfMonth() == NECESSARY_DAY_OF_MONTH) {
                result.add(localDate);
            }
            localDate = localDate.plusDays(1);
        }

        return !result.isEmpty() ? result : null;
    }

    //Task2.2
    public LocalDate getNextFriday13th(LocalDate inputDate) {
        LocalDate localDate = inputDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (localDate.getDayOfMonth() != NECESSARY_DAY_OF_MONTH) {
            localDate = localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }

        return localDate;
    }

    //Task3
    public Optional<LocalDate> parseDate(String input) {
        List<DateTimeFormatter> dateTimeFormatterList = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-d"),
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d/MM/yy"),
            DateTimeFormatter.ofPattern("d/M/yy")
        );

        for (var formatter : dateTimeFormatterList) {
            try {
                LocalDate localDate = LocalDate.parse(input, formatter);
                return Optional.of(localDate);
            } catch (DateTimeParseException ignored) {
            }
        }

        LocalDate localDate = LocalDate.now();

        Pattern pattern = Pattern.compile("(\\d+)\\s+days?\\s+ago");
        Matcher matcher = pattern.matcher(input.toLowerCase());

        if (matcher.matches()) {
            int days = Integer.parseInt(matcher.group(1));
            return Optional.of(localDate.minusDays(days));
        }

        return switch (input.toLowerCase()) {
            case "tomorrow" -> Optional.of(localDate.plusDays(1));
            case "today" -> Optional.of(localDate);
            case "yesterday" -> Optional.of(localDate.minusDays(1));
            default -> Optional.empty();
        };
    }

    //Task4
    public Boolean checkPassword(String input) {
        Pattern pattern = Pattern.compile("[~!@#$%^&*|]");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    //Task5
    public Boolean isValidCarNumber(String input) {

        Pattern pattern = Pattern.compile("^[АВЕКМНОРСТУХ](\\d){3}[АВЕКМНОРСТУХ]{2}(\\d){3}$");
        Matcher matcher = pattern.matcher(input);

        return matcher.matches() && !matcher.group(1).startsWith("0");
    }

    //Task6
    public Boolean isSubLine(String line, String subLine) {

        Pattern pattern = Pattern.compile(".*" + subLine + ".*");
        Matcher matcher = pattern.matcher(line);

        return matcher.matches();
    }

    //Task7
    public Boolean isValidString(String input) {

        Pattern firstPattern = Pattern.compile("^[01]{2}0[01]*$");
        Matcher firstMatcher = firstPattern.matcher(input);

        Pattern secondPattern = Pattern.compile("^([01])[01]*\\1$");
        Matcher secondMatcher = secondPattern.matcher(input);

        Pattern thirdPattern = Pattern.compile("[01]{1,3}");
        Matcher thirdMatcher = thirdPattern.matcher(input);

        return firstMatcher.matches() | secondMatcher.matches() | thirdMatcher.matches();
    }

    //Task8
    public Boolean isValidStringBonus(String input) {

        //Для получения бонуса нужно решить хотя бы половину.

        //1. нечетной длины
        Pattern firstPattern = Pattern.compile("^[01]([01]{2})*$");
        Matcher firstMatcher = firstPattern.matcher(input);

        //2. начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
        Pattern secondPattern = Pattern.compile("^(0([01]{2})*)$|^(1([01]{2})*[01])$");
        Matcher secondMatcher = secondPattern.matcher(input);

        //3. количество 0 кратно 3
        Pattern thirdPattern = Pattern.compile("(1*01*){3}");
        Matcher thirdMatcher = thirdPattern.matcher(input);

        //4. любая строка, кроме 11 или 111
        Pattern fourthPattern = Pattern.compile("^(?!11$|111$)[01]*$");
        Matcher fourthMatcher = fourthPattern.matcher(input);

        //5. каждый нечетный символ равен 1
        Pattern fifthPattern = Pattern.compile("^1([01]1)*[01]?$");
        Matcher fifthMatcher = fifthPattern.matcher(input);

        //6. нет последовательных 1
        Pattern sixthPattern = Pattern.compile("^(?![01]*11)[01]*$");
        Matcher sixthMatcher = sixthPattern.matcher(input);

        return firstMatcher.matches()
            | secondMatcher.matches()
            | thirdMatcher.matches()
            | fourthMatcher.matches()
            | fifthMatcher.matches()
            | sixthMatcher.matches();
    }

}
