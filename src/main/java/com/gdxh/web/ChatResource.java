package com.gdxh.web;

import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;
import org.atmosphere.cpr.BroadcasterCache;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 * Simple chat resource demonstrating the power of Atmosphere. This resource supports transport like WebSocket, Streaming, JSONP and Long-Polling.
 *
 * @author Jeanfrancois Arcand
 */
@Path("/")
public class ChatResource {

    /**
     * Suspend the response without writing anything back to the client.
     *
     * @return a white space
     */
    @Suspend(contentType = "application/json")
    @GET
    public String suspend() {
        return "";
    }

    /**
     * Broadcast the received message object to all suspended response. Do not write back the message to the calling connection.
     *
     * @param message a {@link Message}
     * @return a {@link Response}
     */
    @Broadcast(writeEntity = false)
    @POST
    @Produces("application/json")
    public Response broadcast(Message message) {
        return new Response(message.author, message.message);
    }

}
