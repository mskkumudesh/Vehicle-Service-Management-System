package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.AuthResponseDTO;
import lk.ijse.vehicleservice.dto.LoginDTO;
import lk.ijse.vehicleservice.dto.RegisterDTO;
import lk.ijse.vehicleservice.dto.UserDTO;

import java.util.List;

public interface UserService {

    AuthResponseDTO login(LoginDTO loginDTO);

    UserDTO createAccount(RegisterDTO registerDTO);

    void saveUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    UserDTO searchUser(String email);

    void deleteUser(String email);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(int id);

    boolean isExists(String email);
}