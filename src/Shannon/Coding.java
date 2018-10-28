package Shannon;

import java.util.ArrayList;

public class Coding {


    private ArrayList<Double> P; //массив вероятностей, упорядоченных по убыванию
    private ArrayList<Double> Q = new ArrayList<Double>(); //массив для величин Qi
    private ArrayList<Integer> L = new ArrayList<Integer>(); // массив длин кодовых слов
    private ArrayList<String> code = new ArrayList<>();

    public Coding(ArrayList<Double> P) {
        this.P = P;
        getTable();
    }

    private void getTable() {
        Q.add(0, 0.0);
        for (double a : P) {
            L.add((int) (-logB(a) + 1));
        }

        P.add(0, 0.0);
        for (int i = 1; i < P.size() - 1; i++) {
            Q.add(i, Q.get(i - 1) + P.get(i));
        }
        P.remove(0);

        for (int i = 0; i < P.size(); i++) {
            code.add(toBinary(Q.get(i), L.get(i)));
        }
    }

    private double logB(double x) {
        return (int) Math.ceil(Math.log(x) / Math.log(2));
    }

    private String toBinary(double d, int precision) {
        long wholePart = (long) d;
        return fractionalToBinary(d - wholePart, precision);
    }

    private String fractionalToBinary(double num, int precision) {
        StringBuilder binary = new StringBuilder();
        while (num >= 0 && binary.length() < precision) {
            double r = num * 2;
            if (r >= 1) {
                binary.append(1);
                num = r - 1;
            } else {
                binary.append(0);
                num = r;
            }
        }
        return binary.toString();
    }

    public ArrayList<Double> getP() {
        return P;
    }

    public ArrayList<Double> getQ() {
        return Q;
    }

    public ArrayList<Integer> getL() {
        return L;
    }

    public ArrayList<String> getCode() {
        return code;
    }

    public double getEntropy(ArrayList<Double> p){
        double entropy = 0;

        for (double pi : p) {
            entropy += pi * logB(pi);
        }

        return -entropy;
    }
}
