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
public class MaxController {

    private final TransactionQuery transactionsRepository;
    private final Writer csvWriter;
    private final Path maxFilePath = Paths.get("src\\main\\resources\\csvfiles\\max.csv");

    @Autowired
    public MaxController(TransactionQuery transactionsRepository, Writer csvWriter) {

        this.transactionsRepository = transactionsRepository;
        this.csvWriter = csvWriter;
    }

    @GetMapping("/max")
    public ModelAndView getMaxPage() throws Exception {

        ModelAndView modelAndView = new ModelAndView("max_page");
        List<Statistic> stats;
        stats = Statistic.getStatisticListFromViewList(transactionsRepository.getAllTransactionsGroupingById());
        stats.forEach(
                x->System.out.printf(x.getAmount().toString())
        );
        modelAndView.addObject("max", stats);

        csvWriter.writeDataFromEntityListToCsvFile(stats, maxFilePath, ',');

        return modelAndView;
    }
}
