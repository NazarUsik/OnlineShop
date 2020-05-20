package co.proarena.usik.utils;


import co.proarena.usik.entity.Product;
import co.proarena.usik.entity.SalesOrder;
import co.proarena.usik.entity.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class ReceiptUtils {

    public static final Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 58);
    public static final Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 24);
    public static final Font textFont = new Font(Font.FontFamily.TIMES_ROMAN, 14);


    public static void createReceiptPdf(String fileName, User user, Product product, SalesOrder order)
            throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        addTitle(document, user);
        addContent(document, product, order);
        document.close();
    }

    private static void addTitle(Document document, User user) throws DocumentException {
        Paragraph title = new Paragraph();
        title.add(new Paragraph("Sales receipt", titleFont));
        addEmptyLine(title, 1);
        title.add(new Paragraph("Date: " + LocalDate.now()));
        title.add(new Paragraph("Customer: " + user.getFirstName() + " " + user.getLastName()));
        addEmptyLine(title, 2);
        document.add(title);
    }

    private static void addContent(Document document, Product product, SalesOrder order)
            throws DocumentException, IOException {
        Paragraph content = new Paragraph();
        createOrderTable(content, order);
        addEmptyLine(content, 2);
        createProductTable(content, product);
        addEmptyLine(content, 2);
        addImage(content, BarcodeUtils.generateEAN13BarcodeImage(BarcodeUtils.getEAN13Number()));
        document.add(content);
    }

    private static void createOrderTable(Paragraph paragraph, SalesOrder order) {
        paragraph.add(new Paragraph("Sale order", headerFont));
        addEmptyLine(paragraph, 1);

        PdfPTable table = new PdfPTable(6);

        PdfPCell tableHeader = new PdfPCell(new Phrase("ID"));
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(tableHeader);

        tableHeader = new PdfPCell(new Phrase("Date"));
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(tableHeader);

        tableHeader = new PdfPCell(new Phrase("Product ID"));
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(tableHeader);

        tableHeader = new PdfPCell(new Phrase("Price"));
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(tableHeader);

        tableHeader = new PdfPCell(new Phrase("Quantity"));
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(tableHeader);

        tableHeader = new PdfPCell(new Phrase("Total price"));
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(tableHeader);
        table.setHeaderRows(1);

        table.addCell(String.valueOf(order.getId()));
        table.addCell(String.valueOf(order.getDate()));
        table.addCell(String.valueOf(order.getProductId()));
        table.addCell(String.valueOf(order.getPrice()));
        table.addCell(String.valueOf(order.getQuantity()));
        table.addCell(String.valueOf(order.getTotal()));

        paragraph.add(table);
    }

    private static void createProductTable(Paragraph paragraph, Product product) {
        paragraph.add(new Paragraph("Product", headerFont));
        addEmptyLine(paragraph, 1);

        PdfPTable table = new PdfPTable(3);

        PdfPCell tableHeader = new PdfPCell(new Phrase("ID"));
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(tableHeader);

        tableHeader = new PdfPCell(new Phrase("Name"));
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(tableHeader);

        tableHeader = new PdfPCell(new Phrase("Price"));
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(tableHeader);
        table.setHeaderRows(1);

        table.addCell(String.valueOf(product.getId()));
        table.addCell(product.getName());
        table.addCell(String.valueOf(product.getPrice()));

        paragraph.add(table);
    }

    private static void addImage(Paragraph paragraph, BufferedImage image) throws IOException, DocumentException {
        File file = new File("image.png");
        ImageIO.write(image, "png", file);
        Image pdfImage = Image.getInstance(Utilities.toURL(file.toString()));
        paragraph.add(pdfImage);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
