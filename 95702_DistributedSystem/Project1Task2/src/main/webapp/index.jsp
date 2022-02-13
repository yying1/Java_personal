<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Project 1 Task 2 | Frank Yue Ying | yying2@</title>
</head>
<body>
<h1><%= "Dog Finder" %></h1>
<br/>
<h3><%= "Created by Frank Yue Ying (yying2@)" %></h3>
<br/>
<h2><%= "Dog Breeds" %></h2>
<br/>
<form action="getDogBreeds" method="GET">
    <label for="breed"></label>
    <select id="breed" name="breed">
        <option value="Borzoi">Borzoi</option>
        <option value="Boxer">Boxer</option>
        <option value="Chihuahua">Chihuahua</option>
        <option value="Collie">Collie</option>
        <option value="Dachshund">Dachshund</option>
        <option value="Dalmatian">Dalmatian</option>
        <option value="Maltese">Maltese</option>
        <option value="Otterhound">Otterhound</option>
        <option value="Poodle">Poodle</option>
        <option value="Rottweiler">Rottweiler</option>
        <option value="Saluki">Saluki</option>
        <option value="Whippet">Whippet</option>
    </select>

    <input type="submit" value="Submit">
</form>
</body>
</html>