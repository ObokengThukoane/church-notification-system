<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.webfish.NotificationStore"%>
<%@page import="util.Html"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>City Church Home</title>
    <link rel="stylesheet" href="assets/css/citychurch.css">
</head>
<body>
    <div class="top-bar"></div>
    <main class="page-shell">
        <h1 class="app-title">City Church</h1>
        <h2 class="page-title">Home</h2>

        <div class="home-action">
            <a class="nav-button" href="notification.jsp">View Notifications</a>
        </div>

        <ul class="notification-list" id="notifications">
            <%
                for (String message : NotificationStore.getMessages()) {
            %>
                <li><%= Html.escape(message) %></li>
            <%
                }
            %>
        </ul>
    </main>

    <script>
        var protocol = window.location.protocol === "https:" ? "wss://" : "ws://";
        var socket = new WebSocket(protocol + window.location.host + "<%= request.getContextPath() %>/notifications");
        var list = document.getElementById("notifications");

        socket.onmessage = function (event) {
            var item = document.createElement("li");
            item.textContent = event.data;
            list.appendChild(item);
        };
    </script>
</body>
</html>
