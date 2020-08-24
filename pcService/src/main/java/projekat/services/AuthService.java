package projekat.services;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projekat.dto.login.LoginRequest;
import projekat.dto.login.LoginResponse;
import projekat.dto.register.RegisterRequest;
import projekat.jpa.Customer;
import projekat.jpa.User;
import projekat.reps.CustomerRepository;
import projekat.security.jwt.JwtTokenProvider;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            User user = (User) userService.loadUserByUsername(loginRequest.getUsername());
            if (user != null) {
                String username = loginRequest.getUsername();
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword(), user.getAuthorities()));
                String token = jwtTokenProvider.createToken(username, user.getRole());
                return new LoginResponse(token);
            } else
                return null;
        } catch (AuthenticationException e) {
            return null;
        }
    }

    public Customer register(RegisterRequest registerRequest) {
        if (usernameExists(registerRequest.getUsername())) {
            return null;
        }

        Customer customer = new Customer();
        customer.setCustomername(registerRequest.getName());
        customer.setCustomersurname(registerRequest.getSurname());
        customer.setCustomerphone(registerRequest.getPhone());
        customer.setCustomeradress(registerRequest.getAddress());
        customer.setCustomerusername(registerRequest.getUsername());
        customer.setCustomerpassword(encodePassword(registerRequest.getPassword()));
        return customerRepository.save(customer);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private boolean usernameExists(String username) {
        return userService.loadUserByUsername(username) != null;
    }

    public Claims extractAllClaims(String token) {
        if (token.isEmpty()) {
            return null;
        }
        return jwtTokenProvider.extractAllClaims(token);
    }
}
