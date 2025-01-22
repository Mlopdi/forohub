package com.alura.forohub.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alura.forohub.repository.UsuarioRepository;
import com.alura.forohub.service.TokenServicio;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private TokenServicio tokenServicio;
    private UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenServicio tokenServicio, UsuarioRepository usuarioRepository) {
        this.tokenServicio = tokenServicio;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var token = request.getHeader("Authorization");

        if (!(token == null || token == "")) {
            token = token.replace("Bearer ", "") ;
            var subject = tokenServicio.getSubject(token);
            if (subject != null){
                var usuario =  usuarioRepository.findByNombre(subject);
                var autentication = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autentication);
            }
        }
        filterChain.doFilter(request,response);
    }

}
