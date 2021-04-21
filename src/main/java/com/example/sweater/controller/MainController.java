package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.service.LikeMeService;
import com.example.sweater.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private LikeMeService likeMeService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/main")
    public String main(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false, defaultValue = "") String heading,
            @RequestParam(required = false, defaultValue = "0") String pricemin,
            @RequestParam(required = false, defaultValue = "1000000000") String pricemax,
            Model model) {

        List<Message> messages;

        List<Long> likeMeList = likeMeService.getListLikeMeIdByUserId(user.getId());

//Тільки заголовок є
        if ((filter != null || !filter.isEmpty()) && (heading.equals("All"))) {
            long pricemin1 = Long.parseLong(pricemin);
            long pricemax1 = Long.parseLong(pricemax);
            messages = messageService.filterTitleAndPrice1AndPrice2(filter, pricemin1, pricemax1);
        }
//Тільки рубрика все
        else if ((filter == null || filter.isEmpty()) && (heading.equals("All"))) {
            long pricemin1 = Long.parseLong(pricemin);
            long pricemax1 = Long.parseLong(pricemax);
            messages = messageService.filterPrice1AndPrice2(pricemin1, pricemax1);
        }
//Є рубрика
        else if ((filter == null || filter.isEmpty()) && (heading != null || !heading.isEmpty()) && (!heading.equals("All"))) {
            long pricemin1 = Long.parseLong(pricemin);
            long pricemax1 = Long.parseLong(pricemax);
            messages = messageService.filterHeadingAndPrice1AndPrice2(heading, pricemin1, pricemax1);
        }
//Є заголовок і рубрика
        else if ((filter != null || !filter.isEmpty()) && (heading != null || !heading.isEmpty()) && (!heading.equals("All"))) {
            long pricemin1 = Long.parseLong(pricemin);
            long pricemax1 = Long.parseLong(pricemax);
            messages = messageService.filterTitleAndHeadingAndPrice1AndPrice2(filter, heading, pricemin1, pricemax1);
        } else {
            messages = messageService.findAll();
        }

        model.addAttribute("messages",messages);
        model.addAttribute("filter", filter);
        model.addAttribute("heading", heading);
        model.addAttribute("pricemin", pricemin);
        model.addAttribute("pricemax", pricemax);
        model.addAttribute("likeMeList", likeMeList);
        model.addAttribute("user", user);

        return "main";
    }

    @GetMapping
    public String rather() {
        return "greeting";
        }

    @GetMapping("/likedmess/add/{id}")
    public String addLIke(@AuthenticationPrincipal User user, @PathVariable long id) {
        if(likeMeService.findByMessIdAndUserId(id,user.getId())) {
            likeMeService.save(id, user.getId());
            return "redirect:/main";
        }
        return "redirect:/main"; }

    @GetMapping("/likedmess/delete/{id}")
    public String deleteLIke(@AuthenticationPrincipal User user, @PathVariable long id) { likeMeService.delete(id , user.getId());   return "redirect:/main"; }
}
