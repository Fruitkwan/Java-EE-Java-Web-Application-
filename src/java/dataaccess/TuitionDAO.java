/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import transferobjects.Tuition;



public interface TuitionDAO {
    List<Tuition> getAllTuition();
    void addTuition(Tuition tuition);
    void deleteTuition(Tuition tuition);
    Tuition getTuitionByNumber(String studentNumber);
}
