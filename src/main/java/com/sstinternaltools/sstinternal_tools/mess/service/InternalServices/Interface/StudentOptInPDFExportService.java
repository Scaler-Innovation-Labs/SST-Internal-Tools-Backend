package com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Interface;

import com.itextpdf.text.DocumentException;

import java.io.ByteArrayInputStream;

public interface StudentOptInPDFExportService {
    ByteArrayInputStream generateStudentOptInPDF() throws DocumentException;
}
