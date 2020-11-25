package geekbrains.services;

import geekbrains.controllers.dto.UserDto;
import geekbrains.controllers.dto.UserType;
import geekbrains.entities.Role;
import geekbrains.entities.User;
import geekbrains.exceptions.ManagerIsEarlierThanNeedException;
import geekbrains.exceptions.UnknownUserTypeException;
import geekbrains.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private Role role;
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User saveUser(UserDto userDto) {
        if (userDto.getUserType().equals(UserType.MANAGER)) {
            return saveManager(userDto);

        } else if (userDto.getUserType().equals(UserType.USER)) {
            return saveTypicallyUser(userDto);
        }

        throw new UnknownUserTypeException();
    }

    private User saveTypicallyUser(UserDto userDto) {
        User user = createUserFromDto(userDto);

        Role role = roleService.getByName("ROLE_CUSTOMER");
        user.setRoles(List.of(role));

        return userRepository.save(user);
    }

    private User saveManager(UserDto userDto) {
        if (userDto.getAge() > 18) {
            User user = createUserFromDto(userDto);

            Role role = roleService.getByName("ROLE_MANAGER");
            user.setRoles(List.of(role));

            return userRepository.save(user);
        }

        throw new ManagerIsEarlierThanNeedException("Пользователь младше восемнадцати лет");
    }

    private User createUserFromDto(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setAge(userDto.getAge());

        return user;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersWithType(UserType userType) {
        if(userType == UserType.MANAGER){
            role = roleService.getByName("ROLE_MANAGER");
        } else if (userType == UserType.USER){
            role = roleService.getByName("ROLE_CUSTOMER");
        }return userRepository.findAllByroles(role);

    }
}
