/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.dao;

import com.lakmal.domain.Person;
import java.util.List;

/**
 *
 * @author lakmal
 */
public interface PersonDao {

    public int save(Person person);

    public List<Person> getResourcePersons();
}
