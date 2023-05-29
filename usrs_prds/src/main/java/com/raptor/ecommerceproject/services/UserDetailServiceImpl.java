package com.raptor.ecommerceproject.services;

import com.raptor.ecommerceproject.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    HttpSession session;

    private Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Este es el username: ",username);
        Optional<User> optionalUser = userService.findByMail(username);
        if (optionalUser.isPresent()) {
            log.info("Esto es el id del usuario: ",optionalUser.get().getId());
            session.setAttribute("idUser", optionalUser.get().getId());
            User user = optionalUser.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getMail()).
                    password(user.getPassword())
                    .roles(user.getTypeUser()).build();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
