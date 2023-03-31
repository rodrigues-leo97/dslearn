package com.devsuperior.dslearnbds.services;

import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.UserRepository;
import com.devsuperior.dslearnbds.services.exceptions.ForbiddenException;
import com.devsuperior.dslearnbds.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true) //para operações somente de leitura para não travar o banco de dados pensando que pode haver escrita
    public User authenticated(){
        //para pegar o usuário autenticado
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName(); //pega o nome do usuário já reconhecido pelo Spring Security
            return userRepository.findByEmail(username); //retorna a identidade do usuário logado
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid user");
        }

    }

    public void validateSelfOrAdmin(Long userId) {
        User user = authenticated();

        if(!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN")) {
            throw new ForbiddenException("Access Denied");
        }
    }

}
