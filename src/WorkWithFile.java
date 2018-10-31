import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkWithFile {

    private ArrayList<String> characterArrayList = new ArrayList<>();
    private ArrayList<Double> probabilityArrayList = new ArrayList<>();

    public WorkWithFile(String path) throws IOException {
        culcProbability(readFile(path));
    }

    public void writeFile(String fileName, String str) throws IOException {
        FileWriter file = new FileWriter(fileName, true);
        file.write(str);
        file.close();
    }

    public String readFile(String fileName) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        String res = "";
        int b = file.read();
        while (b != -1) {
            res += (char) b;
            b = file.read();
        }
        file.close();
        return res;
    }

    private void culcProbability(String text) {
        int howChar = text.length(), count, i = 0;
        char[] simbol = text.toCharArray();
        Set<Character> setChar = new HashSet<>();
        for (char temp : simbol) {
            setChar.add(temp);
        }
        char[] listChar = new char[setChar.size()];
        int[] index = new int[listChar.length];
        for (Character temp : setChar) {
            listChar[i] = temp;
            count = 0;
            for (int j = 0; j < simbol.length; j++) {
                if (listChar[i] == simbol[j]) {
                    count++;
                }
            }
            index[i] = count;
            i++;
        }
        for (int j = 0; j < index.length; j++) {
            for (int k = 0; k < index.length - 1; k++) {
                if (index[k] < index[k + 1]) {
                    int temp = index[k];
                    char tmp = listChar[k];
                    index[k] = index[k + 1];
                    listChar[k] = listChar[k + 1];
                    index[k + 1] = temp;
                    listChar[k + 1] = tmp;
                }
            }
        }
        for (int k = 0; k < listChar.length; k++) {

            if (listChar[k] == '\n') {
                characterArrayList.add(String.valueOf("(\\n)"));
            } else if (listChar[k] == ' ') {
                characterArrayList.add(String.valueOf("(space)"));
            } else if (listChar[k] == '\r') {
                characterArrayList.add(String.valueOf("(\\r)"));
            } else {
                characterArrayList.add(String.valueOf(listChar[k]));
            }

            probabilityArrayList.add((double) index[k] / howChar);
        }
    }

    public ArrayList<String> getCharacterArrayList() {
        return characterArrayList;
    }

    public ArrayList<Double> getProbabilityArrayList() {
        return probabilityArrayList;
    }
}
