package lk.ijse.vehicleservice.controller;

import jakarta.validation.Valid;
import lk.ijse.vehicleservice.dto.UserDTO;
import lk.ijse.vehicleservice.service.UserService;
import lk.ijse.vehicleservice.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<APIResponse<String>> saveUser(@Valid @RequestBody UserDTO userDTO) {
        if (userService.isExists(userDTO.getEmail())) {
            userService.updateUser(userDTO);
            return new ResponseEntity<>(new APIResponse<>(200,"User updated",null), HttpStatus.OK);
        }else {
            userService.saveUser(userDTO);
            return new ResponseEntity<>(new APIResponse<>(201, "User saved", null), HttpStatus.CREATED);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<String>> updateUser(@Valid @RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return new ResponseEntity<>(new APIResponse<>(200,"User updated",null), HttpStatus.OK);
    }

    @GetMapping("/search/{email}")
    public ResponseEntity<APIResponse<UserDTO>> getUser(@PathVariable String email) {
        UserDTO userDTO = userService.searchUser(email);
        return  new ResponseEntity<>(new APIResponse<>(200,"User found",userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<APIResponse<String>> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return new ResponseEntity<>(new APIResponse<>(200,"User deleted",null), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> list = userService.getAllUsers();
        return  new ResponseEntity<>(new APIResponse<>(200,"User list",list), HttpStatus.OK);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllCustomers() {
        List<UserDTO> list = userService.getAllCustomers();
        return  new ResponseEntity<>(new APIResponse<>(200,"User list",list), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<APIResponse<UserDTO>> getUserById(@PathVariable int id) {
        UserDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<>(new APIResponse<>(200,"User found",userDTO), HttpStatus.OK);
    }
}