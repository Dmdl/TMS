<%--
    Document   : session
    Created on : Jan 19, 2015, 1:54:51 PM
    Author     : lakmal
--%>

<%@page import="com.lakmal.domain.Session"%>
<%@page import="com.lakmal.dao.SessionDao"%>
<%@page import="com.lakmal.dao.SessionDaoImpl"%>
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
    List<Person> persons = personDao.getResourcePersons();
    SessionDao sessionDao = new SessionDaoImpl();
    List<Session> sessions = sessionDao.getSessions();
%>
<html>
    <head>
        <style type="text/css">
            .fieldset-auto-width {
                width: 50%;
                display: inline-block;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Session Management</title>
    </head>
    <body>
        <div id="result"></div>
        <div id="session-div">
            <fieldset class="fieldset-auto-width">
                <legend>Manage Session</legend>
                Select Resource Person :
                <select id="pers">
                    <option value="0">Select</option>
                    <%for (Person pe : persons) {%>
                    <option value="<%=pe.getCode()%>"><%=pe.getName()%></option>
                    <%}%>
                </select>
                <br><br>
                Select Session :
                <select id="cos">
                    <option value="0">Select</option>
                    <%for (Session co : sessions) {%>
                    <option value="<%=co.getSessionCode()%>"><%=co.getSessionName()%></option>
                    <%}%>
                </select>
                <br><br>
                <input type="submit" value="Manage Session" id="session"/>
            </fieldset>
        </div>
    </body>
</html>
<script src="<%=contextPath%>/javascript/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
    $('#session').click(function() {
        var person = $('#pers').val();
        var cos = $('#cos').val();
        alert(person + ' ' + cos);
        $.ajax({
            type: "POST",
            url: 'ProcessData',
            cache: false,
            timeout: "600000",
            dataType: "text",
            data: {action: "manageSession", person: person, cos: cos},
            success: function(data) {
                $('#result').html(data).show();
                setTimeout(function() {
                    jQuery("#result").hide();
                }, 3000);
            }
        });
    });
</script>