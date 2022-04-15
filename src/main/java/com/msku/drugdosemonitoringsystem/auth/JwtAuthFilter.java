package com.msku.drugdosemonitoringsystem.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class JwtAuthFilter extends OncePerRequestFilter {
    //ADD JWT FILTER

    @Autowired
    JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    UserDetailService patientDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        System.out.println("JwtAuthFilter");
        try{
            if(StringUtils.hasText(request.getHeader("Authorization")) && request.getHeader("Authorization").startsWith("Bearer ")){
                token = request.getHeader("Authorization").substring(7);
                System.out.println("token: "+token);

            }

            if(token != null && jwtTokenGenerator.isTokenValid(token)){
                UUID patient_id = jwtTokenGenerator.getUserId(token);
                UserDetail patientDetail = (UserDetail) patientDetailService.loadUserById(patient_id);
                if(patientDetail != null){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(patientDetail,patientDetail,patientDetail.getAuthorities());
                    System.out.println("authenticationToken: "+authenticationToken);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
            }
        } catch (Exception e) {
            System.out.println("Exception"+e.toString());
            return;
        }
        //spring security other filters
        filterChain.doFilter(request,response);
    }
}
