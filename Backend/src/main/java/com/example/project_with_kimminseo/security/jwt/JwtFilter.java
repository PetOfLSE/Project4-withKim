package com.example.project_with_kimminseo.security.jwt;

import com.example.project_with_kimminseo.security.custom.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var req = new ContentCachingRequestWrapper(request);
        var res = new ContentCachingResponseWrapper(response);

        String header = req.getHeader("Authorization");
        String token = "";

        if(header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        if(token == null || token.isEmpty()) {
            filterChain.doFilter(req, res);
            res.copyBodyToResponse();
            return;
        }

        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            jwtUtil.validation(token);

            var claims = jwtUtil.parseToken(token);
            var subject = claims.getSubject();

            UserDetails user = customUserDetailsService.loadUserByUsername(subject);

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
        }

        filterChain.doFilter(req, res);

        res.copyBodyToResponse();
    }
}
