package com.java.courses.tasks.task7;

import java.math.BigDecimal;

class Task7Exception extends Exception {
    public Task7Exception(String msg) {super(msg);}
    public Task7Exception() {}
}
public enum CalculationOfProfit {
    SMALL_INVESTMENT(0, 1000, 1.0), MEDIUM_INVESTMENT(1000, 10000, 1.5),
    HIGH_INVESTMENT(10000, 100000, 2.5), HUGE_INVESTMENT(100000, 1000000000, 5.0);

    private final int lowLimit;
    private final int highLimit;
    private final double percentage;

    CalculationOfProfit(int lowLimit, int highLimit, double percentage) {
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
        this.percentage = percentage;
    }

    static void calculateProfit(double sum) throws Task7Exception {
                if (sum <=0) {
            throw new Task7Exception("Ввведенное значение ниже нуля или равно нулю!\n " +
                    "Введите положительную сумму для расчета прибыли" );
        } else if(sum > 1000000000) {
            throw new Task7Exception("\nДля клиента банка:   Введенное значение превышает лимит, " +
                    "разрешенный банком для одной транзакции\n" +
                    "Максимально возможная сумма для одной транзакции - 1 000 000 000 фунтов \n\n" +
                    "Для программиста:   Максимальный предел определяется максимально\n" +
                    "допустимым положительным числом для формата double а также удобством\n" +
                    "так как все предыдущие лимиты кратны тысяче  и адекватной максимальной суммой вклада"
            );
        }
        //else if (? ) {
        //  throw new Task7Exception("Введенная сумма имеет более двух знаков после запятой, \n" +
        //            "для ввода значения с точностью до цента введите сумму в следующем формате: \n" +
        //          "ХХХ.YY , где YY - центы");}
        else {
            calculate(sum);
        }

    }

    static void calculate(double sum){
        BigDecimal bigDecimalSum = new BigDecimal(sum);
        BigDecimal profit = BigDecimal.ZERO;
        if (sum <=  CalculationOfProfit.SMALL_INVESTMENT.highLimit) {
            profit = bigDecimalSum.multiply(new BigDecimal(CalculationOfProfit.SMALL_INVESTMENT.percentage / 100));
            System.out.println("Profit of your investment for this year is " + profit.setScale(2, BigDecimal.ROUND_DOWN));
        } else if (sum > (double) CalculationOfProfit.MEDIUM_INVESTMENT.lowLimit && sum <= (double) CalculationOfProfit.MEDIUM_INVESTMENT.highLimit) {
            profit = bigDecimalSum.multiply(new BigDecimal(CalculationOfProfit.MEDIUM_INVESTMENT.percentage / 100));
            System.out.println("Profit of your investment for this year is " + profit.setScale(2, BigDecimal.ROUND_DOWN));
        } else if (sum > (double) CalculationOfProfit.HIGH_INVESTMENT.lowLimit && sum <= (double) CalculationOfProfit.HIGH_INVESTMENT.highLimit) {
            profit = bigDecimalSum.multiply(new BigDecimal(CalculationOfProfit.HIGH_INVESTMENT.percentage / 100));
            System.out.println("Profit of your investment for this year is " + profit.setScale(2, BigDecimal.ROUND_DOWN));
        } else if (sum > (double) CalculationOfProfit.HUGE_INVESTMENT.lowLimit && sum <= (double) CalculationOfProfit.HUGE_INVESTMENT.highLimit) {
            profit = bigDecimalSum.multiply(new BigDecimal(CalculationOfProfit.HUGE_INVESTMENT.percentage / 100));
            System.out.println("Profit of your investment for this year is " + profit.setScale(2, BigDecimal.ROUND_DOWN));
        }
    }

    public static void main(String[] args) {

        try {
            calculateProfit(1000);
        } catch(Task7Exception e) {
            System.err.print(e);
        }
    }
}
