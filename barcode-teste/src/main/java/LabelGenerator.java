import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.FileNotFoundException;

public class LabelGenerator {

    // Parâmetros configuráveis
    //public float convertUUtoMM = 2.834645669F; // Um MM = 2.834645669 User Unit
    //public float convertUUtoMM = 72.0F; // 1 inche = 72 user unit


    public float pageSizeWidth = 200F;
    public float pageSizeHeight = 100F;

    public final PDFContent[] pdfContents = {
            new PDFContent("João da Silva", "125478546321"),
            new PDFContent("Maria Mariana", "548754623554"),
            new PDFContent("José Carvalho", "547854621458"),
            new PDFContent("Aparecida dos Santos", "547896542315"),
            new PDFContent("Ingredi Lacerda", "231654879874"),
            new PDFContent("Natália Christina", "564654564456"),
            new PDFContent("Rosana Maria de Souza", "546545645468"),
            new PDFContent("Talita Tavares", "789543287253"),
            new PDFContent("Edmilson dos Santos", "546489732184")
    };

    public void gerarPDF(String dest) throws FileNotFoundException {

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        pdfDoc.setDefaultPageSize(new PageSize(pageSizeWidth, pageSizeHeight));
        Document doc = new Document(pdfDoc);


        for (PDFContent pdfContent : pdfContents) {
            pdfDoc.addNewPage();
            pdfDoc.getLastPage();

            Image img = createBarcode(pdfContent.getCodigoBarras(), pdfDoc, 1, 1);
            doc.add(img);
            //doc.showTextAligned(pdfContent.getNomePaciente(), 150, 70, TextAlignment.CENTER);
        }
        doc.close();
    }

    public Image createBarcode(String codigo, PdfDocument pdfDoc, float xScale, float yScale) {
        Barcode128 barcode128 = new Barcode128(pdfDoc);
        barcode128.setBaseline(8);
        barcode128.setSize(8); // Tamanho da fonte
        barcode128.setCode(codigo);
        barcode128.setCodeType(Barcode128.CODE128);
        Image barcode128Image = new Image(barcode128.createFormXObject(pdfDoc));
        barcode128Image.setHorizontalAlignment(HorizontalAlignment.CENTER);

        //barcode128Image.scaleToFit(200F, 100F);
        return barcode128Image;
    }

    private class PDFContent {
        private String nomePaciente;
        private String codigoBarras;

        public PDFContent(String nomePaciente, String codigoBarras) {
            this.nomePaciente = nomePaciente;
            this.codigoBarras = codigoBarras;
        }

        public String getNomePaciente() {
            return nomePaciente;
        }

        public void setNomePaciente(String nomePaciente) {
            this.nomePaciente = nomePaciente;
        }

        public String getCodigoBarras() {
            return codigoBarras;
        }

        public void setCodigoBarras(String codigoBarras) {
            this.codigoBarras = codigoBarras;
        }
    }

}

 /*   // Desenha uma tabela no PDF e coloca os códigos de barras nas celulas
    public static void gerarPDFAsTable(String dest) throws FileNotFoundException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();


        for (int i = 0; i < 12; i++) {
            table.addCell(createBarcodeAsCell(String.format("%08d", i), pdfDoc));
        }

        doc.add(table);

        doc.close();
    }*/

/*    private static PdfFormXObject createBarcode(String code, PdfDocument pdfDoc) {
        BarcodeEAN barcode = new BarcodeEAN(pdfDoc);
        barcode.setCodeType(BarcodeEAN.EAN8);
        barcode.setCode(code);

        // Create barcode object to put it to the cell as image
        PdfFormXObject barcodeObject = barcode.createFormXObject(null, null, pdfDoc);
        return barcodeObject;
    }
}*/

   /* private static Cell createBarcodeAsCell(String code, PdfDocument pdfDoc) {
        BarcodeEAN barcode = new BarcodeEAN(pdfDoc);
        barcode.setCodeType(BarcodeEAN.EAN8);
        barcode.setCode(code);

        // Create barcode object to put it to the cell as image
        PdfFormXObject barcodeObject = barcode.createFormXObject(null, null, pdfDoc);
        Cell cell = new Cell().add(new Image(barcodeObject));
        cell.setPaddingTop(10);
        cell.setPaddingRight(10);
        cell.setPaddingBottom(10);
        cell.setPaddingLeft(10);

        return cell;
    }

}*/
//        doc.add(new Paragraph(String.format("This barcode measures %s by %s user units",
//                img.getImageScaledWidth(), img.getImageScaledHeight())));

//        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
//        Document doc = new Document(pdfDoc);
//
//        PdfFormXObject barcode = createBarcode("12345678", pdfDoc);
//        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
//
//        canvas.addXObjectAt(barcode, 36, 750);
//
//        doc.close();