package com.rahulsharmadev;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

/**
 * Simple JasperReports Banking Transaction Demo
 */
public class App {

    public static void main(String[] args) {
        try {
            System.out.println("Starting JasperReports Demo...");

            // Load JRXML template from resources
            InputStream jrxmlStream = App.class.getResourceAsStream("/banking_transactions.jrxml");
            if (jrxmlStream == null) {
                throw new RuntimeException("JRXML template not found in resources!");
            }

            // Compile the template
            System.out.println("Compiling JRXML template...");
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

            // Create sample transaction data
            List<Map<String, Object>> transactions = createSampleTransactions();

            // Create data source from the transaction list
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transactions);

            // Set parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("accountNumber", "1234567890");
            parameters.put("accountHolder", "Rahul Sharma");
            parameters.put("reportDate", LocalDate.now().toString());

            // Fill the report with data
            System.out.println("Filling report with data...");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export to PDF
            String outputFile = "banking_transactions_report.pdf";
            System.out.println("Exporting to PDF: " + outputFile);
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFile);

            System.out.println("✅ PDF Report generated successfully: " + outputFile);
            System.out.println("📄 Total transactions: " + transactions.size());

        } catch (Exception ex) {
            System.err.println("❌ Error generating report: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Creates sample banking transaction data
     */
    private static List<Map<String, Object>> createSampleTransactions() {
        List<Map<String, Object>> transactions = new ArrayList<>();
        double balance = 50000.00;

        // Transaction 1: Salary Credit
        balance += 75000.00;
        transactions.add(createTransaction("TXN001", "2024-12-01", "Salary Credit - December",
                "CREDIT", 75000.00, balance));

        // Transaction 2: ATM Withdrawal
        balance -= 5000.00;
        transactions.add(createTransaction("TXN002", "2024-12-02", "ATM Withdrawal - Main Street",
                "DEBIT", 5000.00, balance));

        // Transaction 3: Online Shopping
        balance -= 3499.00;
        transactions.add(createTransaction("TXN003", "2024-12-05", "Amazon.in - Electronics",
                "DEBIT", 3499.00, balance));

        // Transaction 4: Bill Payment
        balance -= 1200.00;
        transactions.add(createTransaction("TXN004", "2024-12-07", "Electricity Bill Payment",
                "DEBIT", 1200.00, balance));

        // Transaction 5: Refund Credit
        balance += 500.00;
        transactions.add(createTransaction("TXN005", "2024-12-08", "Refund - Cancelled Order",
                "CREDIT", 500.00, balance));

        // Transaction 6: Restaurant Payment
        balance -= 2850.00;
        transactions.add(createTransaction("TXN006", "2024-12-10", "Restaurant - Family Dinner",
                "DEBIT", 2850.00, balance));

        // Transaction 7: Fuel Payment
        balance -= 4500.00;
        transactions.add(createTransaction("TXN007", "2024-12-12", "Petrol Pump - Shell",
                "DEBIT", 4500.00, balance));

        // Transaction 8: Interest Credit
        balance += 450.00;
        transactions.add(createTransaction("TXN008", "2024-12-13", "Savings Account Interest",
                "CREDIT", 450.00, balance));

        // Transaction 9: UPI Transfer Out
        balance -= 10000.00;
        transactions.add(createTransaction("TXN009", "2024-12-14", "UPI Transfer to Friend",
                "DEBIT", 10000.00, balance));

        // Transaction 10: Mobile Recharge
        balance -= 599.00;
        transactions.add(createTransaction("TXN010", "2024-12-15", "Mobile Recharge - Prepaid",
                "DEBIT", 599.00, balance));

        return transactions;
    }

    /**
     * Helper method to create a transaction map
     */
    private static Map<String, Object> createTransaction(String id, String date, String description,
            String type, double amount, double balance) {
        Map<String, Object> transaction = new HashMap<>();
        transaction.put("transactionId", id);
        transaction.put("date", date);
        transaction.put("description", description);
        transaction.put("type", type);
        transaction.put("amount", amount);
        transaction.put("balance", balance);
        return transaction;
    }
}
