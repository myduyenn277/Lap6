package Lap6.model;

public class Student implements Comparable<Student> {
    private String idStudent;
    private String studentName;
    private String semester;
    private String courseName;
    public Student(String studentName) {
        this.studentName = studentName;
    }
    public Student(String idStudent, String nameStudent, String semester, String courseName) {
        this.idStudent = idStudent;
        this.studentName = nameStudent;
        this.semester = semester;
        this.courseName = courseName;
    }
    @Override
    public int compareTo(Student t) {
        return t.studentName.compareTo(this.studentName);
    }

    public void print() {
        System.out.printf("%-15s%-15s%-15s\n", studentName, semester, courseName);
    }
    public String getIdStudent() {
        return idStudent;
    }
    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
   

}
