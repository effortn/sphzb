package com.en.sphzb.service;

import com.en.sphzb.VO.ResultVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 案件相关的Service
 * create by en
 * at 2019/2/22 15:47
 **/
public interface CaseService {

    ResultVO uploadCases(MultipartFile file);



}
