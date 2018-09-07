<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../../ExpenseAppWeb/css/landingpage.css">
    <link rel="stylesheet" href="../../ExpenseAppWeb/css/grid.css">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <title>Landing Page</title>
</head>

<body>
	
    <div class="card-container col-sm-12 col-md-10 col-lg-8">
        <div class="login-signup-container">
            <div class="login-container">
                <h2>Login</h2>
                <form name="login-form" onsubmit="return loginFormValidate();" action="../../ExpenseAppWeb/LoginServlet" method="post">
                    <div class="form-container">
                        <input type="text" name="username" placeholder="Enter your username.." class="username-input">
                        <input type="password" name="password" placeholder="Enter your password.." class="password-input">
                        <input type="submit" name="submit" value="Login" class="submit-button">
                    </div>
                </form>
                <div class="login-circle-button" onclick="loginOnclick()"></div>
            </div>
            <div class="signup-container">
                <h2>Signup</h2>
                <form name="signup-form" onsubmit="return signupFormValidate();" action="../../ExpenseAppWeb/SignUpServlet" method="post">
                    <div class="form-container">
                        <input type="text" name="username" placeholder="Enter a username.." class="username-input">
                        <input type="email" name="email-id" placeholder="Enter your email-id.." class="email-input">
                        <input type="password" name="password" placeholder="Enter a password.." class="password-input">
                        <input type="password" name="confirm-password" placeholder="Confirm your password.." class="password-input">
                        <input type="submit" name="submit" value="Signup" class="submit-button">
                    </div>
                </form>
                <div class="signup-circle-button" onclick="signupOnclick()"></div>
            </div>
        </div>
        <p><%= request.getAttribute("message") %></p>
    </div>
    
    <script src="../../ExpenseAppWeb/js/landingpage.js"></script>
</body>

</html>