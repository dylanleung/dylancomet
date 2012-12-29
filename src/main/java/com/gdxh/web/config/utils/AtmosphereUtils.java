package com.gdxh.web.config.utils;

import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListenerAdapter;
import org.atmosphere.cpr.Meteor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AtmosphereUtils {
	public static final Logger LOG = LoggerFactory.getLogger(AtmosphereUtils.class);

    public static AtmosphereResource getAtmosphereResource(HttpServletRequest request) {
        return getMeteor(request).getAtmosphereResource();
    }

    public static Meteor getMeteor(HttpServletRequest request) {
        return Meteor.build(request);
    }

    public static void suspend(final AtmosphereResource resource) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        resource.addEventListener(new AtmosphereResourceEventListenerAdapter() {
            @Override
            public void onSuspend(AtmosphereResourceEvent event) {
                countDownLatch.countDown();
                resource.removeEventListener(this);
            }
        });
        resource.suspend();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LOG.error("Interrupted while trying to suspend resource {}", resource);
        }
    }

}
