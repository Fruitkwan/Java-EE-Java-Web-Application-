/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import dataaccess.ClassRepDAO;
import dataaccess.ClassRepDAOImpl;
import java.util.List;
import transferobjects.ClassRep;

/**
 *
 * @course Shahriar Emami
 * @author Sidhant Son
 */
public class ClassRepLogic {

    private static final int STUDENT_NUM = 10000000;
    private static final int COURSE_NUM_MAX_LENGTH = 45;
    private static final char TERM_W = 'W', TERM_F = 'F',TERM_S = 'S';
    private static final int YEAR  = 2000;

    private ClassRepDAO classRep1 = null;


    public ClassRepLogic() {
        classRep1 = new ClassRepDAOImpl(){
        };
    }

    
    public List<ClassRep> getAllClassRep() {
        return classRep1.getAllClassRep();
    }

    
    public void addClassRep(ClassRep classRep) throws ValidationException {
        cleanClassRep(classRep);
        validateClassRep(classRep);
        validateStudentNum(classRep.getStudentNum(),"Student Number");
        validateYear(classRep.getYear(),"Year");
        classRep1.addClassRep(classRep);
       // this.classRep.addClassRep(classRep);
    }
    

    public void deleteClassRep(ClassRep classRep) throws ValidationException {
        validateString(classRep.getStudentNum(), "Student Number", STUDENT_NUM, false);
        validateStudentNum(classRep.getStudentNum(),"Student Number");
        classRep1.deleteClassRep(classRep);
    }
    
    
    public List<ClassRep> getClassRepByNumber(String studentNumber) throws ValidationException {
        validateString(studentNumber,"Student Number", STUDENT_NUM, false);
        validateStudentNum(studentNumber,"Student Number");
        return classRep1.getClassRepByNumber(studentNumber);
    }

    
    private void cleanClassRep(ClassRep classRep) {
        if (classRep.getStudentNum() != null) {
            classRep.setStudentNum(classRep.getStudentNum().trim());
        }
        if (classRep.getCourseNumber() != null) {
            classRep.setCourseNumber(classRep.getCourseNumber().trim());
        }
        if (classRep.getTerm() != null) {
            classRep.setTerm(classRep.getTerm().trim());
        }
        if (classRep.getYear() != null) {
            classRep.setYear(classRep.getYear().trim());
        }

    }
    
    
    private void validateClassRep(ClassRep classRep) throws ValidationException {
        validateString(classRep.getStudentNum(), "Student Number", STUDENT_NUM, false);
        validateString(classRep.getCourseNumber(), "Course Number", COURSE_NUM_MAX_LENGTH, false);
        //validateString(classRep.getTerm(), "Term", TERM, false);
        validateString(classRep.getYear(), "Year", YEAR, false);
    }

    
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

    
    private void validateStudentNum(String value, String fieldName)throws ValidationException{
        long validate;
        try {
            validate = Long.parseLong(value);
        } catch (IllegalArgumentException e){
            throw new ValidationException(String.format("%s is not a number", fieldName));
        }
    }
    
    
    private void validateYear(String value, String fieldName)throws ValidationException{
        int validate;
        try {
            validate = Integer.parseInt(value);
        } catch (IllegalArgumentException e){
            throw new ValidationException(String.format("%s is not a number", fieldName));
        }
    }
}
