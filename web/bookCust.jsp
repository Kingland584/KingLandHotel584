<%-- 
    Document   : bookCust
    Created on : Jan 8, 2024, 1:02:26 AM
    Author     : Sarah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sql:setDataSource var="myDatasource" driver="org.apache.derby.jdbc.ClientDriver" url="jdbc:derby://localhost:1527/KingLandHotel" user="app" password="app"/>






<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Customer Booking Form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="author" content="" />
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=|Roboto+Sans:400,700|Playfair+Display:400,700">

    <link rel="stylesheet" href="customerPage/css/bootstrap.min.css">
    <link rel="stylesheet" href="customerPage/css/animate.css">
    <link rel="stylesheet" href="customerPage/css/owl.carousel.min.css">
    <link rel="stylesheet" href="customerPage/css/aos.css">
    <link rel="stylesheet" href="customerPage/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="customerPage/css/jquery.timepicker.css">
    <link rel="stylesheet" href="customerPage/css/fancybox.min.css">
    
    <link rel="stylesheet" href="customerPage/fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="customerPage/fonts/fontawesome/css/font-awesome.min.css">

    <!-- Theme Style -->
    <link rel="stylesheet" href="customerPage/css/style.css">

   
          
</head>

  
  <body>
      
       <c:set var="custFName" value="${param.custFName}"/> 
       <c:set var="custLName" value="${param.custLName}"/> 
       <c:set var="custPhoneNumber" value="${param.custPhoneNumber}"/> 
       <c:set var="custEmail" value="${param.custEmail}"/> 
        <c:set var="checkInDate" value="${param.checkInDate}"/> 
        <c:set var="checkOutDate" value="${param.checkOutDate}"/>
        <c:set var="roomType" value="${param.roomType}"/>
        <c:set var="paymentMethod" value="${param.paymentMethod}"/>
        
        <c:if test="${(custFName!=null)&&(custLName!=null)&&(custPhoneNumber!=null)&&(custEmail!=null)&&(checkInDate!=null)&&(checkOutDate!=null)&&(roomType!=null)&&(paymentMethod!=null)}" var="result">
            <sql:update var="result" dataSource="${myDatasource}">
                INSERT INTO CUSTOMER (name, email, checkInDate, checkOutDate)
                VALUES (?, ?, ?, ?)
                
                <sql:param value="${custFName}"/>
                <sql:param value="${custLName}"/>
                <sql:param value="${custPhoneNumber}"/>
                <sql:param value="${custEmail}"/>
                
             </sql:update>
                
            <sql:update var="result" dataSource="${myDatasource}">
                INSERT INTO BOOKING (checkInDate, checkOutDate)
                VALUES (?, ?)
                
                <sql:param value="${checkInDate}"/>
                <sql:param value="${checkOutDate}"/>
                
                
            </sql:update>
                
            <sql:update var="result" dataSource="${myDatasource}">
                INSERT INTO ROOM (roomType)
                VALUES (?)
                
                <sql:param value="${roomType}"/>
                
                
            </sql:update>
                
            <sql:update var="result" dataSource="${myDatasource}">
                INSERT INTO PAYMENT (paymentMethod)
                VALUES (?)
                
                <sql:param value="${paymentMethod}"/>
                
                
            </sql:update>

        </c:if>
    
    <header class="site-header js-site-header">
      <div class="container-fluid">
        <div class="row align-items-center">
          <div class="col-6 col-lg-4 site-logo" data-aos="fade"><a href="homeCust.html">King Land Hotel</a></div>
          <div class="col-6 col-lg-8">


            <div class="site-menu-toggle js-site-menu-toggle"  data-aos="fade">
              <span></span>
              <span></span>
              <span></span>
            </div>
            <!-- END menu-toggle -->

            <div class="site-navbar js-site-navbar">
              <nav role="navigation">
                <div class="container">
                  <div class="row full-height align-items-center">
                    <div class="col-md-6 mx-auto">
                      <ul class="list-unstyled menu">
                        <li><a href="homeCust.html">Home</a></li>
                        <li><a href="rooms.html">Rooms</a></li>
                        <li><a href="contact.html">Contact</a></li>
                        <li class="active"><a href="booking.html">Booking</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
              </nav>
            </div>
          </div>
        </div>
      </div>
    </header>
    <!-- END head -->

    <section class="site-hero inner-page overlay" style="background-image: url(customerPage/images/hero_4.jpg)" data-stellar-background-ratio="0.5">
      <div class="container">
        <div class="row site-hero-inner justify-content-center align-items-center">
          <div class="col-md-10 text-center" data-aos="fade">
            <h1 class="heading mb-3">Booking Form</h1>
            <ul class="custom-breadcrumbs mb-4">
              <li><a href="homeCust.jsp">Home</a></li>
              <li>&bullet;</li>
              <li>Booking</li>
            </ul>
          </div>
        </div>
      </div>

      <a class="mouse smoothscroll" href="#next">
        <div class="mouse-icon">
          <span class="mouse-wheel"></span>
        </div>
      </a>
    </section>
    <!-- END section -->

    <section class="section contact-section" id="next">
      <div class="container">
        <div class="row">
          <div class="col-md-7" data-aos="fade-up" data-aos-delay="100">
            
            <form action="bookCustServlet" method="post" class="bg-white p-md-5 p-4 mb-5 border">
             

              
               <div class="col-md-6 form-group">
                <label class="text-black font-weight-bold" for="custFName">First Name</label>
                <input type="text" id="custFName" class="form-control">
               </div>
                <div class="col-md-6 form-group">
                <label class="text-black font-weight-bold" for="custLName">Last Name</label>
                <input type="text" id="custLName" class="form-control">
                </div>
            
                <div class="col-md-6 form-group">
                  <label class="text-black font-weight-bold" for="custPhoneNumber">Phone</label>
                  <input type="text" id="custPhoneNumber" class="form-control ">
                </div>
              </div>
          
              <div class="row">
                <div class="col-md-12 form-group">
                  <label class="text-black font-weight-bold" for="custEmail">Email</label>
                  <input type="email" id="custEmail" class="form-control ">
                </div>
              </div>

              <div class="row">
                <div class="col-md-6 form-group">
                  <label class="text-black font-weight-bold" for="checkInDate">Date Check In</label>
                  <input type="text" id="checkInDate" class="form-control">
                </div>
                <div class="col-md-6 form-group">
                  <label class="text-black font-weight-bold" for="checkOutDate">Date Check Out</label>
                  <input type="text" id="checkOutDate" class="form-control">
                </div>
              </div>

             

              <!-- type of room -->
              <div class="row">
  <div class="col-md-6 form-group">
    <label class="text-black font-weight-bold" for="roomType">Room Type</label>

    <div class="custom-control custom-radio">
      <input type="radio" id="standardRoom" name="roomType" class="custom-control-input" value="standard">
      <label class="custom-control-label" for="standardRoom">Standard room</label>
    </div>

    <div class="custom-control custom-radio">
      <input type="radio" id="deluxeRoom" name="roomType" class="custom-control-input" value="deluxe">
      <label class="custom-control-label" for="deluxeRoom">Deluxe Room</label>
    </div>

    <div class="custom-control custom-radio">
      <input type="radio" id="superiorRoom" name="roomType" class="custom-control-input" value="superior">
      <label class="custom-control-label" for="superiorRoom">Superior Room</label>
    </div>

    <div class="custom-control custom-radio">
      <input type="radio" id="superiorKing" name="roomType" class="custom-control-input" value="superiorKing">
      <label class="custom-control-label" for="superiorKing">Superior King Room</label>
    </div>
  </div>
</div>


              <!-- Add this div for the notification -->
            <div class="row">
              <div class="col-md-12 form-group">
                <div id="roomSelectionNotification" class="text-success"></div>
            </div>
          </div>

             <!-- Payment method -->
<div class="row">
  <div class="col-md-12 form-group">
    <label class="text-black font-weight-bold">Payment Method</label>

    <div class="custom-control custom-radio">
      <input type="radio" id="paymentMethod" name="paymentMethod" class="custom-control-input" value="Credit Card">
      <label class="custom-control-label" for="paymentMethod">Credit Card</label>
    </div>

    <div class="custom-control custom-radio">
      <input type="radio" id="paymentMethod" name="paymentMethod" class="custom-control-input" value="Online Banking">
      <label class="custom-control-label" for="paymentMethod">Online Banking</label>
    </div>
  </div>
</div>


              
              <div class="row">
                <div class="col-md-6 form-group">
                  <input type="submit" value="Booking Now" class="btn btn-primary text-white py-3 px-5 font-weight-bold">
                </div>
              </div>
            </form>

          </div>
         
        </div>
      </div>
    </section>

   

    <footer class="section footer-section">
      <div class="container">
        <div class="row mb-4">
          
          
          <div class="col-md-3 mb-5 pr-md-5 contact-info">
            
            <p><span class="d-block"><span class="ion-ios-location h5 mr-3 text-primary"></span>Address:</span> <span> No 11 Jalan Desa Aman 9 Taman Desa Aman 5Th Mile <br>Kuala Lumpur 56100</span></p>
            <p><span class="d-block"><span class="ion-ios-telephone h5 mr-3 text-primary"></span>Phone:</span> <span> 03-2274-5627</span></p>
            <p><span class="d-block"><span class="ion-ios-email h5 mr-3 text-primary"></span>Email:</span> <span> kinglandhotel@gmail.com</span></p>
          </div>
          
        </div>
        <div class="row pt-5">
          <p class="col-md-6 text-left">
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved.
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
          </p>
            
          
        </div>
      </div>
    </footer>
    
    <script src="customerPage/js/jquery-3.3.1.min.js"></script>
    <script src="customerPage/js/jquery-migrate-3.0.1.min.js"></script>
    <script src="customerPage/js/popper.min.js"></script>
    <script src="customerPage/js/bootstrap.min.js"></script>
    <script src="customerPage/js/owl.carousel.min.js"></script>
    <script src="customerPage/js/jquery.stellar.min.js"></script>
    <script src="customerPage/js/jquery.fancybox.min.js"></script>
    
    
    <script src="customerPage/js/aos.js"></script>
    
    <script src="customerPage/js/bootstrap-datepicker.js"></script> 
    <script src="customerPage/js/jquery.timepicker.min.js"></script> 

    

    <script src="customerPage/js/main.js"></script>
 
    </body>
</html>
