/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.dao;

import com.lakmal.domain.Course;
import java.util.List;

/**
 *
 * @author lakmal
 */
public interface CourseDao {

    public int save(Course course);

    public List<Course> getCourses();
}
