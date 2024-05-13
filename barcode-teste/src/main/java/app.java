import java.io.File;
import java.io.FileNotFoundException;

public class app {

    public static final String DEST = "P:\\prontlife\\BarCodeTeste\\OUTPUT\\etiquetas.pdf";

    public static void main(String[] args) {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        try {
            LabelGenerator lg = new LabelGenerator();
            lg.gerarPDF(DEST);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

//        try {
//            LabelGenerator.gerarPDFAsTable(DEST);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

}
