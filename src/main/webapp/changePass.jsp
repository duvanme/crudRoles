<%-- 
    Document   : form
    Created on : Mar 4, 2024, 8:59:30 PM
    Author     : duvan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>User form</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-primary">

            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <!-- SIGN OUT BUTTON -->
                <a href="logOut">
                    <button type="button" class="btn btn-danger">Sign out</button>
                </a>
            </div>
        </nav>


    <center><h1><c:out value="${loggedUser.username}"></c:out></h1></center>

 


    <div class="container">

        <form action="changePass" method="POST">

            
            <div class="form-group">
                <label for="newPassword">New password</label>
                <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="Enter Username">
            </div>
            
            <br>
            
            <div class="form-group">
                <label for="confirmPassword">Confirm password</label>
                <input type="password" class="form-control" id="email" placeholder="Email" name="confirmPassword" id="confirmPassword">
            </div>

            <br>

            <input type="hidden" name="id" value="${loggedUser.id}">

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    
         <c:if test="${change == false}">
             <p>Passwords do not match</p>
        </c:if>
    </div>        
</body>
</html>
