import Shannon.AlphabetBuilder;
import Shannon.Coding;
import Shannon.Decoding;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        WorkWithFile workWithFile = new WorkWithFile("text.txt");
        AlphabetBuilder alphabetBuilder = new AlphabetBuilder(workWithFile.getProbabilityArrayList());
        workWithFile.writeFile("alphabet.txt", "Char\t\t\t|Probability\t|P(i)\t\t|Q(i)\t|L(i)\t|Code\n");
        workWithFile.writeFile("alphabet.txt", "--------------------------------------------\n");
        for (int i = 0; i < workWithFile.getProbabilityArrayList().size(); i++) {
            workWithFile.writeFile("alphabet.txt", workWithFile.getCharacterArrayList().get(i) + "\t\t\t\t|"
                    + workWithFile.getProbabilityArrayList().get(i) + "\t\t\t|"
                    + alphabetBuilder.getProbabilityArray().get(i) + "\t\t|" + alphabetBuilder.getQArray().get(i) + "\t|"
                    + alphabetBuilder.getCodeLengthArray().get(i) + "\t\t|" + alphabetBuilder.getCode().get(i) + "\n");
        }

        System.out.println("Entropy: " + alphabetBuilder.getEntropy(workWithFile.getProbabilityArrayList()));

        Coding coding = new Coding(alphabetBuilder.getCode(), workWithFile.getCharacterArrayList(), workWithFile.readFile("text.txt"));
        workWithFile.writeFile("encoded.txt", coding.textToBinText());

        Decoding decoding = new Decoding(alphabetBuilder.getCode(), workWithFile.getCharacterArrayList(), workWithFile.readFile("encoded.txt"));
        workWithFile.writeFile("decoded-text.txt", decoding.binTextToText());
    }
}
