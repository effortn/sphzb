package com.en.sphzb.controller;

import com.en.sphzb.VO.ResultVO;
import com.en.sphzb.entity.AnswerRecord;
import com.en.sphzb.entity.AnswerStat;
import com.en.sphzb.entity.Question;
import com.en.sphzb.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    public ModelAndView stat(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Map<String, Object> map){
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = new Sort(direction, "totalAnswer");
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        Page<AnswerStat> stat = questionService.getStat(pageRequest);
        map.put("statPage", stat);
        return new ModelAndView("stat", map);
    }

}