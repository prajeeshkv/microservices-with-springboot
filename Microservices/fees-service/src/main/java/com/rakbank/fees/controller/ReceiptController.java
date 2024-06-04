package com.rakbank.fees.controller;

import com.rakbank.fees.model.Response;
import com.rakbank.fees.service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/fees")
public class ReceiptController {

    @Autowired
    FeesService feesService;

    @GetMapping("/v1/receipt/{studentid}")
    public String getReceiptDetailsAsThymeleaf(@PathVariable("studentid") Long studentid, Model model) {
        Response response = feesService.getReceiptDetails(studentid).getBody();
        model.addAttribute("student", response.getStudentDto());
        model.addAttribute("fees", response.getFeesDto());
        return "receipt";
    }

}
