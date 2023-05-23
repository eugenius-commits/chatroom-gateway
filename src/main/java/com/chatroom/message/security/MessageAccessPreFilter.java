package com.chatroom.message.security;

import java.util.function.Supplier;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.chatroom.message.exception.ForbiddenException;
import com.chatroom.message.model.UserProfile;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MessageAccessPreFilter extends ZuulFilter {

    private final PathMatcher pathMatcher = new AntPathMatcher();

    private Supplier<Boolean> putMessageSupplier() {
        return () -> {
            RequestContext context = RequestContext.getCurrentContext();

            String requestUri = (String) context.get("requestUri");

            if (!pathMatcher.match("/**/1.0/messages/{message}", requestUri)) {
                return false;
            }

            log.debug("Pre filter " + requestUri);

            return true;
        };
    }

    @SneakyThrows
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        UserProfile UserProfile = (UserProfile) context.get("userProfile");

        if (UserProfile == null || Strings.isNullOrEmpty(UserProfile.getUserId())) {
            throw new ForbiddenException("No user has been detected");
        }

        Boolean isValid = putMessageSupplier().get();

        if (!isValid) {
            throw new ZuulException("Zuul Message Filter", HttpServletResponse.SC_NOT_FOUND, "Service is Not allowed");
        }
        return null;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        String proxy = (String) context.get("proxy");
        return "message-api".equalsIgnoreCase(proxy);
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }
}
