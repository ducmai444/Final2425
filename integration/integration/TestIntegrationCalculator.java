package hus.oop.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestIntegrationCalculator {
  private MyPolynomial polynomial;

  public TestIntegrationCalculator(MyPolynomial polynomial) {
    this.polynomial = polynomial;
  }

  public static void main(String[] args) {
    StringBuilder output = new StringBuilder();
    TestIntegrationCalculator tester = new TestIntegrationCalculator(null);
    output.append("===== Test Array Polynomial =====\n");
    output.append(tester.testArrayPolynomial());
    output.append("\n===== Test List Polynomial =====\n");
    output.append(tester.testListPolynomial());
    // Ghi ra file
    try (FileWriter writer = new FileWriter("NguyenVanA_123456_Integration.txt")) {
      writer.write(output.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String testArrayPolynomial() {
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    int size = random.nextInt(5) + 3; // Đa thức bậc 3-7
    MyArrayPolynomial poly = new MyArrayPolynomial();
    for (int i = 0; i < size; i++) {
      poly.append(random.nextDouble() * 10 - 5); // Hệ số [-5, 5]
    }
    this.polynomial = poly;
    sb.append("Đa thức ban đầu: ").append(poly).append("\n");
    sb.append("Bậc: ").append(poly.degree()).append("\n");
    sb.append("Đạo hàm: ").append(poly.derivative()).append("\n");
    sb.append("Giá trị tại x=2: ").append(poly.evaluate(2)).append("\n");
    // Thêm, sửa, xóa
    poly.append(3.14);
    sb.append("Sau append 3.14: ").append(poly).append("\n");
    poly.set(0, 0);
    sb.append("Sau set hệ số bậc 0 = 0: ").append(poly).append("\n");
    poly.add(-2.5, 1);
    sb.append("Sau add -2.5 vào vị trí 1: ").append(poly).append("\n");
    // Cộng, trừ, nhân
    MyArrayPolynomial poly2 = new MyArrayPolynomial();
    for (int i = 0; i < size; i++)
      poly2.append(random.nextDouble() * 10 - 5);
    sb.append("Đa thức thứ 2: ").append(poly2).append("\n");
    sb.append("poly + poly2: ").append(poly.plus(poly2)).append("\n");
    sb.append("poly - poly2: ").append(poly.minus(poly2)).append("\n");
    sb.append("poly * poly2: ").append(poly.multiply(poly2)).append("\n");
    // Tích phân
    MidpointRule midpoint = new MidpointRule(1e-6, 20);
    TrapezoidRule trapezoid = new TrapezoidRule(1e-6, 20);
    SimpsonRule simpson = new SimpsonRule(1e-6, 20);
    IntegrationCalculator calc = new IntegrationCalculator(poly);
    calc.setIntegrator(midpoint);
    sb.append("Tích phân (Midpoint, [1,5]): ").append(calc.integrate(1, 5)).append("\n");
    calc.setIntegrator(trapezoid);
    sb.append("Tích phân (Trapezoid, [1,5]): ").append(calc.integrate(1, 5)).append("\n");
    calc.setIntegrator(simpson);
    sb.append("Tích phân (Simpson, [1,5]): ").append(calc.integrate(1, 5)).append("\n");
    return sb.toString();
  }

  public String testListPolynomial() {
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    int size = random.nextInt(5) + 3;
    MyListPolynomial poly = new MyListPolynomial();
    for (int i = 0; i < size; i++) {
      poly.append(random.nextDouble() * 10 - 5);
    }
    this.polynomial = poly;
    sb.append("Đa thức ban đầu: ").append(poly).append("\n");
    sb.append("Bậc: ").append(poly.degree()).append("\n");
    sb.append("Đạo hàm: ").append(poly.derivative()).append("\n");
    sb.append("Giá trị tại x=2: ").append(poly.evaluate(2)).append("\n");
    poly.append(2.71);
    sb.append("Sau append 2.71: ").append(poly).append("\n");
    poly.set(0, 0);
    sb.append("Sau set hệ số bậc 0 = 0: ").append(poly).append("\n");
    poly.add(-1.5, 1);
    sb.append("Sau add -1.5 vào vị trí 1: ").append(poly).append("\n");
    MyListPolynomial poly2 = new MyListPolynomial();
    for (int i = 0; i < size; i++)
      poly2.append(random.nextDouble() * 10 - 5);
    sb.append("Đa thức thứ 2: ").append(poly2).append("\n");
    sb.append("poly + poly2: ").append(poly.plus(poly2)).append("\n");
    sb.append("poly - poly2: ").append(poly.minus(poly2)).append("\n");
    sb.append("poly * poly2: ").append(poly.multiply(poly2)).append("\n");
    MidpointRule midpoint = new MidpointRule(1e-6, 20);
    TrapezoidRule trapezoid = new TrapezoidRule(1e-6, 20);
    SimpsonRule simpson = new SimpsonRule(1e-6, 20);
    IntegrationCalculator calc = new IntegrationCalculator(poly);
    calc.setIntegrator(midpoint);
    sb.append("Tích phân (Midpoint, [2,6]): ").append(calc.integrate(2, 6)).append("\n");
    calc.setIntegrator(trapezoid);
    sb.append("Tích phân (Trapezoid, [2,6]): ").append(calc.integrate(2, 6)).append("\n");
    calc.setIntegrator(simpson);
    sb.append("Tích phân (Simpson, [2,6]): ").append(calc.integrate(2, 6)).append("\n");
    return sb.toString();
  }
}
