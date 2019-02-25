package com.en.sphzb.controller;

import com.en.sphzb.VO.ResultVO;
import com.en.sphzb.service.CaseService;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CaseService caseService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "case";
    }

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResultVO caseUpload(MultipartFile cases) {
        try {
            if(!cases.isEmpty()){
                caseService.uploadCases(cases);
            }else {
                ResultVO resultVO = new ResultVO(1,"文件不存在");
                return resultVO;
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        return null;
    }


}