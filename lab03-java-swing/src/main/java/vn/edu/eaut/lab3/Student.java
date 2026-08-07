package vn.edu.eaut.lab3;

public class Student {
    private String id;
    private String name;
    private double gpa;

    public Student(String id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    public String xepLoai() {
        if (gpa >= 8.5) return "Giỏi";
        if (gpa >= 7.0) return "Khá";
        if (gpa >= 5.0) return "Trung bình";
        return "Yếu";
    }
}