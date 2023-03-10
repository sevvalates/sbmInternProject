package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.User;
import com.sbmInternProject.InsuranceAgency.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

        @Mock
        private UserService userService;

        @Mock
        private Model model;

        @InjectMocks // is used to inject those mock objects into the class
        private UserController userController;

        private List<User> userList;

        @BeforeEach
        public void setUp(){

            userList = new ArrayList<>();

            User user1 = new User();
            user1.setId(1L);
            user1.setName("sevval");
            user1.setSurname("ates");
            user1.setIdentityNumber(12345678900L);
            user1.setPhoneNumber(1234567890L);
            user1.setBirthYear(2000);
            userList.add(user1);

            User user2 = new User();
            user2.setId(2L);
            user2.setName("esra");
            user2.setSurname("ates");
            user2.setIdentityNumber(12345678901L);
            user2.setPhoneNumber(1234567891L);
            user2.setBirthYear(1998);
            userList.add(user2);
        }

        @Test
        public void testGetRegisterPage(){

            String viewName = userController.getRegisterPage(model);
            assertEquals("register", viewName);

            //verify model.addAttribute() method was called exactly once with a string argument of "user"
            //and a second argument of any instance of the User class.
            verify(model, times(1)).addAttribute(eq("user"), any(User.class));
        }

        @Test
        public void testHandleRegisterFormWithValidUser(){

            User user = new User();
                 user.setId(3);
                 user.setName("ada");
                 user.setSurname("deniz");
                 user.setIdentityNumber(12345678902L);
                 user.setPhoneNumber(1234567892L);
                 user.setBirthYear(2002);

            BindingResult result = mock(BindingResult.class);

            String viewName = userController.handleRegisterForm(user, result, model);

            assertEquals("redirect:/userlist", viewName);

            // verify that the addUser method was called once on the userService object
            // with the argument user.
            // If the method was not called,
            // or was called with a different argument
            // or number of times, the test will fail.
            verify(userService, times(1)).addUser(eq(user));
        }

        @Test
        public void testHandleRegisterFormWithInvalidUser(){

            User user = new User();
                 user.setId(3);
                 user.setName("ada");
                 user.setSurname("deniz");
                 user.setIdentityNumber(12345678902L);
                 user.setPhoneNumber(1234567892L);
                 user.setBirthYear(LocalDate.now().getYear() - 17);

            BindingResult result = mock(BindingResult.class);

            String viewName = userController.handleRegisterForm(user, result, model);

            assertEquals("register", viewName);

            // The eq method is to ensure that the value of the user object being passed
            // to the method is the same as the user object that is expected by the method.
            verify(model, times(1)).addAttribute(eq("user"), eq(user));

            // to ensure that a certain message is being passed to the model
            // in certain error conditions
            verify(model, times(1)).addAttribute(eq("errorMessage"), anyString());

            // to ensure that "userService" was never called and never used to add user during the test
            verify(userService, never()).addUser(any(User.class));
        }


        @Test
        public void testGetUserListPage(){

            when(userService.getUsers()).thenReturn(userList);

            String viewName = userController.getUserListPage(model);

            assertEquals("userlist", viewName);
            verify(userService, times(1)).getUsers();
            verify(model, times(1)).addAttribute(eq("userList"), any(List.class));
        }

        @Test
        public void testDeleteUser() {

            Long id = 1L;
            String viewName = userController.deleteUser(id);

            assertEquals("redirect:/userlist", viewName);
            verify(userService).deleteUserById(id);
        }

        @Test
        public void testEditUserForm(){

            User user = userList.get(0);
            when(userService.getUserById(user.getId())).thenReturn(user);

            String viewName = userController.editUserForm(user.getId(), model);

            assertEquals("edit_user", viewName); // == assertThat(viewName).isEqualTo("edit_user");
            verify(model, times(1)).addAttribute(eq("user"), any(User.class));
        }

        @Test
        public void testUpdateUser(){

            User existingUser = userList.get(0);
            User updatedUser = new User();
            updatedUser.setName("Ela");
            updatedUser.setPhoneNumber(1234567890L);

            when(userService.getUserById(existingUser.getId())).thenReturn(existingUser);

            String result = userController.updateUser(existingUser.getId(), updatedUser, null);

            verify(userService, times(1)).updateUser(existingUser);
            assertEquals("redirect:/userlist", result);
            // assertEquals(existingUser.getId(), updatedUser.getId());  //HATA????
            assertEquals(updatedUser.getName(), existingUser.getName());
            assertEquals(updatedUser.getPhoneNumber(), existingUser.getPhoneNumber());
        }

}