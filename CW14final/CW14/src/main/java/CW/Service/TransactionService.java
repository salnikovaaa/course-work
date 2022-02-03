package CW.Service;

import CW.Data.TransactionData;
import CW.Data.TransactionTableDto;
import CW.Entity.Transaction;
import CW.Query.TransactionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class TransactionService {
    private final TransactionQuery transactionQuery;


    @Autowired
    public TransactionService(TransactionQuery transactionQuery) {

        this.transactionQuery = transactionQuery;
    }

    @Transactional
    public void saveAllTransactions(List<TransactionData> transactionDtoList) {

        transactionQuery.truncateTable();
        List<Transaction> transactionList = transactionDtoList
                .stream()
                .map(this::mapToTransactionFromDto)
                .collect(Collectors.toList());
        transactionQuery.saveAll(transactionList);
    }

    @Transactional
    public List<TransactionTableDto> getAllTransactionsGroupingByDate() {

        return transactionQuery.getAllTransactionsGroupingById();
    }

    private TransactionData getTransactionDto(Transaction x) {

        TransactionData transactionDto = new TransactionData();
        transactionDto.setAmount(x.getAmount());
        transactionDto.setCustomerId(x.getCustomerId());
        return transactionDto;
    }

    private Transaction mapToTransactionFromDto(TransactionData transactionDto) {

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCustomerId(transactionDto.getCustomerId());
        return transaction;
    }


}
