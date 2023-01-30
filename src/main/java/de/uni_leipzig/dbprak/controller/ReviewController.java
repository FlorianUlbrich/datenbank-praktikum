package de.uni_leipzig.dbprak.controller;

import de.uni_leipzig.dbprak.dao.ReviewRepository;
import de.uni_leipzig.dbprak.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/add-new-review")
    public String addNewReview(){
        return "add-new-review";
    }

    @RequestMapping(value = "/add-new-Review", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("review", new Review());
        Review review = (Review) model.getAttribute("review");
        return "redirect:/";
    }
}
