<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>City Church Registration</title>
    <link rel="stylesheet" href="assets/css/citychurch.css">
</head>
<body>
    <div class="top-bar"></div>
    <main class="page-shell">
        <h1 class="app-title">City Church Notification System</h1>
        <h2 class="form-title">Registration</h2>

        <form class="center-form" action="register" method="post">
            <input class="form-field" type="text" name="username" placeholder="Username" required>
            <input class="form-field" type="password" name="password" placeholder="Password" required>
            <select class="form-select" name="role" required>
                <option value="member">Member</option>
                <option value="churchLeader">Church Leader</option>
            </select>

            <div class="button-stack">
                <button class="primary-button" type="submit">Register</button>
                <a class="secondary-button" href="login.jsp">Login</a>
            </div>
        </form>
    </main>
</body>
</html>
