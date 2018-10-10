import java.util.ArrayList;

public class Shannon {


    private ArrayList<Double> P;
    private ArrayList<Double> Q = new ArrayList<Double>();
    private ArrayList<Integer> L = new ArrayList<Integer>();
    private ArrayList<String> code = new ArrayList<>();

    public Shannon(ArrayList<Double> P) {
        this.P = P;
        getTable();
    }

    private void getTable() {
        Q.add(0, 0.0);
        for (int j = 0; j < P.size(); j++) {
            L.add(j, (int) (-logB(P.get(j)) + 1));
        }

        P.add(0, 0.0);
        for (int i = 1; i < P.size() - 1; i++) {
            double d = Math.round((Q.get(i - 1) + P.get(i)) * 100d) / 100d;
            Q.add(i, d);
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
}
