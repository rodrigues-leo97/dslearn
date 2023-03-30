package com.devsuperior.dslearnbds.components;

import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component //para dizer que será um componente do SpringBoot
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UserRepository userRepository;

    //método para inserir mais informações ao meu token
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        //o método serve para entrar no ciclo de vida do token e na hora de gerar o token ele irá acrescentar os objetos que eu passar para ter um TOKEN com mais informações

        User user = userRepository.findByEmail(oAuth2Authentication.getName()); //essa informação já estará no token de autenticação, então consigo pegar ela de lá

        //o tipo da chave é String e o tipo do valor Object pq pode ser qualquer tipo
        Map<String, Object> map = new HashMap<>();

        //inserindo informações ao token(estou informando oq quero inserir mas ainda não está sendo inserido)
        //map.put("userFirstName", user.getName()); deixar o tokenEnhancer somente com informações do ID
        map.put("userId", user.getId());

        //agora para inserir de fato ao token essas informações tenho que usar o objeto OAuth2AccessToken
        //mas preciso fazer um CAST para tipar ainda mais o objeto, pois está mais específico dentro de uma classe, acrescento a palavra Default na frente

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
        token.setAdditionalInformation(map); //passo as informações do token

        return oAuth2AccessToken;
    }
}
