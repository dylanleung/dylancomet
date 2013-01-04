package com.gdxh.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class TwitterMessages implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3715196795126294354L;

	@XmlElement(name = "twitterMessage")
	private Collection<TwitterMessage> twitterMessages = new ArrayList<TwitterMessage>(
			0);

	@XmlAttribute
	private long latestTweetId = 0;

	public Collection<TwitterMessage> getTwitterMessages() {
		return twitterMessages;
	}

	public void setTwitterMessages(Collection<TwitterMessage> twitterMessages) {

		for (TwitterMessage tweet : twitterMessages) {
			if (latestTweetId < tweet.getId()) {
				this.latestTweetId = tweet.getId();
			}
		}

		this.twitterMessages = twitterMessages;
	}

	public long getLatestTweetId() {
		return latestTweetId;
	}

	public void setLatestTweetId(long latestTweetId) {
		this.latestTweetId = latestTweetId;
	}

	@XmlAttribute
	public String getLatestTweetIdAsString() {
		return String.valueOf(latestTweetId);
	}

}
