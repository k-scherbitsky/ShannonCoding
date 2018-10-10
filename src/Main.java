import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        SourceText sourceText = new SourceText("text.txt");
        ArrayList<Double> p = new ArrayList<Double>(sourceText.getProbabilityArrayList());
        Shannon shannon = new Shannon(sourceText.getProbabilityArrayList());
        System.out.println("Char\t|Probability\t|P(i)\t|Q(i)\t|L(i)\t|Code");
        System.out.println("------------------------------");
//        sourceText.culcProbability();
//        System.out.println(sourceText.getCharacterArrayList());
//        System.out.println(sourceText.getProbabilityArrayList());
        for (int i = 0; i < sourceText.getProbabilityArrayList().size(); i++) {
            System.out.println(sourceText.getCharacterArrayList().get(i) + "\t\t|" + sourceText.getProbabilityArrayList().get(i)
                    + "\t\t\t|" + shannon.getP().get(i) + "\t|" + shannon.getQ().get(i) + "\t|"
                    + shannon.getL().get(i) + "\t\t|" + shannon.getCode().get(i));
        }
    }
}
