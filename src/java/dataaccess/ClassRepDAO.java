/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import java.util.List;
import transferobjects.ClassRep;


/**
 *
 * @author Sidhant Soni
 */

public interface ClassRepDAO {
    List<ClassRep> getAllClassRep();
    void addClassRep(ClassRep classRep);
    void deleteClassRep(ClassRep classRep);
    List<ClassRep> getClassRepByNumber(String studentNumber);
}
