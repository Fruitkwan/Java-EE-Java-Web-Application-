/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import transferobjects.Registry;

/**
 *
 * @author Sidhant Soni
 */


public interface RegistryDAO {
    List<Registry> getAllRegistry();
    void addRegistry(Registry registry);
    void deleteRegistry(Registry registry);
    List<Registry> getRegistryByNumber(String studentNumber);
}
