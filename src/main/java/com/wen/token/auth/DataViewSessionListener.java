package com.wen.token.auth;


import com.wen.token.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

@WebListener
public class DataViewSessionListener implements HttpSessionListener
{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void sessionCreated(HttpSessionEvent event)
	{
		logger.debug("Create session, sessionId=" + event.getSession().getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent event)
	{
		Object value = event.getSession().getAttribute(SessionUtil.EXPORT_FILES);
		if (value instanceof Map) {
			Map<String, Object> values = (Map<String, Object>) value;
			values.clear();
		} else
			value = null;
		logger.debug("Destory session, sessionId=" + event.getSession().getId());
	}

}
