/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.interfaces;

import java.util.List;

/**
 *
 * @author JcarlosAd7
 */
public interface CrudPaginationInterface<T> {
   public List<T> list(String texto,int totalPorPagina,int numPagina);
   public boolean insert(T obj);
   public boolean update(T obj);
   public boolean delete(int id);
   public int total();
   public boolean exists(String texto);
}
