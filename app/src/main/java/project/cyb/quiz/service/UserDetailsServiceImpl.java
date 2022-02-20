package project.cyb.quiz.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.cyb.quiz.models.User;
import project.cyb.quiz.models.UserRole;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    /**
     * To load user by user name from user table
     */

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optionalUser = Optional.empty();

        optionalUser = userService.findByEmail(username);

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        User user = optionalUser.get();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(UserRole.USER.toString()));
        return new org.springframework.security.core.userdetails.User(user.getId().toString(), user.getPassword(),
                grantedAuthorities);
    }
}
