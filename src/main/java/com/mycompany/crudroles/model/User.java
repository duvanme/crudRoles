/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudroles.model;

/**
 *
 * @author duvan
 */
public class User {
     //attributes
    protected int id;
    protected String username;
    protected String email;
    protected String password;
    protected int id_role;
    protected String role;
    
    //Constructors
    public User() {
        
    }
    
    public User(int id) {
        this.id = id;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(int id_role, String password) {
        this.id_role = id_role;
        this.password = password;
    }
       
    public User(String username, String email, String password, int id_role, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id_role = id_role;
        this.role = role;
    }
    
    
      public User(String username, String email, String password, int id_role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id_role = id_role;
    }
      
       public User(String username, String email, int id_role) {
        this.username = username;
        this.email = email;
        this.id_role = id_role;
    }
      
      
       public User(int id,String username, String email, int id_role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.id_role = id_role;
    }
    
    
    public User(int id, String username, String email, String password, int id_role, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.id_role = id_role;
        this.role = role;
    }
    
    public User(int id, String username, String email, int id_role, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.id_role = id_role;
        this.role = role;
    }
    
    
    // getters and setters 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
