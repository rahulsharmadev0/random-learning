package com.rahulsharmadev;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.*;

/**
 * Simple JasperReports Example for Beginners
 */
public class SimpleApp {

    public static void main(String[] args) {
        try {
            // Step 1: Load the JRXML template
            InputStream template = SimpleApp.class.getResourceAsStream("/simple_transactions.jrxml");

            // Step 2: Compile JRXML to JasperReport
            JasperReport report = JasperCompileManager.compileReport(template);

            // Step 3: Create sample data - just 3 transactions
            List<Map<String, Object>> data = List.of(
                    createTransaction("2024-12-01", "Salary Credit", 50000.00),
                    createTransaction("2024-12-05", "ATM Withdrawal", 5000.00),
                    createTransaction("2024-12-10", "Online Shopping", 2500.00));

            // Step 4: Create data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

            // Step 5: Set parameters
            Map<String, Object> parameters = Map.of("accountName", "Rahul Sharma");

            // Step 6: Fill report with data
            JasperPrint filledReport = JasperFillManager.fillReport(report, parameters, dataSource);

            // Step 7: Export to PDF
            JasperExportManager.exportReportToPdfFile(filledReport, "simple_report.pdf");

            System.out.println("✅ PDF created: simple_report.pdf");

        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to create transaction data
    private static Map<String, Object> createTransaction(String date, String desc, double amount) {
        return Map.of("date", date, "description", desc, "amount", amount);
    }
}
