package com.en.sphzb.service;

import com.en.sphzb.entity.Case;
import com.en.sphzb.entity.mapper.CaseMapper;
import com.en.sphzb.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * 案件装换为题目
 * create by en
 * at 2019/2/25 14:16
 **/
@Service
public class CaseToQuestionService {

//    @Autowired
//    private CaseMapper caseMapper;

    @Autowired
    private CaseRepository caseRepository;

    public void parseQuestion() {
//        Set<Long> caseIdSet = caseMapper.findCaseToParse();
//        for (long caseId : caseIdSet) {
//            Case aCase = caseRepository.findById(caseId).get();
//        }
    }

}
