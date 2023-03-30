package com.devsuperior.dslearnbds.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) //para forçar que sempre que buscar um USER no banco sempre venha os ROLES(perfis do usuários), pois o SPRING SECURITY ele exige que tenha isso, ai sempre virá juntos os objetos de perfis(roles nesse caso), se usar o LAZY, só vira quando fizer um GET
    @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>(); //Associação MANY to MANY se coloca o set pra garantir que não haja repetição do mesmo role no mesmo usuário, posterior chama uma classe que a implementa

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    //IMPLEMENTAÇÃO DO USERDETAIL -> desenvolver os métodos de acordo com a necessidade do código
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //para retornar uma coleção do tipo GrantedAuthority
        //o User tem uma associação com os ROLES, percorrer a coleção convertendo cada elemento do tipo ROLE para o tipo GRANTEDAUTHORITY

        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority())) //classe concreta que implementa o GRANTEDAUTHORITY(interface), getAuthority(pq quero o nome do ROLE que está nesse obj role)
                .collect(Collectors.toList());
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    //daqui pra baixo vou fazer tudo retornar true pq não tem essa lógica por enquanto na aplicação
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
