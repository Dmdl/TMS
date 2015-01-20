<%--
    Document   : index
    Created on : Jan 19, 2015, 9:53:56 AM
    Author     : lakmal
--%>

<%@page import="java.util.List"%>
<%@page import="com.lakmal.domain.Course"%>
<%@page import="com.lakmal.dao.CourseDaoImpl"%>
<%@page import="com.lakmal.dao.CourseDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String contextPath = request.getContextPath();
    CourseDao courseDao = new CourseDaoImpl();
    List<Course> courses = courseDao.getCourses();
%>
<html>
    <head>
        <style type="text/css">
            .fieldset-auto-width {
                width: 60%;
                display: inline-block;
            }
            #ui-datepicker-div { font-size: 12px; }
        </style>
        <script language="JavaScript" type="text/JavaScript" src="<%=contextPath%>/javascript/datepicker/jquery.ui.core.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="<%=contextPath%>/javascript/datepicker/jquery.ui.widget.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="<%=contextPath%>/javascript/datepicker/jquery.ui.datepicker.js"></script>
        <script src="<%=contextPath%>/javascript/jquery-1.10.2.min.js"></script>
        <script src="<%=contextPath%>/javascript/jquery-ui.min.js"></script>
        <script src="<%=contextPath%>/javascript/jquery.timepicker.min.js"></script>
        <link rel="stylesheet" href="<%=contextPath%>/css/jquery-ui.min.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/jquery.timepicker.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Person</title>
    </head>
    <body>
        <div id="result"></div>
        <div id="person-div">
            <fieldset class="fieldset-auto-width">
                <legend>Add Resource Person</legend>
                Person Code : <input type="text" id="code" name="code"/>
                Person Name : <input type="text" id="name" name="name"/><br>
                <input type="submit" value="Add Person" id="person"/>
            </fieldset>
        </div><br>
        <div id="course-div">
            <fieldset class="fieldset-auto-width">
                <legend>Add Course</legend>
                Course Code : <input type="text" id="c-code" name="code"/>
                Course Name : <input type="text" id="c-name" name="name"/><br>
                <input type="submit" value="Add Course" id="course"/>
            </fieldset>
        </div>
        <br>
        <div id="session-div">
            <fieldset class="fieldset-auto-width">
                <legend>Add Session</legend>
                Session Code : <input type="text" id="s-code" name="code"/>
                Session Name : <input type="text" id="s-name" name="name"/><br><br>
                Select Course :
                <select id="cos">
                    <option value="0">Select</option>
                    <%for (Course co : courses) {%>
                    <option value="<%=co.getCode()%>"><%=co.getName()%></option>
                    <%}%>
                </select><br><br>
                <!--Date :<input type="text" id="datepicker"><br><br>-->
                Date :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input style="width:120px;" id="datepicker" /><br><br>
                Time From :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input style="width:90px;" type="text" id="fromTime" name="from-time"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                Time To:<input style="width:90px;" type="text" id="toTime" name="from-time"/><br><br>
                <input type="submit" value="Add Session" id="session"/>
            </fieldset>
        </div>
        <br>
        <a href="session">Manage Session</a>
    </body>
</html>



<script type="text/javascript">


    $("#datepicker").datepicker({
        dateFormat: 'yy-mm-dd',
        changeMonth: true,
        changeYear: true,
        showOn: "button"
    });
    $('#person').click(function() {
        var code = $('#code').val();
        var name = $('#name').val();
        $.ajax({
            type: "POST",
            url: 'ProcessData',
            cache: false,
            timeout: "600000",
            dataType: "text",
            data: {action: "save", code: code, name: name},
            success: function(data) {
                $('#result').html(data).show();
                setTimeout(function() {
                    jQuery("#result").hide();
                }, 3000);
                $('#code').val("");
                $('#name').val("");
            }
        });
    });
    $('#course').click(function() {
        var code = $('#c-code').val();
        var name = $('#c-name').val();
        $.ajax({
            type: "POST",
            url: 'ProcessData',
            cache: false,
            timeout: "600000",
            dataType: "text",
            data: {action: "saveCourse", code: code, name: name},
            success: function(data) {
                $('#result').html(data).show();
                setTimeout(function() {
                    jQuery("#result").hide();
                }, 3000);
                $('#c-code').val("");
                $('#c-name').val("");
            }
        });
    });
    $(function() {
        $("#datepicker").datepicker({
            changeMonth: true,
            changeYear: true
        });
    });

    $('#session').click(function() {
        var date = $('#datepicker').val();
        var from = $('#fromTime').val();
        var to = $('#toTime').val();
        var sesCode = $('#s-code').val();
        var sesname = $('#s-name').val();
        var cos = $('#cos').val();

//        alert(date + ' ' + from + ' ' + to + ' ' + sesCode + ' ' + sesname + ' ' + cos);
        $.ajax({
            type: "POST",
            url: 'ProcessData',
            cache: false,
            timeout: "600000",
            dataType: "text",
            data: {action: "saveSession", code: sesCode, name: sesname, course: cos, date: date, fromT: from, toT: to},
            success: function(data) {
                $('#result').html(data).show();
                setTimeout(function() {
                    jQuery("#result").hide();
                }, 3000);
            }
        });
    });

    $('#fromTime').timepicker();
    $('#toTime').timepicker();

</script>