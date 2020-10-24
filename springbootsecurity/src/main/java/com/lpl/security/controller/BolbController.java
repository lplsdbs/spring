package com.lpl.security.controller;

import com.lpl.security.entity.Blob;
import com.lpl.security.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BolbController {

    @Autowired
    IBlogService blogService;
    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {
        List<Blob> list =blogService.getBlobs();
        model.addAttribute("blogsList", list);
        return new ModelAndView("blogs/list", "blogModel", model);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")  //security方法级别的权限控制，可以写在service上
    @GetMapping(value = "/{id}/deletion")
    //获得请求占位符参数
    public ModelAndView delete(@PathVariable("id") Long id, Model model) {
        blogService.deleteBlob(id);
        model.addAttribute("blogsList", blogService.getBlobs());
        return new ModelAndView("blogs/list", "blogModel", model);
    }
}
