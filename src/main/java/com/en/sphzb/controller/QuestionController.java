package com.en.sphzb.controller;

import com.en.sphzb.VO.AnswerStatVO;
import com.en.sphzb.VO.ResultVO;
import com.en.sphzb.entity.AnswerRecord;
import com.en.sphzb.entity.AnswerStat;
import com.en.sphzb.entity.Question;
import com.en.sphzb.service.AnswerStatService;
import com.en.sphzb.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private AnswerStatService answerStatService;

    @GetMapping(value = "")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("question");
        Question question = questionService.getQuestion();
        mv.addObject("question", question);
        return mv;
    }

    @PostMapping(value = "commit")
    public ModelAndView commitAnswer(Long questionId, String answer, HttpServletRequest httpServletRequest) {
        ModelAndView mv = new ModelAndView("commit");
        String ip = httpServletRequest.getRemoteAddr();
        if (StringUtils.isEmpty(ip))
            ip = "0.0.0.0";
        AnswerRecord answerRecord = questionService.saveAnswer(ip, null, questionId, answer);
        ResultVO resultVO = null;
        if (answerRecord == null) {
            resultVO = new ResultVO(1, "保存失败");
        } else {
            resultVO = new ResultVO(0, "保存成功");
        }
        mv.addObject("result", resultVO);
        return mv;
    }

    @GetMapping(value = "stat")
    public ModelAndView stat(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Map<String, Object> map){
        Page<AnswerStatVO> stat = questionService.getStat(page, size);
        map.put("statPage", stat);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("stat", map);
    }

    @ResponseBody
    @RequestMapping(value = "toStat", method = RequestMethod.GET)
    public ResultVO caseUpload(String token) {
        // 判断token是否正确
        if ("stf-wt".equals(token)) {
            answerStatService.stat();
            return new ResultVO(0, "权重计算完成！");
        } else {
            return new ResultVO(0, "没有权限！");
        }
    }

}