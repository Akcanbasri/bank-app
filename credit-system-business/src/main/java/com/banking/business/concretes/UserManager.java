package com.banking.business.concretes;

import com.banking.business.abstracts.UserService;
import com.banking.business.dtos.requests.LoginRequest;
import com.banking.business.dtos.requests.RegisterRequest;
import com.banking.business.dtos.responses.TokenResponse;
import com.banking.business.security.JwtUtils;
import com.banking.entities.Role;
import com.banking.entities.User;
import com.banking.entities.UserType;
import com.banking.repositories.abstracts.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("businessJwtUtils")
    private JwtUtils jwtUtils;

    @Override
    public TokenResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setCustomerId(request.getCustomerId());
        user.setCustomerType(request.getCustomerType());

        // Set user type and roles
        if (request.getCustomerId() != null && request.getCustomerType() != null) {
            user.setUserType(UserType.CUSTOMER);
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_CUSTOMER);

            if (request.getCustomerType() != null) {
                switch (request.getCustomerType()) {
                    case INDIVIDUAL:
                        roles.add(Role.ROLE_INDIVIDUAL_CUSTOMER);
                        break;
                    case CORPORATE:
                        roles.add(Role.ROLE_CORPORATE_CUSTOMER);
                        break;
                }
            }

            user.setRoles(roles);
        } else {
            user.setUserType(UserType.CUSTOMER);
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_CUSTOMER);
            user.setRoles(roles);
        }

        User savedUser = userRepository.save(user);

        // Generate JWT token
        String jwt = jwtUtils.generateJwtToken(savedUser);

        List<String> roleList = savedUser.getRoles().stream()
                .map(Role::name)
                .collect(Collectors.toList());

        return new TokenResponse(jwt, "Bearer", savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(),
                roleList);
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = userRepository.findByUsername(request.getUsernameOrEmail())
                .orElseGet(() -> userRepository.findByEmail(request.getUsernameOrEmail())
                        .orElseThrow(() -> new UsernameNotFoundException(
                                "User not found with username or email: " + request.getUsernameOrEmail())));

        List<String> roles = user.getRoles().stream()
                .map(Role::name)
                .collect(Collectors.toList());

        return new TokenResponse(jwt, "Bearer", user.getId(), user.getUsername(), user.getEmail(), roles);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}