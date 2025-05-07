package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import org.springframework.stereotype.Service;

public interface ExcelEmailChecker {
    boolean isEmailInExcel(String emailToSearch, String excelFilePath);
}
