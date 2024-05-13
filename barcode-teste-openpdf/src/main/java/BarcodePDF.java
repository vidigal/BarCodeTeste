import com.lowagie.text.*;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;

public class BarcodePDF {

    // 60mm convertidos em UserUnit
    public static final float widthUserUnit = 60f * 72f / 25.4f;
    // 30mm convertidos em UserUnit
    public static final float heightUserUnit = 30f * 72f / 25.4f;

    public static final Rectangle PAGE_SIZE_MINHA_ETIQUETA = new RectangleReadOnly(widthUserUnit,heightUserUnit);

    public void gerarPDF(String[] codigos, String diretorio) throws FileNotFoundException {
        Document document = new Document(PAGE_SIZE_MINHA_ETIQUETA, 0F, 0F, 5F, 0F);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(diretorio));
        document.open();
        PdfContentByte cb = writer.getDirectContent();

        for (String codigo: codigos) {
            Image barcodeImg = gerarCodigoBarras(codigo, cb);
            barcodeImg.scaleToFit(widthUserUnit - 10, heightUserUnit - 10);
            document.add(barcodeImg);
            Paragraph p = new Paragraph("Nome do paciente");
            p.setAlignment(Element.ALIGN_CENTER);
            p.setAlignment(Element.ALIGN_MIDDLE);
            document.add(p);
            document.newPage();
        }

        document.close();
    }

    public Image gerarCodigoBarras(String codigo, PdfContentByte cb) {
        Barcode128 barcode128 = new Barcode128();
        barcode128.setCode(codigo);
        barcode128.setSize(8);
        Image barcodeImage = barcode128.createImageWithBarcode(cb, null, null);
        barcodeImage.scalePercent(200);
        barcodeImage.setAlignment(Element.ALIGN_CENTER);
        barcodeImage.setAlignment(Element.ALIGN_MIDDLE);

        return barcodeImage;
    }

}
