import Shannon.Coding;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        SourceText sourceText = new SourceText("text.txt");
        Coding coding = new Coding(sourceText.getProbabilityArrayList());
        sourceText.writeFile("Char\t\t\t|Probability\t|P(i)\t\t|Q(i)\t|L(i)\t|Code\n");
        sourceText.writeFile("--------------------------------------------\n");
        for (int i = 0; i < sourceText.getProbabilityArrayList().size(); i++) {
            sourceText.writeFile(sourceText.getCharacterArrayList().get(i) + "\t\t\t\t|" + sourceText.getProbabilityArrayList().get(i)
                    + "\t\t\t|" + coding.getP().get(i) + "\t\t|" + coding.getQ().get(i) + "\t|"
                    + coding.getL().get(i) + "\t\t|" + coding.getCode().get(i) + "\n");
        }

        System.out.println("Entropy: " + coding.getEntropy(sourceText.getProbabilityArrayList()));
    }
}
