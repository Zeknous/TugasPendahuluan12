/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.util.ArrayList;
/**
 *
 * @author Lenovo
 */
public interface Repository <N> {
    ArrayList<N> getAll();
    
    int edit(N updatedModel);
    
    int insert(N newModel);
    
    int delete(int idModel);
}
