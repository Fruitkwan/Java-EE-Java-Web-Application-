/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import dataaccess.TuitionDAO;
import dataaccess.TuitionDAOImpl;
import java.util.List;
import transferobjects.Tuition;

/**
 *
 * @author Nsang J
 */
public class TuitionLogic {
    
    
    
     private static final int STUDENT_NUM = 1000000;
    private static final int PAID_MAX_LENGTH = 13;
    private static final int REMAINDER_MAX_LENGTH = 13;
    
    private TuitionDAO tuition1 = null;
    
    public TuitionLogic(){
        tuition1 = new TuitionDAOImpl();
    }
    
    public List<Tuition> getAllTuition(){
        return tuition1.getAllTuition();
    }
    
    public void addTuition(Tuition tuition) throws ValidationException {
        cleanTuition(tuition);
        validateTuition(tuition);
        validateStudentNumber(tuition.getStudentNum(),"Student Number");
        validateFees(tuition.getPaid(),"Paid");
        validateFees(tuition.getRemainder(),"Remainder");
        tuition1.addTuition(tuition);
    }
    
    public void deleteTuition(Tuition tuition) throws ValidationException {
        validateString(tuition.getStudentNum(), "Student Number", STUDENT_NUM, false);
        validateStudentNumber(tuition.getStudentNum(),"Student Number");
        tuition1.deleteTuition(tuition);
    }
    
    public Tuition getTuitionByNumber(String studentNumber) throws ValidationException {
        validateString(studentNumber, "Student Number", STUDENT_NUM, false);
        validateStudentNumber(studentNumber,"Student Number");
        return tuition1.getTuitionByNumber(studentNumber);
    }
    
    private void cleanTuition(Tuition tuition) {
        if (tuition.getStudentNum() != null) {
            tuition.setStudentNum(tuition.getStudentNum().trim());
        }
        if (tuition.getPaid() != null) {
            tuition.setPaid(tuition.getPaid().trim());
        }
        if (tuition.getRemainder() != null) {
            tuition.setRemainder(tuition.getRemainder().trim());
        }
    }
    
    private void validateTuition(Tuition tuition) throws ValidationException {
        validateString(tuition.getStudentNum(), "Student Number", STUDENT_NUM, false);
        validateString(tuition.getPaid(), "Paid", PAID_MAX_LENGTH, false);
        validateString(tuition.getRemainder(), "Remainder", REMAINDER_MAX_LENGTH, false);
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
    
    private void validateStudentNumber(String value, String fieldName)throws ValidationException{
        long validate;
        try {
            validate = Long.parseLong(value);
        } catch (IllegalArgumentException e){
            throw new ValidationException(String.format("%s is not a number", fieldName));
        }
    }
    
    private void validateFees(String value, String fieldName)throws ValidationException{
        double validate;
        try {
            validate = Double.parseDouble(value);
        } catch (IllegalArgumentException e){
            throw new ValidationException(String.format("%s is not a number", fieldName));
        }
    }
}
