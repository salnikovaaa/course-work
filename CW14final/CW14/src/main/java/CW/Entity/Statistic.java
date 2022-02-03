package CW.Entity;

import CW.Data.TransactionTableDto;
import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;
import java.util.List;

public class Statistic implements StatisticView {

    @CsvBindByName(column = "amount")
    private Double Amount;

    @CsvBindByName(column = "customer_id")
    private Long customerId;

    private Long maxCount;


    public Statistic() {

    }

    public void setAmount(Double amount) {

        Amount = amount;
    }

    public Long getCustomerId() {

        return customerId;
    }

    public Long getMaxCount() {

        return maxCount;
    }

    public void setMaxCount(Long maxCount) {

        this.maxCount = maxCount;
    }


    public void setCustomerId(Long customerId) {

        this.customerId = customerId;
    }

    public Statistic(Double amount, Long customerId) {

        this.Amount = amount;
        this.customerId = customerId;
    }

    public Statistic(Double amount, Long customerId, Long maxCount) {

        this.Amount = amount;
        this.customerId = customerId;
        this.maxCount = maxCount;
    }

    @Override
    public Double getAmount() {

        return Amount;
    }


    public static List<Statistic> getStatisticListFromViewList(List<TransactionTableDto> statisticViewList) {

        List<Statistic> statisticList = new ArrayList<Statistic>();

        statisticViewList.forEach(x -> statisticList.add(
                new Statistic(
                        x.getAmount(),
                        x.getCustomerId(),
                        x.getMaxCount())
                )
        );
        return statisticList;
    }
}
