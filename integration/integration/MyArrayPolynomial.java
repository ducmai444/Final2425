package hus.oop.integration;

public class MyArrayPolynomial extends MyAbstractPolynomial {
    private static final int DEFAULT_CAPACITY = 8;
    private double[] coefficents;
    private int size;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyArrayPolynomial() {
        coefficents = new double[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public double coefficient(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return coefficents[index];
    }

    @Override
    public double[] coefficients() {
        double[] result = new double[size];
        System.arraycopy(coefficents, 0, result, 0, size);
        return result;
    }

    @Override
    public MyArrayPolynomial append(double coefficient) {
        if (size == coefficents.length)
            allocateMore();
        coefficents[size++] = coefficient;
        return this;
    }

    @Override
    public MyArrayPolynomial add(double coefficient, int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (size == coefficents.length)
            allocateMore();
        for (int i = size; i > index; i--) {
            coefficents[i] = coefficents[i - 1];
        }
        coefficents[index] = coefficient;
        size++;
        return this;
    }

    @Override
    public MyArrayPolynomial set(double coefficient, int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        coefficents[index] = coefficient;
        return this;
    }

    @Override
    public int degree() {
        return size - 1;
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < size; i++) {
            result += coefficents[i] * Math.pow(x, i);
        }
        return result;
    }

    @Override
    public MyArrayPolynomial derivative() {
        MyArrayPolynomial result = new MyArrayPolynomial();
        for (int i = 1; i < size; i++) {
            result.append(coefficents[i] * i);
        }
        return result;
    }

    @Override
    public MyArrayPolynomial plus(MyPolynomial right) {
        int maxDegree = Math.max(this.degree(), right.degree());
        MyArrayPolynomial result = new MyArrayPolynomial();
        for (int i = 0; i <= maxDegree; i++) {
            double a = (i <= this.degree()) ? this.coefficient(i) : 0;
            double b = (i <= right.degree()) ? right.coefficient(i) : 0;
            result.append(a + b);
        }
        return result;
    }

    @Override
    public MyArrayPolynomial minus(MyPolynomial right) {
        int maxDegree = Math.max(this.degree(), right.degree());
        MyArrayPolynomial result = new MyArrayPolynomial();
        for (int i = 0; i <= maxDegree; i++) {
            double a = (i <= this.degree()) ? this.coefficient(i) : 0;
            double b = (i <= right.degree()) ? right.coefficient(i) : 0;
            result.append(a - b);
        }
        return result;
    }

    @Override
    public MyArrayPolynomial multiply(MyPolynomial right) {
        int newDegree = this.degree() + right.degree();
        double[] newCoeffs = new double[newDegree + 1];
        for (int i = 0; i <= this.degree(); i++) {
            for (int j = 0; j <= right.degree(); j++) {
                newCoeffs[i + j] += this.coefficient(i) * right.coefficient(j);
            }
        }
        MyArrayPolynomial result = new MyArrayPolynomial();
        for (int i = 0; i < newCoeffs.length; i++) {
            result.append(newCoeffs[i]);
        }
        return result;
    }

    /**
     * Tăng kích thước mảng lên gấp đôi để lưu đa thức khi cần thiết.
     */
    private void allocateMore() {
        double[] newCoeffs = new double[coefficents.length * 2];
        System.arraycopy(coefficents, 0, newCoeffs, 0, coefficents.length);
        coefficents = newCoeffs;
    }
}
