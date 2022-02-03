package CW.Controller;
import CW.Entity.Transaction;
import CW.Query.TransactionQuery;
import CW.Functions.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/upload")
public class UploadController {

    private final Parser csvParser;

    private final TransactionQuery transactionsRepository;

    @Autowired
    public UploadController(Parser csvParser, TransactionQuery transactionsRepository) {

        this.csvParser = csvParser;
        this.transactionsRepository = transactionsRepository;
    }

    @GetMapping()
    public String getStatsPage() {

        return "upload_page";
    }

    @PostMapping()
    public String uploadCsvFile(@RequestParam("transaction") MultipartFile transactions) {

        transactionsRepository.deleteAll();
        List<Transaction> transactionList = csvParser
                .parseDataFromCsvFileToEntityList(transactions, Transaction.class, ',');
        transactionsRepository.saveAll(transactionList);

        return "redirect:/stats";
    }

}
