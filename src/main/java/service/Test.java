package service;

public class Test {
    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    private String b;

    public Test(int a, String b) {
        this.a = a;
        this.b = b;
    }
}
