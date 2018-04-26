/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transferobjects;




public class Students {

    private String studentNumber;
    private String firstName;
    private String lastName;
    private String dateBirth;
    private String enrolled;
    
    
    public Students(){}
    
    
    public Students(String studentNumber, String firstName, String lastName, String dateBirth, String enrolled){
        setStudentNum(studentNumber);
        setFirstName(firstName);
        setLastName(lastName);
        setDateBirth(dateBirth);
        setEnrolled(enrolled);
    }
   
    
    public String getStudentNum(){
        return studentNumber;
    }
    public void setStudentNum(String studentNumber){
        this.studentNumber = studentNumber;
    }
    
    
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    
    public String getDateBirth(){
        return dateBirth;
    }
    public void setDateBirth(String dateBirth){
        this.dateBirth = dateBirth;
    }
    
    
    public String getEnrolled(){
        return enrolled;
    }
    public void setEnrolled(String enrolled){
        this.enrolled = enrolled;
    }
    
    
}
