package tk.iluyf.mc;

import static org.bukkit.ChatColor.*;

public class AnnoCompute {
    final static short commonYearMonthCount = 27;
    final static short commonMonthDayCount = 20;
    final static short cycleLeapYearCount = 10;
    final static short cycleGreaterMonthCount = 3;
    final static short yearCycle = 29;
    final static short monthCycle = 10;
    final static short yearCycleMonthCount = yearCycle * commonYearMonthCount + cycleLeapYearCount;
    final static short monthCycleDayCount = monthCycle * commonMonthDayCount + cycleGreaterMonthCount;
    short monthCycle_FirstdayDay[] = new short[monthCycle + 1];
    short yearCycle_FirstmonthMonth[] = new short[yearCycle + 1];

    public String output(long annoDay) {
        return annoToString(annoDay);
    }

    public AnnoCompute() {
        monthCycle_FirstdayDayCompute();
        yearCycle_FirstmonthMonthCompute();
    }

    private final void monthCycle_FirstdayDayCompute() {
        monthCycle_FirstdayDay[1] = 1;
        for (short monthCycle_Month_Circulate = 2; monthCycle_Month_Circulate <= monthCycle; ++monthCycle_Month_Circulate) {
            if (isCommonMonth(monthCycle_Month_Circulate - 1)) {
                monthCycle_FirstdayDay[monthCycle_Month_Circulate] = (short) (monthCycle_FirstdayDay[monthCycle_Month_Circulate
                        - 1] + commonMonthDayCount);
            } else {
                monthCycle_FirstdayDay[monthCycle_Month_Circulate] = (short) (monthCycle_FirstdayDay[monthCycle_Month_Circulate
                        - 1] + commonMonthDayCount + 1);
            }
        }
    }

    private final void yearCycle_FirstmonthMonthCompute() {
        yearCycle_FirstmonthMonth[1] = 1;
        for (short yearCycle_Year_Circulate = 2; yearCycle_Year_Circulate <= yearCycle; ++yearCycle_Year_Circulate) {
            if (isCommonYear(yearCycle_Year_Circulate - 1)) {
                yearCycle_FirstmonthMonth[yearCycle_Year_Circulate] = (short) (yearCycle_FirstmonthMonth[yearCycle_Year_Circulate
                        - 1] + commonYearMonthCount);
            } else {
                yearCycle_FirstmonthMonth[yearCycle_Year_Circulate] = (short) (yearCycle_FirstmonthMonth[yearCycle_Year_Circulate
                        - 1] + commonYearMonthCount + 1);
            }
        }
    }

    private final boolean isCommonYear(long year) {
        short netYear = (short) (year % yearCycle);
        if (netYear == 2 || netYear == 5 || netYear == 8 || netYear == 11 || netYear == 14 || netYear == 16
                || netYear == 19 || netYear == 22 || netYear == 25 || netYear == 28) {
            return false;
        } else {
            return true;
        }
    }

    private final boolean isCommonMonth(long month) {
        short netMonth = (short) (month % monthCycle);
        if (netMonth == 2 || netMonth == 5 || netMonth == 9) {
            return false;
        } else {
            return true;
        }
    }

    final long[] dayToMonth(long day) {
        long monthDayNumber[] = new long[2];
        long monthCycleCount = (day - 1) / monthCycleDayCount;
        short netDay = (short) ((day - 1) % monthCycleDayCount + 1);
        short monthCycle_MonthCount_Circulate = 1;
        while (netDay >= monthCycle_FirstdayDay[monthCycle_MonthCount_Circulate + 1]) {
            ++monthCycle_MonthCount_Circulate;
            if (monthCycle_MonthCount_Circulate >= monthCycle) {
                break;
            }
        }
        monthDayNumber[1] = netDay - monthCycle_FirstdayDay[monthCycle_MonthCount_Circulate] + 1;
        monthDayNumber[0] = monthCycleCount * monthCycle + monthCycle_MonthCount_Circulate;
        return monthDayNumber;
    }

    private final long[] monthToYear(long month) {
        long yearMonthNumber[] = new long[2];
        long yearCycleCount = (month - 1) / yearCycleMonthCount;
        short netMonth = (short) ((month - 1) % yearCycleMonthCount + 1);
        short yearCycle_YearCount_Circulate = 1;
        while (netMonth >= yearCycle_FirstmonthMonth[yearCycle_YearCount_Circulate + 1]) {
            ++yearCycle_YearCount_Circulate;
            if (yearCycle_YearCount_Circulate >= yearCycle) {
                break;
            }
        }
        yearMonthNumber[1] = netMonth - yearCycle_FirstmonthMonth[yearCycle_YearCount_Circulate] + 1;
        yearMonthNumber[0] = yearCycleCount * yearCycle + yearCycle_YearCount_Circulate;
        if (!isCommonYear(yearMonthNumber[0])) {
            yearMonthNumber[1] -= 1;
        }
        return yearMonthNumber;
    }

    private String annoToString(long annoDay) {
        long monthDay[] = new long[2];
        monthDay = dayToMonth(annoDay);
        short dayNumber = (short) monthDay[1];
        long yearMonth[] = new long[2];
        yearMonth = monthToYear(monthDay[0]);
        short monthNumber = (short) yearMonth[1];
        long yearNumber = yearMonth[0];
        if (yearNumber > 0 && monthNumber >= 0 && dayNumber > 0) {
            return BOLD + yearConvert(yearNumber) + "???" + RESET + monthConvert(monthNumber) + RESET
                    + dayConvert((short) dayNumber);
        } else {
            return "";
        }
    }

    public long[] annoToValue(long annoDay) {
        long monthDay[] = new long[2];
        monthDay = dayToMonth(annoDay);
        short dayNumber = (short) monthDay[1];
        long yearMonth[] = new long[2];
        yearMonth = monthToYear(monthDay[0]);
        short monthNumber = (short) yearMonth[1];
        long yearNumber = yearMonth[0];
        if (yearNumber > 0 && monthNumber >= 0 && dayNumber > 0) {
            long[] returnValue = { yearNumber, monthNumber, dayNumber };
            return returnValue;
        } else {
            long[] returnValue = { -1L, -1L, -1L };
            return returnValue;
        }
    }

    public static void main(String[] args) {
        AnnoCompute meow = new AnnoCompute();
        System.out.println(meow.annoToString(170425/* ????????????????????? */));
    }// ?????????

    private final String numberConvert(long number) {
        switch ((short) number) {
            case 0:
                return "???";
            case 1:
                return "???";
            case 2:
                return "???";
            case 3:
                return "???";
            case 4:
                return "???";
            case 5:
                return "???";
            case 6:
                return "???";
            case 7:
                return "???";
            case 8:
                return "???";
            case 9:
                return "???";
            default:
                return "";
        }
    }

    private final String yearConvert(long yearNumber) {
        short yearLength = (short) Long.toString(yearNumber).length();
        String returnValue = "";
        String yearConvertMemory[][] = new String[yearLength][2];
        for (long yearNumber_ = yearNumber; yearNumber_ > 0; yearNumber_ /= 10) {
            short Circulate = 0;// 0???????????????1???????????????????????????
            yearConvertMemory[Circulate][0] = String.valueOf(yearNumber_ % 10);
            yearConvertMemory[Circulate][1] = numberConvert(Long.valueOf(yearConvertMemory[Circulate][0]));
            returnValue = yearConvertMemory[Circulate][1] + returnValue;
        }
        if (yearNumber == 1L) {
            return "??????????????????";
        }
        return "???????????????" + returnValue;
    }

    private final String monthConvert(short monthNumber) {
        switch (monthNumber) {
            case 0:
                return AQUA + "??????";
            case 1:
                return AQUA + "??????";
            case 2:
                return AQUA + "??????";
            case 3:
                return AQUA + "??????";
            case 4:
                return AQUA + "??????";
            case 5:
                return AQUA + "??????";
            case 6:
                return AQUA + "??????";
            case 7:
                return GREEN + "??????";
            case 8:
                return GREEN + "??????";
            case 9:
                return GREEN + "??????";
            case 10:
                return GREEN + "??????";
            case 11:
                return GREEN + "??????";
            case 12:
                return GREEN + "??????";
            case 13:
                return GREEN + "??????";
            case 14:
                return RED + "??????";
            case 15:
                return RED + "??????";
            case 16:
                return RED + "??????";
            case 17:
                return RED + "??????";
            case 18:
                return RED + "??????";
            case 19:
                return RED + "??????";
            case 20:
                return RED + "??????";
            case 21:
                return GOLD + "??????";
            case 22:
                return GOLD + "??????";
            case 23:
                return GOLD + "??????";
            case 24:
                return GOLD + "??????";
            case 25:
                return GOLD + "??????";
            case 26:
                return GOLD + "??????";
            case 27:
                return GOLD + "??????";
            default:
                return "";
        }
    }

    private final String dayConvert(short dayNumber) {
        String dayConvertMemory[][] = new String[2][2];
        dayConvertMemory[1][0] = String.valueOf(dayNumber / 10);
        dayConvertMemory[0][0] = String.valueOf(dayNumber % 10);
        switch (dayConvertMemory[1][0]) {
            case "0":
                dayConvertMemory[1][1] = "???";
                break;
            case "1":
                dayConvertMemory[1][1] = "???";
                break;
            case "2":
                dayConvertMemory[1][1] = "???";
                break;
            default:
                dayConvertMemory[1][1] = "";
        }
        dayConvertMemory[0][1] = numberConvert(Integer.parseInt(dayConvertMemory[0][0].toString()));
        switch (dayNumber) {
            case 10:
                return "??????";
            case 20:
                return "??????";
            default:
                return dayConvertMemory[1][1] + dayConvertMemory[0][1];
        }
    }
}