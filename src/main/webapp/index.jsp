<%-- 
    Document   : login
    Created on : Mar 4, 2024, 4:17:51 PM
    Author     : duvan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>Sign in</title>
        <style>
            .container {
                width:40vw;
            }
            h1{
                margin-top: 5vh;
            }
            form {
                background-color: #387ADF;
                margin-top: 5vh;
                padding:3vw;
                color: whitesmoke;
                font-weight: bolder;
                border-radius: 50px;
            }
        </style>
    </head>
    <body style="background-color: whitesmoke;">
        <div class="container">
            <!--  TITLE -->
            <center><h1>Welcome. Enter your credentials!</h1></center>

            <!--  LOGIN FORM -->

            <form action="SvUser" method="POST">
                <!--  EMAIL/USERNAME -->
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username">
                </div>
                <!--  PASSWORD -->
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <!--  SUBMIT BUTTON -->
                <center>
                    <button type="submit" class="btn btn-success">Sign in</button>
                </center>
            </form>

        </div>

    </body>
</html>
    