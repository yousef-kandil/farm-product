package com.mazra3ty.store.usersAndAddresses.Service;

import com.mazra3ty.store.usersAndAddresses.DTO.Address.AddressResponse;
import com.mazra3ty.store.usersAndAddresses.DTO.User.UserRequest;
import com.mazra3ty.store.usersAndAddresses.DTO.User.UserResponse;
import com.mazra3ty.store.usersAndAddresses.Entity.Address;
import com.mazra3ty.store.usersAndAddresses.Entity.User;
import com.mazra3ty.store.usersAndAddresses.Repository.UserRepository;
import com.mazra3ty.store.usersAndAddresses.Validation.AddressValidator;
import com.mazra3ty.store.usersAndAddresses.Validation.UserValidator;
import com.mazra3ty.store.utils.shared.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final AddressValidator addressValidator;

    public UserResponse createUser(UserRequest request) {

        userValidator.checkUserByEmail(request.getEmail());
        User user = ObjectMapperUtils.map(request, User.class);
        user.setActive(true);

        Address firstAddress = user.getAddressList().getFirst();
        firstAddress.setUser(user);
        firstAddress.setDefault(true);

        userRepository.save(user);
        return ObjectMapperUtils.map(user, UserResponse.class);
    }


    public UserResponse getUserById(Long id) {

        User user = userValidator.checkUserById(id);
        UserResponse response = ObjectMapperUtils.map(user, UserResponse.class);
        List<AddressResponse> userAddress = user.getAddressList().stream().map(address ->
                ObjectMapperUtils.map(address, AddressResponse.class)
        ).toList();
        response.setAddressList(userAddress);
        return response;
    }


    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAllByIsActiveTrue();

        return users.stream().map(user -> {
            UserResponse userResponse = ObjectMapperUtils.map(user, UserResponse.class);

            List<AddressResponse> addressResponseList = user.getAddressList().stream()
                    .map(address -> ObjectMapperUtils.map(address, AddressResponse.class)).toList();

            userResponse.setAddressList(addressResponseList);

            return userResponse;
        }).toList();
    }


    public UserResponse updateUserById(Long id, UserRequest request) {

        User existsUser = userValidator.checkUserById(id);

        if (request.getEmail() != null && !existsUser.getEmail().equals(request.getEmail())) {
            userValidator.checkUserByEmail(request.getEmail());
            existsUser.setEmail(request.getEmail());
        }
        if (request.getFullName() != null && !existsUser.getFullName().equals(request.getFullName())) {
            existsUser.setFullName(request.getFullName());
        }
        if (request.getUsername() != null && !existsUser.getUsername().equals(request.getUsername())) {
            existsUser.setUsername(request.getUsername());
        }
        if (request.getPhone() != null && !existsUser.getPhone().equals(request.getPhone())) {
            existsUser.setPhone(request.getPhone());
        }
        if (request.getPassword() != null && !existsUser.getPassword().equals(request.getPassword())) {
            existsUser.setPassword(request.getPassword());
        }
        userRepository.save(existsUser);
        return ObjectMapperUtils.map(existsUser, UserResponse.class);
    }


    public void userSoftDeleteById(Long id) {
        User user = userValidator.checkUserById(id);
        user.setActive(false);
        userRepository.save(user);
    }

}
