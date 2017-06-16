/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author testautom-nack
 */
public class Author {


    protected Integer id;
    private String firstName;
    private String lastName;
    private String country;
    private String bio;
    
     private List<Author> authors = new ArrayList();

    public Author(int id, String firstName, String lastName, String country, String bio) {

        this.id = id;
        this.country = country;
        this.bio = bio;
        this.firstName = firstName;
        this.lastName = lastName;

    }
//    Author(Authors authors) {
//        this.authors = authors;
//    }
     public Author() {
        
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {

        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
    }


}
