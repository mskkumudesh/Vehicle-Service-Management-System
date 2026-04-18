package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.AuthResponseDTO;
import lk.ijse.vehicleservice.dto.LoginDTO;
import lk.ijse.vehicleservice.dto.RegisterDTO;
import lk.ijse.vehicleservice.dto.UserDTO;
import lk.ijse.vehicleservice.entity.Role;
import lk.ijse.vehicleservice.entity.User;
import lk.ijse.vehicleservice.exception.custom.DuplicateException;
import lk.ijse.vehicleservice.exception.custom.NotFoundException;
import lk.ijse.vehicleservice.repository.UserRepository;
import lk.ijse.vehicleservice.service.UserService;
import lk.ijse.vehicleservice.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(
                () -> new RuntimeException("Email not found")
        );
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        String token = jwtUtil.generateToken(loginDTO.getEmail());
        return new AuthResponseDTO(token , user.getRole().name());
    }

    @Override
    public UserDTO createAccount(RegisterDTO dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.Customer);
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDTO.class);
    }
    @Override
    public void saveUser(UserDTO userDTO) {

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new DuplicateException("User already exists!");
        }
          User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.Customer);
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {

         User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());

        userRepository.save(user);
    }

    @Override
    public UserDTO searchUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<UserDTO> getAllCustomers() {
        return userRepository.findAllByRoleEquals(Role.Customer)
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return modelMapper.map(user, UserDTO.class);
    }
    @Override
    public boolean isExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}