<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<! –– https://github.com/CMU-Heinz-95702/Project1 ––>
<!DOCTYPE html>
<html>
<head>
    <title>Project 1 Task 1 | Frank Yue Ying | yying2@</title>
</head>
<body>
<h1><%= "Choose your hash method:" %>
</h1>

<form action="getComputeHashes" method="GET">
    <input type="radio" id="MD5" name="hash_function" value="MD5" checked="checked">
    <label for="MD5">MD5</label><br>
    <input type="radio" id="SHA-256" name="hash_function" value="SHA-256">
    <label for="SHA-256">SHA-256</label><br>
    <label for="input">String:</label><br>
    <input type="text" id="input" name="input"><br>
    <input type="submit" value="Submit">
</form>
<br/>
</body>
</html>