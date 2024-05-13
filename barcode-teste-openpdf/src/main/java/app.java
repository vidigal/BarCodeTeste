import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class app {

    private static final String diretorio = "P:\\prontlife\\BarCodeTeste\\OUTPUT\\etiquetas.pdf";

    public static String[] codigos = {
            "123456789546",
            "123412389546",
            "123456789657",
            "123456789786",
            "655478952134",
            "124589654785",
            "125478935443",
            "123454789345",
            "121564578565",
            "456787651894"
    };

    public static void main(String[] args) {
        BarcodePDF barcodePDF = new BarcodePDF();
        try {
            barcodePDF.gerarPDF(codigos, diretorio);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
