package edu.uniformix.api.services;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.UnitValue;
import edu.uniformix.api.exceptions.ResourceNotFoundException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ProtocolPdfService {

    public byte[] generateTransactionPDF(String employeeName, String protocolNumber, String uniformName, int quantity, String unitState, String unitCity) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        String responsibilityText = "Eu, " + UtilsService.capitalizeWords(employeeName) + ", DECLARO que estou recebendo meu uniforme " +
                "para utilização no desempenho das minhas funções, ciente das normas internas e de como se dá o seu devido uso. " +
                "Estou ciente também de que, em caso de dano ou extravio, pagarei o valor proporcional correspondete ao custo " +
                "de outra aquisição e assumo o compromisso de zelar pelo bom uso destes, bem como deverei devolver o fardamento em caso " +
                "de desligamento ou quando solicitado pela empresa.\n\n" +
                "Fardamento adquirido: " + uniformName + " - " + quantity + " unidade(s).\n";

        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.setMargins(45, 70, 60, 70);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
            PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);

            try (InputStream is = getClass().getClassLoader().getResourceAsStream("static/generic_logo.png")) {
                if (is == null) {
                    throw new ResourceNotFoundException("Resource not found: static/generic_logo.png");
                }

                ImageData imageData = ImageDataFactory.create(is.readAllBytes());
                Image image = new Image(imageData);

                image.setWidth(UnitValue.createPercentValue(20));
                image.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                image.setWidth(100);
                image.setMarginBottom(40);

                document.add(image);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }

            Paragraph title = new Paragraph("PROTOCOLO DE FARDAMENTO")
                    .setFont(boldFont)
                    .setUnderline()
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(40);

            Paragraph protocolParagraph = new Paragraph("Número de protocolo: " + protocolNumber)
                    .setFont(font)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginBottom(100);

            Paragraph termsParagraph = new Paragraph(responsibilityText)
                    .setFont(font)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.JUSTIFIED)
                    .setMarginBottom(60);

            Paragraph signature = new Paragraph(
                    "Assinatura: ______________________________________________________________\n" +
                            unitCity + ", " + unitState + ", \n" + UtilsService.dateFormatter(Timestamp.valueOf(LocalDateTime.now())) + ".\n"
            ).setFont(font)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.LEFT);

            document.add(title);
            document.add(protocolParagraph);
            document.add(termsParagraph);
            document.add(signature);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
}
