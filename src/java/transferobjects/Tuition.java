/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transferobjects;



public class Tuition {
    
    private String studentNumber;
    private String paid;
    private String remainder;
    
    public Tuition(){}
    
    
    public Tuition(String studentNumber, String paid, String remainder){
        setStudentNum(studentNumber);
        setPaid(paid);
        setRemainder(remainder);
    }
    
    public String getStudentNum(){
        return studentNumber;
    }
    public void setStudentNum(String studentNumber){
        this.studentNumber = studentNumber;
    }
    
    
    public String getPaid(){
        return paid;
    }
    public void setPaid(String paid){
        this.paid = paid;
    }
    
    
    public String getRemainder(){
        return remainder;
    }
    public void setRemainder(String remainder){
        this.remainder = remainder;
    }
    
    
}
