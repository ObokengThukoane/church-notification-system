# City Church Notification System

A Java EE 8 web app for a church community. Members register and log in, and church leaders send announcements that show up instantly on every member's home page without a refresh.

Built for the Enterprise Java module (ITEJA3-33) at Eduvos, 2026.

## How a notification travels

```
Church leader submits form
        │
        ▼
SendNotificationServlet  ── checks the session role, returns 403 if not a leader
        │  sends a JMS TextMessage
        ▼
jms/ChurchNotificationQueue   (GlassFish JMS)
        │
        ▼
NotificationListener (message-driven bean)
        │
        ▼
NotificationEndpoint (WebSocket /notifications)  ── pushes to every open browser
        │
        ▼
home.jsp adds the message to the list live
```

Sending through a JMS queue means the servlet answers the leader straight away, and delivery to members happens asynchronously in the message-driven bean.

## Features

- Registration and login with servlets and JSP, using two roles: `member` and `churchLeader`
- Role-based access: only church leaders can open the send page or post a notification (others get HTTP 403)
- Real-time delivery with JMS and WebSockets (JSR 356)
- A second WebSocket endpoint (`/draw`) that relays messages between connected clients
- Proper HTTP status codes for failures: 400 for missing fields, 401 for bad logins, 409 for duplicate usernames

## Security

- Passwords are hashed with PBKDF2 (HMAC-SHA256, 120,000 iterations, random salt per user) and never stored in plain text
- Notifications and usernames are HTML-escaped before display to block script injection (XSS)
- The session is replaced on login to prevent session fixation

## Tech

Java 8, Java EE 8 (Servlets 4, JSP, JMS 2, EJB message-driven beans, WebSocket, CDI), GlassFish 5, Maven

## Running it

1. Install GlassFish 5 (or Payara 5) and JDK 8.
2. Build: `mvn package`
3. Deploy `target/webFish-1.0-SNAPSHOT.war`. The JMS connection factory and queue are created from `WEB-INF/glassfish-resources.xml`.
4. Open `http://localhost:8080/webFish/`

## Limitations

Users and notifications are kept in memory, so they reset when the server restarts. The next step would be storing them in a database with JPA.

## Author

Obokeng Khumo Thukoane, [github.com/ObokengThukoane](https://github.com/ObokengThukoane)
