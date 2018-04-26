/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuilderPattern;

/**
 *
 * @author Sidhant Soni
 */
public class StudentBuilder {
    
        private String studentNumber;
	private String firstName;
	private String lastName;
	private String dateBirth;
	

	public String getStudentNum(){ 
            return studentNumber;
        }
	public String getFirstName(){ 
            return firstName; 
        }
	public String getLastName(){
            return lastName;
        }
	public String getDateBirth(){ 
            return dateBirth; 
        }
	

	private StudentBuilder() { }

	
	public static StudentBuilder create() { 
           return new StudentBuilder(); 
        }

	
	public StudentBuilder StudentNum (String studentNumber)     { 
            this.studentNumber = studentNumber;  return this;
        }
	public StudentBuilder firstName(String firstName) { 
            this.firstName = firstName; return this; 
        }
	public StudentBuilder lastName (String lastName)  {
            this.lastName = lastName;   return this;
        }
	public StudentBuilder dateBirth    (String datebirth) { 
            this.dateBirth = datebirth; return this;
        }
	

	
	public student build() {
            return new student(this); 
        }
}
