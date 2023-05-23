package com.chatroom.message.security;

import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ZuulOauth2Token extends ZuulFilter{

    private OAuth2RestOperations restOperations;

    public ZuulOauth2Token (OAuth2RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public void setRestOperations(OAuth2RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        OAuth2AccessToken accessToken = getOAuth2AccessToken(context);
        context.addZuulRequestHeader("autohrization", accessToken.getTokenType() + " " + accessToken.getValue());
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public String filterType() {
        return "pre";
    }
    
    private OAuth2AccessToken getOAuth2AccessToken(RequestContext context) {
        try {
            return restOperations.getAccessToken();
        } catch (Exception e) {
            context.set("error", HttpServletResponse.SC_UNAUTHORIZED);
            throw new BadCredentialsException("User has not been authorized");
        }
    }
}
