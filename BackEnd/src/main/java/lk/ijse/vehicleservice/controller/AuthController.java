package lk.ijse.vehicleservice.controller;

import jakarta.validation.Valid;
import lk.ijse.vehicleservice.dto.AuthResponseDTO;
import lk.ijse.vehicleservice.dto.LoginDTO;
import lk.ijse.vehicleservice.dto.RegisterDTO;
import lk.ijse.vehicleservice.dto.UserDTO;
import lk.ijse.vehicleservice.service.UserService;
import lk.ijse.vehicleservice.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<AuthResponseDTO>> login(@RequestBody @Valid LoginDTO loginDto) {
        AuthResponseDTO authResponseDTO = userService.login(loginDto);
        return new ResponseEntity<>(
                new APIResponse<>(200, "Login Success", authResponseDTO), HttpStatus.OK
        );
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserDTO>> signup(@RequestBody @Valid RegisterDTO registerDTO) {
        UserDTO userDTO = userService.createAccount(registerDTO);
        return new ResponseEntity <>(
                new APIResponse<>(200 , "User Saved" , userDTO), HttpStatus.OK
        );
    }
}
