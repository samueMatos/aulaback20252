package com.senac.aulafull.infra.config;

import com.senac.aulafull.application.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.equals("/auth/login")
                || path.equals("/auth/esqueciminhasenha")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/webjars")
                || path.startsWith("/swagger-ui")) {

            filterChain.doFilter(request, response);
            return;
        }


        String header = request.getHeader("Authorization");

        try {


            if (header != null && header.startsWith("Bearer ")) {
                String token = header.replace("Bearer ", "");
                var usuario = tokenService.validarToken(token);

                var autorizacao = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        usuario.autorizacao());

                SecurityContextHolder.getContext().setAuthentication(autorizacao);

                filterChain.doFilter(request,response);

            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token não informado");
                return;
            }

        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token não informado");
            return;
        }
    }
}