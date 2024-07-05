<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Guess The Number</title>
<style>
  body {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    font-family: Arial, sans-serif;
  }
  .container {
    background-color: aliceblue;
    text-align: center;
    border: 1px solid #a70505;
    padding: 20px;
    box-shadow: 0px 0px 10px rgba(42, 3, 3, 0.1);
    border-radius: 10px;
  }
  input[type="number"] {
    display: block;
    margin: 10px auto;
    padding: 10px;
    width: 58%;
    box-sizing: border-box;
  }
  input[type="submit"] {
  background-color:blue;
  color:white;
    display: block;
    margin: 10px auto;
    padding: 10px 20px;
    cursor: pointer;
    border-radius:5px;
  }
</style>
</head>
<body>
<% String msg=(String)request.getAttribute("msg"); %>
  <div class="container">
     <h1 style="color:green;">Guess the Number Game</h1>
    <p>I have selected a number between 1 and 100. Try to guess it!</p>
     <p style="color:red;"><Strong><% out.print(msg); %></Strong></p>
    <form action="result" method="post">
      <label>Enter your guess:</label>
      <input type="number" name="num" required>
      <input  type="submit" name="Submit" value="Submit">
    </form>
  </div>
</body>
</html>

