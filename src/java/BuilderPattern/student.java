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
public class student {
    
    
    
    private String studentNumber;
	private String firstName;
	private String lastName;
	private String datebirth;
	

	public String getStudentNum(){ return studentNumber; }
	public void setStudentID(int personID){this.studentNumber = studentNumber; }

	public String getFirstName(){ return firstName; }
	public void setLirstName(String firstName){this.firstName = firstName;}

	public String getLastName(){ return lastName; }
	public void setLastName(String lastName){this.lastName = lastName;}

	public String getDatebirth(){ return datebirth; }
	public void setDateBirth(String phone){this.datebirth = datebirth;}

	

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[")
		.append(firstName).append(" ")
		.append(lastName).append(" ")
		.append(datebirth).append("]"); // .append(System.lineSeparator());
		return sb.toString();
	}

	
	public student(StudentBuilder builder)
	{
		studentNumber = builder.getStudentNum();
		firstName = builder.getFirstName();
		lastName = builder.getLastName();
		datebirth = builder.getDateBirth();
		
	}

	
	public student(String studentID, String firstName, String lastName, String datebirth){
		super();
		this.studentNumber = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.datebirth = datebirth;
        }	

	public student(String studentNumber, String firstName, String lastName){ 
		this(studentNumber, firstName, lastName, null);
	}

	public student(String studentID, String firstName){
		this(studentID, firstName, null, null);
	}

	public student(String studentID){
		this(studentID,  null, null, null);
	}

	public student(){ 
		this(null, null, null, null);
	}
}
