<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%
    String role = (String) session.getAttribute("role");
    if (!"churchLeader".equals(role)) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Only Church Leaders can access notifications.");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>City Church Notifications</title>
    <link rel="stylesheet" href="assets/css/citychurch.css">
</head>
<body>
    <div class="top-bar"></div>
    <main class="page-shell">
        <h1 class="app-title">City Church Notification System</h1>
        <h2 class="form-title">Send Notification</h2>

        <form class="center-form" action="sendNotification" method="post">
            <textarea class="message-box" name="message" rows="5" placeholder="Write announcement" required></textarea>

            <div class="button-stack">
                <button class="primary-button" type="submit">Send</button>
                <a class="secondary-button" href="home.jsp">Home</a>
            </div>
        </form>
    </main>
</body>
</html>
