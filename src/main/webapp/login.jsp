<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>City Church Login</title>
    <link rel="stylesheet" href="assets/css/citychurch.css">
</head>
<body>
    <div class="top-bar"></div>
    <main class="page-shell">
        <h1 class="app-title">City Church Notification System</h1>
        <h2 class="form-title">Login</h2>

        <form class="center-form" action="login" method="post">
            <input class="form-field" type="text" name="username" placeholder="Username" required>
            <input class="form-field" type="password" name="password" placeholder="Password" required>

            <div class="button-stack">
                <button class="primary-button" type="submit">Login</button>
                <a class="secondary-button" href="registration.jsp">Register</a>
            </div>
        </form>
    </main>
</body>
</html>
