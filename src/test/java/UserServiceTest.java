import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.User;
import org.example.UserNotFoundException;
import org.example.UserService;
import org.example.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindUserById_UserExists() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // When
        User foundUser = userService.findUserById(1L);

        // Then
        assertEquals("John Doe", foundUser.getName());
        assertEquals(1L, foundUser.getId());
    }

    @Test
    public void testFindUserById_UserNotFound() {
        // Given
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When / Then
        assertThrows(UserNotFoundException.class, () -> {
            userService.findUserById(1L);
        });
    }
}
