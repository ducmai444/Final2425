package hus.oop.integration;

import java.util.ArrayList;
import java.util.List;

public class MyListPolynomial extends MyAbstractPolynomial {
    private List<Double> coefficients;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyListPolynomial() {
        coefficients = new ArrayList<>();
    }

    @Override
    public double coefficient(int index) {
        if (index < 0 || index >= coefficients.size()) throw new IndexOutOfBoundsException();
        return coefficients.get(index);
    }

    @Override
    public double[] coefficients() {
        double[] result = new double[coefficients.size()];
        for (int i = 0; i < coefficients.size(); i++) {
            result[i] = coefficients.get(i);
        }
        return result;
    }

    @Override
    public MyListPolynomial append(double coefficient) {
        coefficients.add(coefficient);
        return this;
    }

    @Override
    public MyListPolynomial add(double coefficient, int index) {
        if (index < 0 || index > coefficients.size()) throw new IndexOutOfBoundsException();
        coefficients.add(index, coefficient);
        return this;
    }

    @Override
    public MyListPolynomial set(double coefficient, int index) {
        if (index < 0 || index >= coefficients.size()) throw new IndexOutOfBoundsException();
        coefficients.set(index, coefficient);
        return this;
    }

    @Override
    public int degree() {
        return coefficients.size() - 1;
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            result += coefficients.get(i) * Math.pow(x, i);
        }
        return result;
    }

    @Override
    public MyListPolynomial derivative() {
        MyListPolynomial result = new MyListPolynomial();
        for (int i = 1; i < coefficients.size(); i++) {
            result.append(coefficients.get(i) * i);
        }
        return result;
    }

    @Override
    public MyListPolynomial plus(MyPolynomial right) {
        int maxDegree = Math.max(this.degree(), right.degree());
        MyListPolynomial result = new MyListPolynomial();
        for (int i = 0; i <= maxDegree; i++) {
            double a = (i <= this.degree()) ? this.coefficient(i) : 0;
            double b = (i <= right.degree()) ? right.coefficient(i) : 0;
            result.append(a + b);
        }
        return result;
    }

    @Override
    public MyListPolynomial minus(MyPolynomial right) {
        int maxDegree = Math.max(this.degree(), right.degree());
        MyListPolynomial result = new MyListPolynomial();
        for (int i = 0; i <= maxDegree; i++) {
            double a = (i <= this.degree()) ? this.coefficient(i) : 0;
            double b = (i <= right.degree()) ? right.coefficient(i) : 0;
            result.append(a - b);
        }
        return result;
    }

    @Override
    public MyListPolynomial multiply(MyPolynomial right) {
        int newDegree = this.degree() + right.degree();
        double[] newCoeffs = new double[newDegree + 1];
        for (int i = 0; i <= this.degree(); i++) {
            for (int j = 0; j <= right.degree(); j++) {
                newCoeffs[i + j] += this.coefficient(i) * right.coefficient(j);
            }
        }
        MyListPolynomial result = new MyListPolynomial();
        for (int i = 0; i < newCoeffs.length; i++) {
            result.append(newCoeffs[i]);
        }
        return result;
    }
}
