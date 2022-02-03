package CW.Controller;

import CW.Entity.Statistic;
import CW.Query.TransactionQuery;
import CW.Functions.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class StatsController {

    private final TransactionQuery transactionsRepository;
    private final Writer csvWriter;
    private final Path statsFilePath = Paths.get("src\\main\\resources\\csvfiles\\stats.csv");

    @Autowired
    public StatsController(TransactionQuery transactionsRepository, Writer csvWriter) {

        this.transactionsRepository = transactionsRepository;
        this.csvWriter = csvWriter;
    }

    @GetMapping("/stats")
    public ModelAndView getStatsPage() throws Exception {

        ModelAndView modelAndView = new ModelAndView("stats_page");
        List<Statistic> stats = Statistic.getStatisticListFromViewList(
                transactionsRepository.getAllTransactionsMaxCountGroupingById()
        );
        stats.forEach(
                x -> System.out.println(x.getAmount().toString())
        );
        modelAndView.addObject("stats", stats);

        csvWriter.writeDataFromEntityListToCsvFile(stats, statsFilePath, ',');

        return modelAndView;
    }
}
