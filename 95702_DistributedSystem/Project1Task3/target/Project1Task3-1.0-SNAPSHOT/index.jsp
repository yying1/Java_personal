<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% if (request.getAttribute("doctype") != null) { %>
<%= request.getAttribute("doctype") %>
<% } else { %>
<!DOCTYPE html>
<% } %>
<html>
<head>
    <title>Project 1 Task 3 | Frank Yue Ying | yying2@</title>
</head>
<body>
<h1><%= "Distributed Systems Class Clicker" %>
</h1>
<% if (request.getAttribute("selected_option") != null) { %>
<p>Your "${selected_option}" response has been registered</p>
<% } %>
<p><%= "Submit your answer to the current question: " %></p>
<br/>
<form action="submit" method="GET">
    <input type="radio" id="A" name="option" value="A">
    <label for="A">A</label><br>
    <input type="radio" id="B" name="option" value="B">
    <label for="B">B</label><br>
    <input type="radio" id="C" name="option" value="C">
    <label for="C">C</label><br>
    <input type="radio" id="D" name="option" value="D">
    <label for="D">D</label><br>
    <br/>
    <button type="submit" value="Submit">Submit</button>
</form>
</body>
</html>