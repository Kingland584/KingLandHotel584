<%-- 
    Document   : AddBooking
    Created on : Jan 5, 2024, 1:02:26 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sql:setDataSource var="myDatasource" driver="org.apache.derby.jdbc.ClientDriver" url="jdbc:derby://localhost:1527/KingLandHotel" user="app" password="app"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Booking</title>
    <link rel="stylesheet" href="adminPage/css/dashcss.css" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <style>
        .scrollable-container {
            max-height: 600px; /* Set the maximum height for the scrollable area */
            overflow-y: auto; /* Add a vertical scrollbar when content overflows */
        }
    </style>
</head>
<body>
        <c:set var="bookingID" value="${param.bookingID}"/> 
        <c:set var="checkInDate" value="${param.checkInDate}"/> 
        <c:set var="checkOutDate" value="${param.checkOutDate}"/>
        <c:set var="custID" value="${param.custID}"/>
        <c:set var="roomID" value="${param.roomID}"/>
        <c:set var="adminID" value="${param.adminID}"/> 
        <c:set var="paymentID" value="${param.paymentID}"/>
        
        <c:if test="${(bookingID!=null)&&(checkInDate!=null)&&(checkOutDate!=null)&&(custID!=null)&&(roomID!=null)&&(adminID!=null)&&(paymentID!=null)}" var="result">
            <sql:update var="result" dataSource="${myDatasource}">
                INSERT INTO BOOKING (bookingID, checkInDate, checkOutDate, custID, roomID, adminID, paymentID)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                
                <sql:param value="${bookingID}"/>
                <sql:param value="${checkInDate}"/>
                <sql:param value="${checkOutDate}"/>
                <sql:param value="${custID}"/>
                <sql:param value="${roomID}"/>
                <sql:param value="${adminID}"/>
                <sql:param value="${paymentID}"/>
            </sql:update>

        </c:if>
                
    <div class="scrollable-container">
        <div class="formbold-main-wrapper">
            <div class="formbold-form-wrapper">
                <form action="AddBookServlet" method="POST">
                    <input type="hidden" name="action" value="Add"/>

                    <div class="formbold-mb-3">
                        <label for="bookingID" class="formbold-form-label">Booking ID</label>
                        <input type="text" name="bookingID" id="bookingID" class="formbold-form-input" />
                    </div>

                    <div class="formbold-mb-3">
                        <label for="checkInDate" class="formbold-form-label">Check-in Date</label>
                        <input type="date" name="checkInDate" id="checkInDate" class="formbold-form-input" />
                    </div>

                    <div class="formbold-mb-3">
                        <label for="checkOutDate" class="formbold-form-label">Check-out Date</label>
                        <input type="date" name="checkOutDate" id="checkOutDate" class="formbold-form-input" />
                    </div>
                    
                    <div class="formbold-mb-3">
                        <label for="custID" class="formbold-form-label">Customer ID</label>
                        <input type="text" name="custID" id="custID" class="formbold-form-input" />
                    </div>
                    
                    <div class="formbold-mb-3">
                        <label for="roomID" class="formbold-form-label">Room ID</label>
                        <input type="text" name="roomID" id="roomID" class="formbold-form-input" />
                    </div>

                    <div class="formbold-mb-3">
                        <label for="adminID" class="formbold-form-label">Admin ID</label>
                        <input type="text" name="adminID" id="adminID" class="formbold-form-input" />
                    </div>

                    <div class="formbold-mb-3">
                        <label for="paymentID" class="formbold-form-label">Payment ID</label>
                        <input type="text" name="paymentID" id="paymentID" class="formbold-form-input" />
                    </div>
                    
                    <button class="formbold-btn">Submit</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
