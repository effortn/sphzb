package com.en.sphzb.service;

import com.en.sphzb.VO.ResultVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 案件相关的Service
 * create by en
 * at 2019/2/22 15:47
 **/
public interface CaseService {

    /**
     * 案件上传，保存到数据库
     * @param file      上传的数据文件
     * @return
     */
    ResultVO uploadCases(MultipartFile file);



}
