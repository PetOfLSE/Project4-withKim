package com.example.project_with_kimminseo.security.custom;

import com.example.project_with_kimminseo.user.model.entity.UserEntity;
import com.example.project_with_kimminseo.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity name = userRepository.findByName(username);
        if (name == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetails(name);
    }
}
