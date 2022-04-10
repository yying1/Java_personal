<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>test</title>
</head>
<body>
<h1><%= "FPL Dashboard" %>
</h1>
<p><%= "Here is the most recent request made and the result in this session: " %></p>
<br/>
<!-- Referenced from https://stackoverflow.com/questions/51317062/creating-an-html-table-in-java -->
<table border="1">
<tr><th>Request Time</th><th>Action Type</th><th>Player 1</th><th>Player 2</th><th>Result</th><th>Duration</th></tr>
    <tr><th><%= request.getAttribute("time")%></th>
    <th><%= request.getAttribute("action")%></th>
    <th><%= request.getAttribute("p1")%></th><th><%= request.getAttribute("p2")%></th>
    <th><%= request.getAttribute("result")%></th>
    <th><%= request.getAttribute("duration")%></th></tr>
</table>
<br/>
<p><%= "Here is the summary of all requests: " %></p>
<p style="margin-left: 40px"><%= "Total requests made: "+request.getAttribute("total_count") %></p>
<p style="margin-left: 40px"><%= "Total requests made today: "+request.getAttribute("today_count") %></p>
<p style="margin-left: 40px"><%= "Total search requests made: "+request.getAttribute("search_count") %></p>
<p style="margin-left: 40px"><%= "Total compare requests made: "+request.getAttribute("compare_count") %></p>
<p style="margin-left: 40px"><%= "Average request duration (seconds): "+request.getAttribute("avg_duration") %></p>
</body>
</html>