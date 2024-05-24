package ca.ubc.cs304.util;

import javax.swing.*;

public class InputValidationUtil {


    public static boolean isValidInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Input cannot be empty.", "Input Validation Error", JOptionPane.WARNING_MESSAGE);

            return false;
        }

        // Patterns that might indicate SQL injection attempts
        String[] dangerousPatterns = {
                "'", "\"", ";", "--", "/*", "*/", "@@", "@", "char", "nchar", "varchar", "nvarchar",
                "alter", "begin", "cast", "create", "cursor", "declare", "delete", "drop", "end", "exec", "execute",
                "fetch", "insert", "kill", "select", "sys", "sysobjects", "syscolumns", "table", "update"
        };

        String inputLower = input.toLowerCase(); // Consider case-insensitive matching

        for (String pattern : dangerousPatterns) {
            if (inputLower.contains(pattern)) {
                JOptionPane.showMessageDialog(null, "Input contains invalid characters or patterns. Please correct your input.", "Invalid Input", JOptionPane.ERROR_MESSAGE);

                return false; // Found a dangerous pattern
            }
        }

        return true; // Input passed all checks
    }
}
