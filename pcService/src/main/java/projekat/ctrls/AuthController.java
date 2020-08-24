package projekat.ctrls;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekat.dto.login.LoginRequest;
import projekat.dto.login.LoginResponse;
import projekat.dto.register.RegisterRequest;
import projekat.jpa.Customer;
import projekat.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @CrossOrigin
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest)
    {
        return this.authService.login(loginRequest);
    }

    @PostMapping("/register")
    public Customer register(@RequestBody RegisterRequest registerRequest)
    {
        return this.authService.register(registerRequest);
    }

    @GetMapping("/claims")
    public Claims extractAllClaims(@RequestParam String token )
    {
        return authService.extractAllClaims(token);
    }
}
