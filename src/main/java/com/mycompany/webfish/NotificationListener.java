package com.mycompany.webfish;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/ChurchNotificationQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NotificationListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String notification = ((TextMessage) message).getText();
                NotificationEndpoint.broadcastMessage(notification);
            }
        } catch (JMSException ex) {
            throw new IllegalStateException("Unable to read JMS notification", ex);
        }
    }
}
