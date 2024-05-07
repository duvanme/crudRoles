<%-- 
    Document   : mainView
    Created on : Mar 4, 2024, 4:35:04 PM
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
        <title>JSP Page</title>
    </head>
    <body>

        <!-- NAVBAR -->
        <nav class="navbar navbar-expand-lg bg-primary">

            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <!-- SIGN OUT BUTTON -->
                <div>
                <a href="changePass.jsp" class="btn btn-success">Change password</a>
                <a href="logOut">
                    <button type="button" class="btn btn-danger">Sign out</button>
                </a>
                </div>
            </div>
        </nav>

        <div class="container">

            <center><h1 style="margin: 5vh 0"><p>Welcome, <c:out value="${loggedUser.username}"></c:out></p></h1></center>

                <!-- SEARCH BOX -->

                
                <form action="list" class="d-flex" role="search" style="width: 300px; margin-bottom: 50px">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="searchedUser">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>

                <!-- TABLE -->         

                <table class="table">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Username</th>
                            <th scope="col">Email</th>
                            <th scope="col">Role</th>
                            <c:if test="${loggedUser.id_role==1}">
                            <th scope="col">Edit/Delete</th>
                            </c:if>
                            <c:if test="${loggedUser.id_role==2}">
                            <th scope="col">Edit</th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user1" items="${listUser}">
                        <tr>
                            <th scope="row"><c:out value="${user1.id}"></c:out></th>
                            <td><c:out value="${user1.username}"></c:out></td>
                            <td><c:out value="${user1.email}"></c:out></td>
                            <td><c:out value="${user1.role}"></c:out></td>

                                <td>
                                    <a href="showEditForm?id=<c:out value="${user1.id}"></c:out>">
                                        <button type="button" class="btn btn-success">Edit</button>
                                    </a>

                                <c:if test="${loggedUser.id_role==1}">
                                    <a href="delete?id=<c:out value="${user1.id}"></c:out>">
                                            <button type="button" class="btn btn-danger">Delete</button>
                                        </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${loggedUser.id_role==1}">
                <a href="newUserForm"><center><button type="button" class="btn btn-success"><strong> + Add new user</strong></button></center></a>
            </c:if>
        </div>
    </body>
</html>
