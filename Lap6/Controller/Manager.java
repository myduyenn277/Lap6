package Lap6.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Lap6.Common.Validation;
import Lap6.model.Report;
import Lap6.model.Student;
import Lap6.view.Menu;


     public class Manager extends Menu<String> {

        static String[] opsList = {"Create", "Find and Sort", "Update/Delete","Report","Exit"};
        protected ArrayList<Student> studentList;
        Validation validation = new Validation();
        static Scanner sc = new Scanner(System.in);
    
        public Manager() {
            super("Manager Student Menu", opsList);
            studentList = new ArrayList<>();
        }
    
        @Override
        public void execute(int n) {
            switch (n) {
                case 1: {
                    createStudent(studentList);
                    break;
                }
                case 2: {
                   findAndSort(studentList);
                   break;
                }
                case 3:{
                updateOrDelete(studentList);
                break;
                }
                case 4:{
                report(studentList);
                    break;
                }
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Please, enter again!");
            }
        }
 
        public  void createStudent(ArrayList<Student>studentList) {
            int count=0;    
            System.out.print("Enter idStudent: ");
            String idStudent = Validation.checkInputString();
            System.out.print("Enter name student: ");
            String studentName = Validation.checkInputString();
            System.out.print("Enter semester: ");
            String semester = Validation.checkInputString();
            System.out.print("Enter name course: ");
            String courseName = Validation.checkInputCourse();
            
            if (Validation.checkStudentExist(studentList, idStudent, studentName, semester, courseName)) {
                studentList.add(new Student(idStudent, studentName, semester, courseName));
                count++;
                System.out.println("Add student success.");
                return;
            }
             if (count > 10) {
                System.out.print("Do you want to continue (Y/N): ");
                if (!Validation.checkInputYN()) {
                    return;
                }
            }
            }
    

     public  void findAndSort(ArrayList<Student>studentList) {
        if (studentList.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Student> listStudentFindByName = listStudentFindByName(studentList);
        if (listStudentFindByName.isEmpty()) {
            System.err.println("Not exist.");
        } else {
            Collections.sort(listStudentFindByName);
            System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");

            for (Student student : listStudentFindByName) {
                student.print();
            }
        }
    }

    public  ArrayList<Student> listStudentFindByName(ArrayList<Student> studentList) {
        ArrayList<Student> listStudentFindByName = new ArrayList<>();
        System.out.print("Enter name to search: ");
        String name = Validation.checkInputString();
        for (Student student : studentList) {
            if (student.getStudentName().contains(name)) {
                listStudentFindByName.add(student);
            }
        }
        return listStudentFindByName;
    }

     public static void updateOrDelete( ArrayList<Student> studentList) {
        int count=0;
        //if list empty 
        if (studentList.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.print("Enter id: ");
        String id = Validation.checkInputString();
        ArrayList<Student> listStudentFindByName = getListStudentById(studentList, id);
        //check list empty
        if (listStudentFindByName.isEmpty()) {
            System.err.println("Not found student.");
            return;
        } else {
            Student student = getStudentByListFound(listStudentFindByName);
            System.out.print("Do you want to update (U) or delete (D) student: ");
            //check user want to update or delete
            if (Validation.checkInputUD()) {
                System.out.print("Enter name student: ");
                String name = Validation.checkInputString();
                System.out.print("Enter semester: ");
                String semester = Validation.checkInputString();
                System.out.print("Enter name course: ");
                String course = Validation.checkInputCourse();
                //check student exist or not
                if (Validation.checkStudentExist(studentList, id, name, semester, course)) {
                    student.setStudentName(name);
                    student.setSemester(semester);
                    student.setCourseName(course);
                    System.err.println("Update success.");
                }
                return;
            } else {
                studentList.remove(student);
                count--;
                System.err.println("Delete success.");
                return;
            }
        }
    }

    public static Student getStudentByListFound(ArrayList<Student> listStudentFindByName) {
        System.out.println("List student found: ");
        int count = 1;
        System.out.printf("%-10s%-15s%-15s%-15s\n", "Number", "Student name",
                "semester", "Course Name");
        //display list student found
        for (Student student : listStudentFindByName) {
            System.out.printf("%-10d%-15s%-15s%-15s\n", count,
                    student.getStudentName(), student.getSemester(),
                    student.getCourseName());
            count++;
        }
        System.out.print("Enter student: ");
        int choice = Validation.checkInputIntLimit(1, listStudentFindByName.size());
        return listStudentFindByName.get(choice - 1);
    }

    //Get list student find by id
    public static ArrayList<Student> getListStudentById(ArrayList<Student> studentList, String id) {
        ArrayList<Student> getListStudentById = new ArrayList<>();
        for (Student student : studentList) {
            if (id.equalsIgnoreCase(student.getIdStudent())) {
                getListStudentById.add(student);
            }
        }
        return getListStudentById;
    }

    //Print report
    public static void report(ArrayList<Student> studentList) {
        if (studentList.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Report> list = new ArrayList<>();
        int size = studentList.size();
        for (int i = 0; i < size; i++) {
            int total = 0;
            for (Student student1 : studentList) {
                for (Student student2 : studentList) {
                    if (student1.getIdStudent().equalsIgnoreCase(student2.getIdStudent())
                            && student1.getCourseName().equalsIgnoreCase(student2.getCourseName())) {
                        total++;
                    }
                }
                if (Validation.checkReportExist(list, student1.getStudentName(),
                        student1.getCourseName(), total)) {
                    list.add(new Report(student1.getStudentName(),
                            student1.getCourseName(), total));
                }
            }
        }
        //print report
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-15s|%-10s|%-5d\n", list.get(i).getStudentName(),
                    list.get(i).getCourseName(), list.get(i).getTotalCourse());
        }
    }
}

    

