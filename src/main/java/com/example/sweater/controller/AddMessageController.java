package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.service.MessageService;
import com.example.sweater.utils.validator.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import java.util.Objects;
import java.util.UUID;

@Controller
public class AddMessageController {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.path}s")
    private String uploadPaths;

    @Value("${upload.path}sq")
    private String uploadPathsq;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageValidator messageValidator;

    @GetMapping("/addnotice")
    public String addget(@AuthenticationPrincipal User user,
                         Model model) {
        model.addAttribute("user", user);
        return "addnotice";
    }

    @PostMapping("/addnotice")
    public String addpost(
            @AuthenticationPrincipal User user,
            @Valid
            @ModelAttribute("messageForm") Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file,
            @RequestParam("files") MultipartFile files,
            @RequestParam("filesq") MultipartFile filesq
    ) throws IOException {

        messageValidator.validate(message, bindingResult);

        messageValidator.bindingResultErrors(bindingResult, model);

        message.setAuthor(user);


        if (message.getTitle() != null && !message.getTitle().isEmpty() &&
                message.getDescription() != null && !message.getDescription().isEmpty() &&
                message.getPrice() >= 0 && message.getPrice() <= 9999999 &&
                message.getPlace() != null && !message.getPlace().isEmpty() &&
                saveFile1(message, file) &&
                saveFile2(message, files) &&
                saveFile3(message, filesq)
        ) {
            messageService.save(message);
            model.addAttribute("message", null);
            Iterable<Message> messages = messageService.findAll();
            model.addAttribute("messages", messages);
            return "redirect:/main";
        } else {
            model.addAttribute("messages", message);
            return "addnotice";
        }
    }
    private boolean saveFile1(@Valid Message message, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) { uploadDir.mkdir(); }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            message.setFilename(resultFilename);
            return true;
        } else {
            return false;
        }
    }

    private boolean saveFile2(@Valid Message message, @RequestParam("files") MultipartFile files) throws IOException {
        if (files != null && !Objects.requireNonNull(files.getOriginalFilename()).isEmpty()) {
            File uploadDirs = new File(uploadPaths);
            if (!uploadDirs.exists()) { uploadDirs.mkdir();  }
            String uuidFiles = UUID.randomUUID().toString();
            String resultFilenames = uuidFiles + "." + files.getOriginalFilename();
            files.transferTo(new File(uploadPaths + "/" + resultFilenames));
            message.setFilenames(resultFilenames);
            return true;
        } else {
            return false;
        }
    }

    private boolean saveFile3(@Valid Message message, @RequestParam("filesq") MultipartFile filesq) throws IOException {
        if (filesq != null && !Objects.requireNonNull(filesq.getOriginalFilename()).isEmpty()) {
            File uploadDirsq = new File(uploadPathsq);
            if (!uploadDirsq.exists()) {
                uploadDirsq.mkdir();
            }
            String uuidFilesq = UUID.randomUUID().toString();
            String resultFilenamesq = uuidFilesq + "." + filesq.getOriginalFilename();
            filesq.transferTo(new File(uploadPathsq + "/" + resultFilenamesq));
            message.setFilenamesq(resultFilenamesq);
            return true;
        } else {
            return false;
        }
    }
}
