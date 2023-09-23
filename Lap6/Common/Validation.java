package Lap6.Common;

import java.util.ArrayList;
import java.util.Scanner;

import Lap6.model.Report;
import Lap6.model.Student;
public class Validation {
    private final static Scanner in = new Scanner(System.in);

    
    public static int checkInputIntLimit(int min, int max) {
        
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    //check user input string
    public static String checkInputString() {
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    //check user input yes/ no
    public static boolean checkInputYN() {
   
        while (true) {
            String result = checkInputString();
          
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
          
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    //check user input u / d
    public static boolean checkInputUD() {
       
        while (true) {
            String result = checkInputString();
          
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
           
            if (result.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Please input u/U or d/D.");
            System.out.print("Enter again: ");
        }
    }

    
    public static String checkInputCourse() {
       
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                return result;
            }
            System.err.println("Only three courses: Java, .Net, C/C++");
            System.out.print("Enter again: ");
        }
    }

   
    public static boolean checkStudentExist(ArrayList<Student> studentList, String id,
            String studentName, String semester, String courseName) {
        int size = studentList.size();
        for (Student student : studentList) {
            if (id.equalsIgnoreCase(student.getIdStudent())
                    && studentName.equalsIgnoreCase(student.getStudentName())
                    && semester.equalsIgnoreCase(student.getSemester())
                    && courseName.equalsIgnoreCase(student.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    
    public static boolean checkReportExist(ArrayList<Report> lr, String name,
            String course, int total) {
        for (Report report : lr) {
            if (name.equalsIgnoreCase(report.getStudentName())
                    && course.equalsIgnoreCase(report.getCourseName())
                    && total == report.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkIdExist(ArrayList<Student> studentList, String id, String name) {
        for (Student student : studentList) {
            if (id.equalsIgnoreCase(student.getIdStudent())
                    && !name.equalsIgnoreCase(student.getStudentName())) {
                return false;
            }
        }
        return true;
    }
    

}
