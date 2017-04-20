import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class serves for analyzing similarity between two .TXT documents
 */
public class DocumentAnalyzer {
    private HashMap<String, Integer> wordCounter1;
    private HashMap<String, Integer> wordCounter2;

    /**
     * Read whole document
     *
     * @param path Path to specific document
     * @return {@link HashMap} where word is Key({@link String}) and word frequency is Value({@link Integer})
     *
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    private HashMap<String, Integer> countWordFrequency(String path) {
        HashMap<String, Integer> wordCount = new HashMap<>();

        try {
            FileInputStream fileInputStream= new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader documentReader = new BufferedReader(inputStreamReader);

            String line = documentReader.readLine();

            while(line != null) {
                String[] words = line.split(" ");

                for(String word : words ) {
                    word = word.toLowerCase();

                    if(wordCount.containsKey(word)) {
                        int counter = wordCount.get(word);
                        counter++;
                        wordCount.put(word, counter);
                    } else
                        wordCount.put(word, 1);
                }

                line = documentReader.readLine();
            }

            fileInputStream.close();
            inputStreamReader.close();
            documentReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return wordCount;
    }

    private int computeInnerProduct(Map<String, Integer> wordCount1, Map<String, Integer> wordCount2) {
        int sum = 0;

        for(Map.Entry<String, Integer> entry : wordCount1.entrySet()) {
            if(wordCount2.containsKey(entry.getKey()))
                sum += wordCount1.get(entry.getKey()) * wordCount2.get(entry.getKey());
        }

        return sum;
    }

    /**
     * Analyze two documents and get how similar they are.
     * @param pathToDoc1 Path to Document1
     * @param pathToDoc2 Path to Document2
     * @return Values between 0 and 100. Lower value means two documents are less similar,
     *         while higher values means two documents are more similar
     */
    public double analyze(String pathToDoc1, String pathToDoc2) {
        wordCounter1 = countWordFrequency(pathToDoc1);
        wordCounter2 = countWordFrequency(pathToDoc2);

        int numeration = computeInnerProduct(wordCounter1, wordCounter2);
        double denominator = Math.sqrt(computeInnerProduct(wordCounter1, wordCounter1) * computeInnerProduct(wordCounter2, wordCounter2));
        double documentDistance = (Math.acos(numeration / denominator) / 1.5707963267949) * 100; // If documents are completely different the distance will be the maximum distance which is 1.5707963267949.

        return Math.abs(Math.round(documentDistance-100));
    }

    /**
     * Get {@link HashMap} of previously analyzed document1 - initialized in {@link DocumentAnalyzer#analyze(String, String)},
     * where key represents Words and values represent number of Occurrences
     * @return {@link HashMap}
     */
    public HashMap<String, Integer> getWordsAndCountsFormDoc1() {
        return wordCounter1;
    }

    /**
     * Get {@link HashMap} of previously analyzed document2 - initialized in {@link DocumentAnalyzer#analyze(String, String)},
     * where key represents Words and values represent number of Occurrences
     * @return {@link HashMap}
     */
    public HashMap<String, Integer> getWordsAndCountsFormDoc2() {
        return wordCounter2;
    }
}
