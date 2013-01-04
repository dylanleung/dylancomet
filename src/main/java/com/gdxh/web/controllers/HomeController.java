package com.gdxh.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.atmosphere.cpr.AtmosphereResource;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdxh.web.AtmosphereUtils;
import com.gdxh.web.model.TwitterMessage;
import com.gdxh.web.model.TwitterMessages;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	
	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "test";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/websockets", method = RequestMethod.POST)
	@ResponseBody
	public void post(final AtmosphereResource event, @RequestBody String message)
			throws JsonGenerationException, JsonMappingException, IOException {

		logger.info("Received message to broadcast: {}", message);

		TwitterMessages twitterMessages = new TwitterMessages();

		List<TwitterMessage> messages = new ArrayList<TwitterMessage>();

		final TwitterMessage twitterMessage = new TwitterMessage(-1L,
				new Date(), message, "websocket client", "");

		messages.add(twitterMessage);
		twitterMessages.setTwitterMessages(messages);
		event.getBroadcaster().getAtmosphereResources().size();
		event.getBroadcaster().broadcast(
				objectMapper.writeValueAsString(twitterMessages));
	}

	/**
	 * Responsible for suspending the {@link HttpServletResponse} and executing
	 * a broadcasts periodically.
	 *
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@RequestMapping(value = "/websockets", method = RequestMethod.GET)
	@ResponseBody
	public void websockets(final AtmosphereResource event)
			throws JsonGenerationException, JsonMappingException, IOException {

		AtmosphereUtils.suspend(event);

		//final Broadcaster bc = event.getBroadcaster();

		//final int numberOfClients = bc.getAtmosphereResources().size();

//		String statusMessage = "A new Client has connected on "
//				+ new Date().toString() + " (Total: " + numberOfClients + ")";

	//	logger.info(statusMessage);

		//bc.broadcast(objectMapper.writeValueAsString(new StatusMessage(
		//		statusMessage)));

	}

}
