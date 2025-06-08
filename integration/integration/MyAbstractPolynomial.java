package hus.oop.integration;

public abstract class MyAbstractPolynomial implements MyPolynomial {
    /**
     * Mô tả đa thức theo định dạng [a0 + a1x + a2x^2 + ... + anx^n]
     * 
     * @return String mô tả về đa thức.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        double[] coeffs = coefficients();
        for (int i = 0; i < coeffs.length; i++) {
            if (i > 0)
                sb.append(" + ");
            sb.append(coeffs[i]);
            if (i > 0) {
                sb.append("x");
                if (i > 1)
                    sb.append("^" + i);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Lấy đạo hàm đa thức.
     * 
     * @return mảng các phần tử là hệ số của đa thức đạo hàm.
     */
    public double[] differentiate() {
        double[] coeffs = coefficients();
        if (coeffs.length <= 1)
            return new double[] { 0 };
        double[] diff = new double[coeffs.length - 1];
        for (int i = 1; i < coeffs.length; i++) {
            diff[i - 1] = coeffs[i] * i;
        }
        return diff;
    }
}
