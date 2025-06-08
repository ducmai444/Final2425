package hus.oop.integration;

public class TrapezoidRule implements MyIntegrator {
    private double precision;
    private int maxIterations;

    public TrapezoidRule(double precision, int maxIterations) {
        this.precision = precision;
        this.maxIterations = maxIterations;
    }

    /**
     * Tính xấp xỉ giá trị tích phân. Giá trị xấp xỉ được chấp nhận nếu phép tính
     * đạt độ chính xác đã cho,
     * hoặc có số vòng vượt quá ngưỡng quy định.
     * Độ chính xác được xác định như sau, chọn n0 tùy ý, sau đó tính I_n với n =
     * n0, 2n0, 4n0, ...
     * Việc tính toán dừng lại khi |I_2n - In|/3 < eps (precision), hoặc số lần chia
     * đôi vượt quá ngưỡng quy định (maxIterations).
     * 
     * @param polynomial
     * @param lower
     * @param upper
     * @return
     */
    @Override
    public double integrate(MyPolynomial polynomial, double lower, double upper) {
        int n = 4; // số phân hoạch ban đầu
        double prev = integrate(polynomial, lower, upper, n);
        int count = 0;
        while (count < maxIterations) {
            n *= 2;
            double curr = integrate(polynomial, lower, upper, n);
            if (Math.abs(curr - prev) / 3 < precision) {
                return curr;
            }
            prev = curr;
            count++;
        }
        return prev;
    }

    /**
     * Tính xấp xỉ giá trị tích phân với numOfSubIntervals khoảng phân hoạch đều.
     * 
     * @param polynomial
     * @param lower
     * @param upper
     * @param numOfSubIntervals
     * @return giá trị xấp xỉ giá trị tích phân.
     */
    private double integrate(MyPolynomial polynomial, double lower, double upper, int numOfSubIntervals) {
        double h = (upper - lower) / numOfSubIntervals;
        double sum = 0.5 * (polynomial.evaluate(lower) + polynomial.evaluate(upper));
        for (int i = 1; i < numOfSubIntervals; i++) {
            double xi = lower + i * h;
            sum += polynomial.evaluate(xi);
        }
        return h * sum;
    }
}
