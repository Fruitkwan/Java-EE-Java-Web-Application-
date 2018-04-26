/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import dataaccess.StudentsDAO;
import dataaccess.StudentsDAOImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import transferobjects.Students;

/**
 *
 * @author Nsang Joanne
 */
public class StudentsLogic {
    
     private static final int STUDENT_NUM = 10000000;
    private static final int FIRST_NAME_MAX_LENGTH = 45;
    private static final int LAST_NAME_MAX_LENGTH = 45;
 /* private static DATE DATE_OF_BIRTH;
    private static DATE DATE_ENROLLED;
    */
    
    
    private StudentsDAO students1 = null;
    /**
     * Default constructor
     */
    public StudentsLogic(){
        students1 = new StudentsDAOImpl();
    }
    /**
     * This method is used to get all students information
     * @return 
     */
    public List<Students> getAllStudents(){
        return students1.getAllStudents();
    }
    
    /**
     * This method is used to add a student into Student Database
     * @param student
     * @throws ValidationException 
     */
    public void addStudent(Students student) throws ValidationException {
        cleanStudent(student);
        validateStudent(student);
        validateStudentNumber(student.getStudentNum(), "Student Number");
        validateDate(student.getDateBirth(), "Date Birth");
        validateDate(student.getEnrolled(), "Enrolled");
        students1.addStudent(student);
    }
    
   /**
    * This method is used to get a student information by the student's number
    * @param studentNumber
    * @return
    * @throws ValidationException 
    */
    
    public Students getStudentByNumber(String studentNumber) throws ValidationException {
        validateString(studentNumber, "Student Number", STUDENT_NUM, false);
        validateStudentNumber(studentNumber, "Student Number");
        return students1.getStudentByNumber(studentNumber);
    }
    
    
    /**
    * This method is used to get and set the Student Info and the trim eliminates leading and trailing spaces
    * @param student
    */
    
    private void cleanStudent(Students student) {
        if (student.getStudentNum() != null) {
            student.setStudentNum(student.getStudentNum().trim());
        }
        if (student.getFirstName() != null) {
            student.setFirstName(student.getFirstName().trim());
        }
        if (student.getLastName() != null) {
            student.setLastName(student.getLastName().trim());
        }
        if (student.getDateBirth() != null) {
            student.setDateBirth(student.getDateBirth().trim());
        }
        if (student.getEnrolled() != null) {
            student.setEnrolled(student.getEnrolled().trim());
        }
    }
    
    
    /**
    * This method is used to validate the student's information entered
    * @param student
    * @throws ValidationException 
    */
    
    private void validateStudent(Students student) throws ValidationException {
        validateString(student.getStudentNum(), "Student Number", STUDENT_NUM, false);
        validateString(student.getFirstName(), "First Name", FIRST_NAME_MAX_LENGTH, false);
        validateString(student.getLastName(), "Last Name", LAST_NAME_MAX_LENGTH, false);
    }

    
    /**
    * This method is used to validate the String
    * @param value, fieldName, maxLength, isNullAllowed
    * @throws ValidationException 
    */
    
    private void validateString(String value, String fieldName, int maxLength, boolean isNullAllowed) throws ValidationException {
        if (value == null && isNullAllowed) {
            // null permitted, nothing to validate
        } else if (value == null && !isNullAllowed) {
            throw new ValidationException(String.format("%s cannot be null", fieldName));
        } else if (value.isEmpty()) {
            throw new ValidationException(String.format("%s cannot be empty or only whitespace", fieldName));
        } else if (value.length() > maxLength) {
            throw new ValidationException(String.format("%s cannot exceed %d characters", fieldName, maxLength));
        }
    }
    
    
    /**
    * This method is used to validate the Student Number
    * @param value, fieldName
    * @throws ValidationException 
    */
    
    private void validateStudentNumber(String value, String fieldName) throws ValidationException{
        long validate;
        try {
            validate = Long.parseLong(value);
        } catch (IllegalArgumentException e){
            throw new ValidationException(String.format("%s is not a number", fieldName));
        }
    }
    
    
    /**
    * This method is used to validate the Date format
    * @param value, fieldName
    * @throws ValidationException 
    */
    
    private void validateDate(String value, String fieldName) throws ValidationException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(value);
        } catch (ParseException e) {
            throw new ValidationException(String.format("%s does not match the Date format", fieldName));
        }
    }
    
}
