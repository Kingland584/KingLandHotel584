<%-- 
    Document   : addRoom
    Created on : Jan 5, 2024, 3:46:57 PM
    Author     : mohd azrin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sql:setDataSource var="myDatasource" driver="org.apache.derby.jdbc.ClientDriver" url="jdbc:derby://localhost:1527/KingLandHotel" user="app" password="app"/>

<!DOCTYPE html>
<html>
<head>
    <title>Add Room Page</title>
    <link rel="stylesheet" href="adminPage/css/dashcss.css" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <style>
        .scrollable-container {
            max-height: 600px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <!--
    <br>
    <center>
        <span style="font-size:50px;cursor:pointer; color: white; text-align:center;" class="nav">Add Room Form</span>
    </center>
    -->
        <c:set var="roomID" value="${param.roomID}"/>      
        <c:set var="roomNum" value="${param.roomNum}"/> 
        <c:set var="roomStatus" value="${param.roomStatus}"/>
        <c:set var="roomType" value="${param.roomType}"/> 
        
        <c:if test="${(roomID!=null)&&(roomNum!=null)&&(roomStatus!=null)&&(roomType!=null)}" var="result">
            <sql:update var="result" dataSource="${myDatasource}">
                INSERT INTO ROOM (ROOMID, ROOMNUMBER, ROOMSTATUS, ROOMTYPE) VALUES (?,?,?,?)
                <sql:param value="${roomID}"/>    
                <sql:param value="${roomNum}"/>
                <sql:param value="${roomStatus}"/>
                <sql:param value="${roomType}"/> 
            </sql:update>
        </c:if>


        
    <div class="scrollable-container">
        <div class="formbold-main-wrapper">
            <div class="formbold-form-wrapper">
                <form action="AddRoomServlet" method="POST">
                    <input type="hidden" name="action" value="Add"/>
                    <div class="formbold-mb-3">
                        <label for="roomID" class="formbold-form-label">Room ID</label>
                        <input type="text" name="roomID" id="roomID" class="formbold-form-input" />
                    </div>
                    <div class="formbold-mb-3">
                        <label for="roomNum" class="formbold-form-label">Room Number</label>
                        <input type="text" name="roomNum" id="roomNum" class="formbold-form-input" />
                    </div>
                    <div class="formbold-mb-3">
                        <label for="roomStatus" class="formbold-form-label">Room Status</label>
                        <select name="roomStatus" id="roomStatus" class="formbold-form-input">
                            <option selected>-- Choose Status --</option>
                            <option value="Available">Available</option>
                            <option value="Reserved">Reserved</option>
                            <option value="Occupied">Occupied</option>
                        </select>
                    </div>
                    <div class="formbold-mb-3">
                        <label for="roomType" class="formbold-form-label">Room Type</label>
                        <select name="roomType" id="roomType" class="formbold-form-input">
                            <option selected>-- Choose Room --</option>
                            <option value="Standard">Standard Room</option>
                            <option value="Deluxe">Deluxe Room</option>
                            <option value="Superior">Superior Room</option>
                            <option value="SuperiorKing">Superior King Room</option>
                        </select>
                    </div>
                    <button class="formbold-btn" type="submit" value="Save">Submit</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
