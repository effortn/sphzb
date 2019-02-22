package com.en.sphzb.controller;

import com.en.sphzb.VO.ResultVO;
import com.en.sphzb.entity.AnswerRecord;
import com.en.sphzb.entity.Question;
import com.en.sphzb.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 题目控制器
 * create by en
 * at 2019/2/21 14:44
 **/
@Controller
@RequestMapping(value = "/question/")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("question");
        Question question = questionService.getQuestion();
        mv.addObject("question", question);
        return mv;
    }

    @PostMapping(value = "commit")
    public ResultVO commitAnswer(Long questionId, String answer, HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getRemoteAddr();
        if (StringUtils.isEmpty(ip))
            ip = "0.0.0.0";
        AnswerRecord answerRecord = questionService.saveAnswer(ip, null, questionId, answer);
        if (answerRecord == null) {
            return new ResultVO(1, "保存失败");
        }
        return new ResultVO(0, "保存成功");
    }

    @GetMapping(value = "stat")
    public String stat() {
        return "stat";
    }

}
