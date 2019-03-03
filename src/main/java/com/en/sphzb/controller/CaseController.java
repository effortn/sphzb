package com.en.sphzb.controller;

import com.en.sphzb.VO.ResultVO;
import com.en.sphzb.service.CaseService;
import com.en.sphzb.service.KeyWeightService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
@RequestMapping(value = "/case/")
public class CaseController {

    @Autowired
    private CaseService caseService;

    @Autowired
    private KeyWeightService keyWeightService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "case";
    }

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResultVO caseUpload(MultipartFile cases) {
        // 判断文件是否为空
        if(!cases.isEmpty()){
            return caseService.uploadCases(cases);
        }else {
            log.error("【案件上传】上传文件为空！");
            ResultVO resultVO = new ResultVO(1,"文件不存在");
            return resultVO;
        }
    }

    @RequestMapping(value = "samples", method = RequestMethod.GET)
    public ResultVO caseUpload(String token) {
        // 判断token是否正确
        if ("stf-wt".equals(token)) {
            keyWeightService.calculateWeight();
            return new ResultVO(0, "权重计算完成！");
        } else {
            return new ResultVO(0, "没有权限！");
        }
    }

}