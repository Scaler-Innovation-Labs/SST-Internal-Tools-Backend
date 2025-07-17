package com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Implementation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.sstinternaltools.sstinternal_tools.mess.entity.Hostel;
import com.sstinternaltools.sstinternal_tools.mess.entity.MealType;
import com.sstinternaltools.sstinternal_tools.mess.entity.VendorPlanSelection;
import com.sstinternaltools.sstinternal_tools.mess.repository.VendorPlanSelectionRepository;
import com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Interface.StudentOptInPDFExportService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.List;

@Service
public class StudentOptInPDFExportServiceImpl implements StudentOptInPDFExportService {

    private final VendorPlanSelectionRepository vendorPlanSelectionRepository;

    public StudentOptInPDFExportServiceImpl(VendorPlanSelectionRepository vendorPlanSelectionRepository) {
        this.vendorPlanSelectionRepository = vendorPlanSelectionRepository;
    }

    @Override
    public ByteArrayInputStream generateStudentOptInPDF(Hostel hostel) throws DocumentException {
        List<VendorPlanSelection> selections = vendorPlanSelectionRepository.findAllWithDetails();

        Map<String, List<VendorPlanSelection>> groupedByVendor = new HashMap<>();
        for (VendorPlanSelection selection : selections) {
            if (!selection.getHostel().equals(hostel)) continue; // Filter by selected hostel

            String vendorName = selection.getPlan().getVendor().getVendorName();
            if (!groupedByVendor.containsKey(vendorName)) {
                List<VendorPlanSelection> newList = new ArrayList<>();
                newList.add(selection);
                groupedByVendor.put(vendorName, newList);
            }
            else {
                groupedByVendor.get(vendorName).add(selection);
            }
        }

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        document.add(new Paragraph("" + hostel, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18))); // Hostel name outside
        document.add(Chunk.NEWLINE);

        for (String vendor : groupedByVendor.keySet()) {

            Map<String, String[]> studentMealMap = new TreeMap<>();

            // Make a mapping of all the meals the student has chosen for this vendor
            for (VendorPlanSelection selection : groupedByVendor.get(vendor)) {
                String studentName = selection.getUser().getUsername();
                int roomNo = selection.getRoomNumber();
                String studentHostel = selection.getHostel().toString(); // Needed for uniqueness

                String key = studentName + "|" + roomNo + "|" + studentHostel;

                studentMealMap.putIfAbsent(key, new String[]{studentName, String.valueOf(roomNo), "No", "No", "No"});
                for (MealType meal : selection.getPlan().getMealTypes()) {
                    studentMealMap.get(key)[meal.ordinal() + 2] = "Yes";
                }
            }

            // Add headings and fill in the cells
            document.add(new Paragraph("Vendor: " + vendor, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15))); // Vendor name outside each table
            document.add(Chunk.NEWLINE); // Adds a newline

            PdfPTable table = new PdfPTable(5);
            List<String> headers = List.of("Student Name", "Room No", "Breakfast", "Lunch", "Dinner");
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell();
                headerCell.setBackgroundColor(BaseColor.BLACK); // Sets the background color to the cell
                headerCell.setPhrase(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.WHITE))); // Sets the phrase (the header) inside the header cell
                table.addCell(headerCell); // adds the first row i.e. the header cells to the table
            }

            for (String[] values : studentMealMap.values()) {
                table.addCell(values[0]); // Name
                table.addCell(values[1]); // Room No
                table.addCell(values[2]); // Breakfast
                table.addCell(values[3]); // Lunch
                table.addCell(values[4]); // Dinner
            }

            document.add(table);
            document.add(Chunk.NEWLINE);
            document.add(new LineSeparator());
            document.add(Chunk.NEWLINE);
        }

        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
