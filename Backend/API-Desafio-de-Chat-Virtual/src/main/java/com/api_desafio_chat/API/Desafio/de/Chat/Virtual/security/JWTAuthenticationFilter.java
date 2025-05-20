package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.LoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, 
                                            HttpServletResponse res) throws AuthenticationException {
        try {
            LoginDTO login = new ObjectMapper()
                .readValue(req.getInputStream(), LoginDTO.class);
            
            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getNome(), 
                        login.getSenha(), 
                        new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }                                        
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        
        UserSpringSecurity userSpringSecurity = (UserSpringSecurity) auth.getPrincipal();
        String token = jwtUtil.generateToken(userSpringSecurity.getUsername(),
                userSpringSecurity.getAuthorities().iterator().next().getAuthority());
        
        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

}
