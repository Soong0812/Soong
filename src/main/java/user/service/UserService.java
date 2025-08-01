package user.service;

import lombok.RequiredArgsConstructor;
import memo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.dto.UserRequestDto;
import user.dto.UserResponseDto;
import user.entity.User;
import user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User savedUser = userRepository.save(new User(userRequestDto.getEmail()));
        return new UserResponseDto(savedUser.getId(), savedUser.getEmail());
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 없습니다.")
        );

        return new UserResponseDto(user.getId(), user.getEmail());
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> dtoList = new ArrayList<>();

        for (User user : userList) {
            dtoList.add(new UserResponseDto(user.getId(), user.getEmail()));
        }
        return dtoList;
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 없습니다.")
        );

        return new UserResponseDto(user.getId(), user.getEmail());
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 없습니다.")
        );
        userRepository.deleteById(id);
    }
}
