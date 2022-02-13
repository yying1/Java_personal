<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>
<html>
<head>
    <title>Project 1 Task 3 | Frank Yue Ying | yying2@</title>
</head>
<body>
<h1><%= "Distributed Systems Class Clicker" %>
</h1>
<% if (request.getAttribute("option_D").equals("false") && request.getAttribute("option_C").equals("false") && request.getAttribute("option_B").equals("false") && request.getAttribute("option_A").equals("false")) { %>
<% } else {%>
<p><%= "The results from the survey are as follows: " %></p><br/>
<% } %>
<% if (request.getAttribute("option_A").equals("true")) { %>
<p>A: <%= request.getAttribute("count_A")%></p><br/>
<% } %>
<% if (request.getAttribute("option_B").equals("true")) { %>
<p>B: <%= request.getAttribute("count_B")%></p><br/>
<% } %>
<% if (request.getAttribute("option_C").equals("true")) { %>
<p>C: <%= request.getAttribute("count_C")%></p><br/>
<% } %>
<% if (request.getAttribute("option_D").equals("true")) { %>
<p>D: <%= request.getAttribute("count_D")%></p><br/>
<% } %>
<% if (request.getAttribute("option_D").equals("false") && request.getAttribute("option_C").equals("false") && request.getAttribute("option_B").equals("false") && request.getAttribute("option_A").equals("false")) { %>
<p>There are currently no results.</p><br>
<% } else { %>
<p>These results have now been cleared.</p><br>
<% } %>
<br/>
</body>
</html>