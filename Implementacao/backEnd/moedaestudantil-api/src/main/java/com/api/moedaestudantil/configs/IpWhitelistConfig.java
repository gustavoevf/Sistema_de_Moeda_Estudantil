package com.api.moedaestudantil.configs;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class IpWhitelistConfig extends OncePerRequestFilter {

    @Value("${api.security.ip-whitelist}")
    private String ipWhitelist;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String clientIp = request.getRemoteAddr();
        List<String> allowedIps = Arrays.asList(ipWhitelist.split(","));

        if (!allowedIps.contains(clientIp)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access denied: Your IP is not whitelisted");
            return;
        }

        filterChain.doFilter(request, response);
    }
}