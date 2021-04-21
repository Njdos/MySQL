package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.service.UserService;
import com.example.sweater.utils.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@Controller
public class ProfileController {

    @Value("${upload.path}q")
    private String uploadPathq;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/profile/{userId}")
    public String profile(@AuthenticationPrincipal User user,
                          Model model
    ) {
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String addUser(
            @Valid
            @AuthenticationPrincipal
            @ModelAttribute("userForm") User user,
            BindingResult bindingResult,
            Model model,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password2") String password2,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("date") String date,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("gender") String gender,
            @RequestParam("fileq") MultipartFile fileq
    ) throws IOException {

        userValidator.validate(user, bindingResult);

        userValidator.bindingResultErrors(bindingResult, model);

        if (!userService.searchEmailWithoutUser(email, user.getId())) { model.addAttribute("emailError", "Email are used"); }
        if (!userService.searchUserNameWithoutUser(username, user.getId())) { model.addAttribute("usernameError", "User name are used"); }
        if (!userService.searchPhoneWithoutUser(phone, user.getId())) { model.addAttribute("phoneError", "User phone are used"); }
        if (user.getDate().equals("")){ model.addAttribute("dateError", "User date cann`t be empty"); }
        if(!user.getPassword().equals(user.getPassword2())){ model.addAttribute("passwordError",  "password don`t equals password2");}


        if (username != null && !username.isEmpty() &&
                password != null && !password.isEmpty() &&
                password2 != null && !password2.isEmpty() &&
                password.equals(password2) &&

                userService.searchUserNameWithoutUser(username, user.getId()) &&
                userService.searchPhoneWithoutUser(phone, user.getId()) &&
                userService.searchEmailWithoutUser(email, user.getId()) &&

                firstname != null && !firstname.isEmpty() &&
                lastname != null && !lastname.isEmpty() &&
                date != null && !date.isEmpty() &&
                email != null && !email.isEmpty() &&
                phone != null && !phone.isEmpty() &&
                phone.trim().length() == 12 &&
                isFilet(user, fileq)) {

            user.setId(user.getId());
            user.setGender(gender);
            user.setUsername(username);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setDate(date);
            user.setEmail(email);
            user.setPhone(phone);
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setActivatorCode(UUID.randomUUID().toString());
            user.setPassword(passwordEncoder.encode(password));
            userService.update(user);
            return "redirect:/main";
        } else {
            model.addAttribute("user", user);
            return "redirect:/profile/" + user.getId();
            /*return "profile";*/
        }
    }

    private boolean isFilet (User user, MultipartFile fileq) throws IOException {
        if (fileq != null && !Objects.requireNonNull(fileq.getOriginalFilename()).isEmpty()) {
            File uploadDirq = new File(uploadPathq);
            if (!uploadDirq.exists()) {
                uploadDirq.mkdir();
            }
            String uuidFileq = UUID.randomUUID().toString();
            String resultFilenameq = uuidFileq + "." + fileq.getOriginalFilename();
            fileq.transferTo(new File(uploadPathq + "/" + resultFilenameq));
            user.setFilenameq(resultFilenameq);
            return true;
        } else {
            return false;
        }
    }
}
