/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author user
 */
public interface CrudOperations<T> {

    boolean add(T obj);

    boolean update(T obj);

    boolean delete(String id);

}
