package servlet;

import com.mycompany.webfish.NotificationStore;
import java.io.IOException;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sendNotification")
public class SendNotificationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(lookup = "jms/ChurchConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/ChurchNotificationQueue")
    private Queue notificationQueue;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String role = (String) request.getSession().getAttribute("role");
        if (!"churchLeader".equals(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Only Church Leaders can send notifications.");
            return;
        }

        String message = request.getParameter("message");
        if (message == null || message.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Notification message is required.");
            return;
        }

        String notification = message.trim();
        NotificationStore.addMessage(notification);

        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(notificationQueue, notification);
        }

        response.sendRedirect("home.jsp");
    }
}
