package com.en.sphzb.controller;

import com.en.sphzb.VO.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 案件相关控制器
 * create by en
 * at 2019/2/21 14:36
 **/
@Controller
@RequestMapping(value = "/case/")
public class CaseController {


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "case";
    }

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResultVO caseUpload(MultipartFile cases) {
        // TODO
        return null;
    }

}