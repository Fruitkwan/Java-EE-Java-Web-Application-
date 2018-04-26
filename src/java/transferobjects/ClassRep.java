/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transferobjects;

/**
 *
 */


public class ClassRep {

   private String studentNumber;
   private String courseNumber;
   private String term;
   private String year;
        
    public ClassRep(){}
    
    
    public ClassRep(String studentNumber,String courseNumber, String term, String year){
        setStudentNum(studentNumber);
        setCourseNumber(courseNumber);
        setTerm(term);
        setYear(year);
    }
    
    
    public void setStudentNum(String studentNumber){
        this.studentNumber = studentNumber;
    }
    
    
    public String getStudentNum(){
        return studentNumber;
    }
    
    
    public void setCourseNumber(String courseNumber){
        this.courseNumber = courseNumber;
    }
    public String getCourseNumber(){
        return courseNumber;
    }
    
    
    public void setTerm(String term){
        this.term = term;
    }
    public String getTerm(){
        return term;
    }
    
    
    public void setYear(String year){
        this.year = year;
    }
    public String getYear(){
        return year;
    }
    
}
