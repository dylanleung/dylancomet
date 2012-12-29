package com.gdxh.web.config.protocol;

import java.io.IOException;
import java.util.List;

import org.atmosphere.config.service.WebSocketProtocolService;
import org.atmosphere.cpr.AtmosphereConfig;
import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResourceImpl;
import org.atmosphere.websocket.WebSocket;
import org.atmosphere.websocket.WebSocketProcessor;
import org.atmosphere.websocket.WebSocketProtocol;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gdxh.web.config.SpringApplicationContext;
import com.gdxh.web.dto.BaseCommand;
import com.gdxh.web.dto.Command;
import com.gdxh.web.dto.EmptyCommand;
import com.gdxh.web.services.ChatService;

@WebSocketProtocolService
public class DelegatingWebSocketProtocol implements WebSocketProtocol {

    public static final Logger LOG = LoggerFactory.getLogger(DelegatingWebSocketProtocol.class);

    @Override
    public void configure(AtmosphereConfig atmosphereConfig) {
        // nothing needed
    }

    @Override
    public List<AtmosphereRequest> onMessage(WebSocket webSocket, String message) {
        if (webSocket.resource() == null) {
            return null;
        }
        AtmosphereResourceImpl resource = (AtmosphereResourceImpl) webSocket.resource();
        resource.suspend();

        ChatService chatService = SpringApplicationContext.getBean(ChatService.class);
        ObjectMapper mapper = SpringApplicationContext.getBean(ObjectMapper.class);
        Command command = readCommand(message, mapper);
        command.setResource(resource);
        chatService.execute(command);
        return null;
    }

    private Command readCommand(String s, ObjectMapper mapper) {
        Command command = new EmptyCommand();
        try {
            command = mapper.readValue(s, BaseCommand.class);
        } catch (IOException e) {
            LOG.error("Exception converting JSON:", e);
        }
        return command;
    }

    @Override
    public List<AtmosphereRequest> onMessage(WebSocket webSocket, byte[] bytes, int offset, int length) {
        return onMessage(webSocket, new String(bytes, offset, length));
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        LOG.debug("opened web socket connection {}", webSocket);
    }

    @Override
    public void onClose(WebSocket webSocket) {
        LOG.debug("closing web socket connection {}", webSocket);
    }

    @Override
    public void onError(WebSocket webSocket, WebSocketProcessor.WebSocketException e) {
        LOG.error("error on websocket connection {}", e);
    }
}
