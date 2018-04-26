/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import dataaccess.RegistryDAO;
import dataaccess.RegistryDAOImpl;
import java.util.List;
import transferobjects.Registry;

/**
 *
 * @author Jo-1
 */
public class RegistryLogic {
  
    
    private static final int STUDENT_NUM = 10000000;
    private static final int COURSE_NUM_MAX_LENGTH = 45;
    private static final char TERM_W = 'W', TERM_F = 'F',TERM_S = 'S';
    private static final int YEAR  = 2000;
    
    private RegistryDAO registry1 = null;
    
    /**
     * default constructor
     */
    public RegistryLogic(){
        registry1 = new RegistryDAOImpl();
    }
    /**
     * This method is used to get all registry information
     * 
     */
    public List<Registry> getAllRegistry(){
        return registry1.getAllRegistry();
    }
    /**
     * This method is used to add a registry to the Database
     * @param registry
     * @throws ValidationException 
     */
    public void addRegistry(Registry registry) throws ValidationException {
        cleanRegistry(registry);
        validateRegistry(registry);
        validateStudentNumber(registry.getStudentNum(),"Student Number");
        validateYear(registry.getYear(),"Year");
        registry1.addRegistry(registry);
    }
    /**
     * This method is used to delete a register from the Database
     * @param registry
     * @throws ValidationException 
     */
    public void deleteRegistry(Registry registry) throws ValidationException {
        validateString(registry.getStudentNum(), "Student Number", STUDENT_NUM, false);
        validateStudentNumber(registry.getStudentNum(),"Student Number");
        registry1.deleteRegistry(registry);
    }
    
    /**
     *This method is used to get a Register by student number
     * @param studentNumber
     * 
     * @throws ValidationException 
     */
    public List<Registry> getRegistryByNumber(String studentNumber) throws ValidationException {
        validateString(studentNumber,"Student Number", STUDENT_NUM, false);
        validateStudentNumber(studentNumber,"Student Number");
        return registry1.getRegistryByNumber(studentNumber);
    }
   
    
     
    /**
    * This method is used to get and set the Student Info and the trim eliminates leading and trailing spaces
    * @param student
    */
    
    private void cleanRegistry(Registry registry) {
        if (registry.getStudentNum() != null) {
            registry.setStudentNum(registry.getStudentNum().trim());
        }
        if (registry.getCourseNumber() != null) {
            registry.setCourseNumber(registry.getCourseNumber().trim());
        }
        if (registry.getTerm() != null) {
            registry.setTerm(registry.getTerm().trim());
        }
        if (String.valueOf(registry.getYear()) != null) {
            registry.setYear(registry.getYear());
        }
    }
    
    
    /**
    * This method is used to validate the Registry information entered
    * @param registry
    * @throws ValidationException 
    */
    
    private void validateRegistry(Registry registry) throws ValidationException {
        validateString(registry.getStudentNum(), "Student Number", STUDENT_NUM, false);
        validateString(registry.getCourseNumber(), "Course Number", COURSE_NUM_MAX_LENGTH, false);
        validateString(registry.getYear(), "Year", YEAR, false);
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
    
    private void validateStudentNumber(String value, String fieldName)throws ValidationException{
        long validate;
        try {
            validate = Long.parseLong(value);
        } catch (IllegalArgumentException e){
            throw new ValidationException(String.format("%s is not a number", fieldName));
        }
    }
    
    
      /**
    * This method is used to validate the year
    * @param value, fieldName
    * @throws ValidationException 
    */
    
    private void validateYear(String value, String fieldName)throws ValidationException{
        int validate;
        try {
            validate = Integer.parseInt(value);
        } catch (IllegalArgumentException e){
            throw new ValidationException(String.format("%s is not a number", fieldName));
        }
    }
    
    
}
