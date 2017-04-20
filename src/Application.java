

/**
 * Created by Marko on 20.04.2017..
 */
public class Application {
    public static void main(String[] args) {
        DocumentAnalyzer documentAnalyzer = new DocumentAnalyzer();
        double docDistance = documentAnalyzer.analyze("C:\\Users\\Marko\\Desktop\\Files\\tekst.txt", "C:\\Users\\Marko\\Desktop\\Files\\test.txt");
        System.out.println("Document distance: " + docDistance);
        System.out.println("=================");
    }
}
