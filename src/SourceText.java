import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SourceText {

    private ArrayList<String> characterArrayList = new ArrayList<>();
    private ArrayList<Double> probabilityArrayList = new ArrayList<>();
    private String path;

    public SourceText(String path) throws IOException {
        this.path = path;
        culcProbability(readFile());
    }

    private String readFile() throws IOException {
        RandomAccessFile file = new RandomAccessFile(path, "r");
        String res = "";
        int b = file.read();
        while (b != -1){
            res += (char) b;
            b = file.read();
        }
        file.close();
        return res;
    }

    private void culcProbability(String text) {
        String str = "Но если есть в кармане пачка сигарет...";
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

            characterArrayList.add(String.valueOf(listChar[k]));
            int a = index[k];
            int b = howChar;
            double d = (double) a / b;
            probabilityArrayList.add(Math.round(d * 10000d) / 10000d);
//            System.out.println(listChar[k] + " - " + index[k] + "/" + howChar);
        }
    }

    public ArrayList<String> getCharacterArrayList() {
        return characterArrayList;
    }

    public ArrayList<Double> getProbabilityArrayList() {
        return probabilityArrayList;
    }
}
