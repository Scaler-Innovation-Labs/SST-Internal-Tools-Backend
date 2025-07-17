package com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Interface;

import com.itextpdf.text.DocumentException;
import com.sstinternaltools.sstinternal_tools.mess.entity.Hostel;

import java.io.ByteArrayInputStream;

public interface StudentOptInPDFExportService {
    ByteArrayInputStream generateStudentOptInPDF(Hostel hostel) throws DocumentException;
}
