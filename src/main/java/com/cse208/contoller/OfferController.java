package com.cse208.contoller;

import com.cse208.Entity.Offer;
import com.cse208.Entity.User;
import com.cse208.service.OfferService;
import com.cse208.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


@Controller
public class OfferController {
    @Autowired
    OfferService offerService;
    @Autowired
    UserService userService;

    @RequestMapping("/user/offer")
    public String wirteOffer(){
        return "user/upload";
    }

    @Value("${file.path}")
    private String filePath;

    //userId  userName  applyTime result not null
    @RequestMapping("/user/upload")
    public String uploadOffer(Offer offer, @RequestParam(value="file",required=false)  MultipartFile file, HttpServletRequest request) throws IOException {
        String username= (String) request.getSession().getAttribute("loginUser");
        User user=userService.getUserByName(username);
        offer.setUId(user.getId());
        offer.setUsername(username);
        offerService.setOneOffer(offer);

        Date dt=new Date();
        if (!file.isEmpty()) {
            //查看上传的offer格式 jpg? png?
            String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = username+"-"+dt.getTime() + extName;
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream((new File(filePath + fileName))));
        }
        return "redirect:/";
    }


    @RequestMapping("/user/deleteOffer")
    public String deleteOffer(Offer offer, HttpServletRequest request) {
        String username= (String) request.getSession().getAttribute("loginUser");
        User user=userService.getUserByName(username);
        offer.setUId(user.getId());
        offerService.setOneOffer(offer);
        return "redirect:/index";
    }

    @RequestMapping("/user/updateOffer")
    public String updateOffer(Offer offer, HttpServletRequest request) {
        String username= (String) request.getSession().getAttribute("loginUser");
        User user=userService.getUserByName(username);
        offer.setUId(user.getId());
        offerService.setOneOffer(offer);
        return "redirect:/index";
    }

    @RequestMapping("/")
    /* 前端传参: page页码, 关键字keyword*/
    public String showOfferByInput (Integer page, @Param("keyword1") String keyword1, Model model) {
        //调用分页的业务
        if((page == null) && (keyword1 == null)){
            PageInfo<Offer> pageInfo = offerService.getOfferByPage(1);
            model.addAttribute("pageInfo", pageInfo);
            return "test";
        }
        PageInfo<Offer> pageInfo = offerService.getOfferByKeyword(page, keyword1);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("keyword1", keyword1);
        return "test";
    }

}
