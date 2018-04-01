package sur.snapps.sentoff.rest.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import sur.snapps.sentoff.domain.Message;
import sur.snapps.sentoff.domain.repo.MessageRepository;

public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

	@Autowired
	private MessageRepository messageRepository;

	@Override
	protected void initFilterBean() throws ServletException {
		setIncludePayload(true);
		setIncludeQueryString(true);
	}

	@Override
	protected void beforeRequest(HttpServletRequest request, String msg) {

	}

	@Override
	protected void afterRequest(HttpServletRequest request, String msg) {

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Message message = logRequest(request);
		HttpServletRequest requestToUse = request;
		if (!(request instanceof ContentCachingRequestWrapper)) {
			requestToUse = new ContentCachingRequestWrapper(request);
		}

		ContentCachingResponseWrapper responseToUse = null;
		if (!(response instanceof ContentCachingResponseWrapper)) {
			responseToUse = new ContentCachingResponseWrapper(response);
		}

		super.doFilterInternal(requestToUse, responseToUse, filterChain);

		logResponse(message, requestToUse, responseToUse);
		responseToUse.copyBodyToResponse();
	}

	private void logResponse(Message message, HttpServletRequest request, ContentCachingResponseWrapper response) {
		if (message == null) {
			return;
		}
		message.setRequestPayload(getPayload(request));
		message.setResponseTimestamp(LocalDateTime.now());
		message.setResponseStatus(response.getStatus());
		message.setResponsePayload(getPayload(response));
		messageRepository.update(message);
	}

	private Message logRequest(HttpServletRequest request) {
		if ("GET".equals(request.getMethod())) {
			return null;
		}
		Message message = new Message();
		message.setRequestTimestamp(LocalDateTime.now());
		message.setRequestUri(request.getRequestURI());
		message.setRequestMethod(request.getMethod());
		messageRepository.save(message);

		return message;
	}

	private String getPayload(HttpServletRequest request) {
		ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
		String payload = null;
		if (wrapper != null) {
			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				int length = buf.length;
				try {
					payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException ex) {
					payload = "[unknown]";
				}
			}
		}
		return payload;
	}

	private String getPayload(ContentCachingResponseWrapper wrapper) {
		String payload = null;
		if (wrapper != null) {
			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				int length = buf.length;
				try {
					payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException ex) {
					payload = "[unknown]";
				}
			}
		}
		return payload;
	}
}
