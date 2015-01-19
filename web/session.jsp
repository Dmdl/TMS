<%--
    Document   : session
    Created on : Jan 19, 2015, 1:54:51 PM
    Author     : lakmal
--%>

<%@page import="com.lakmal.domain.Course"%>
<%@page import="com.lakmal.dao.CourseDao"%>
<%@page import="com.lakmal.dao.CourseDaoImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.lakmal.domain.Person"%>
<%@page import="com.lakmal.dao.PersonDao"%>
<%@page import="com.lakmal.dao.PersonDaoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String contextPath = request.getContextPath();
    PersonDao personDao = new PersonDaoImpl();
    CourseDao courseDao = new CourseDaoImpl();
    List<Person> persons = personDao.getResourcePersons();
    List<Course> courses = courseDao.getCourses();
%>
<html>
    <head>
        <style type="text/css">
            .fieldset-auto-width {
                display: inline-block;
            }           
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Session</title>
    </head>
    <body>
        <div id="session-div">
            <fieldset class="fieldset-auto-width">
                <legend>Add Session</legend>
                Select Resource Person :
                <select id="pers">
                    <option value="0">Select</option>
                    <%for (Person pe : persons) {%>
                    <option value="<%=pe.getCode()%>"><%=pe.getName()%></option>
                    <%}%>
                </select>
                <br><br>
                Select Course :
                <select id="cos">
                    <option value="0">Select</option>
                    <%for (Course co : courses) {%>
                    <option value="<%=co.getCode()%>"><%=co.getName()%></option>
                    <%}%>
                </select>
                <br>
                <input type="submit" value="Add Session" id="session"/>
            </fieldset>
        </div>
    </body>
</html>
<script src="<%=contextPath%>/javascript/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
    $('#session').click(function() {
        var person = $('#pers').val();
        alert(person);
    });
</script>