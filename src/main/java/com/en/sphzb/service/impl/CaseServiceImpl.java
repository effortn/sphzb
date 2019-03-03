package com.en.sphzb.service.impl;

import com.en.sphzb.VO.ResultVO;
import com.en.sphzb.dto.DateUtils;
import com.en.sphzb.entity.Cases;
import com.en.sphzb.repository.CaseRepository;
import com.en.sphzb.service.CaseService;
import com.en.sphzb.service.CaseToQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 案件Service实现类
 * create by en
 * at 2019/2/25 9:35
 **/
@Slf4j
@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private CaseToQuestionService caseToQuestionService;

    @Override
    @Transactional
    public ResultVO uploadCases(MultipartFile file) {
        ResultVO result;
        InputStream is = null;
        try {
            String fileName = file.getOriginalFilename();
            log.info("【案件上传】数据解析，文件：{}", fileName);
            Workbook workbook = null;
            // 1. 读取文件数据到 Workbook
            if (fileName.endsWith(".xls")) {
                is = file.getInputStream();
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(".xlsx")) {
                is = file.getInputStream();
                workbook = new XSSFWorkbook(is);
            } else {
                log.error("【案件上传】上传失败，文件格式错误，文件：{}", fileName);
                result = new ResultVO(1, "文件格式错误");
            }
            // 2. 解析数据，从第一行开始读取，只读前两列，当前两列数据都为空数据时结束
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet != null) {
                List<Cases> toSave = new ArrayList<>();
                for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                    if (null != sheet.getRow(i)) {
                        Row row = sheet.getRow(i);
                        // 2-1 读取案件编号和案情描述列
                        Cell codeCell = row.getCell(0);
                        Cell descriptionCell = row.getCell(1);
                        //  若都为空，停止读取
                        if (codeCell == null && descriptionCell == null) {
                            break;
                        }
                        String caseCode = codeCell.getStringCellValue();
                        String caseDescription = descriptionCell.getStringCellValue();
                        if (!StringUtils.isEmpty(caseCode) && !StringUtils.isEmpty(caseDescription)) {
                            // 两个数据都不为空，构建案件，放入待保存列表，等待数据读取完毕统一保存
                            Cases aCase = new Cases();
                            aCase.setCaseCode(caseCode.trim());
                            aCase.setCaseDescription(caseDescription.trim());
                            toSave.add(aCase);
                        } else if (StringUtils.isEmpty(caseCode) && StringUtils.isEmpty(caseDescription)) {
                            // 两个数据都为空，读取停止
                            break;
                        } else {
                            // 某一个数据为空，继续读取
                            continue;
                        }
                    }
                }
                log.info("【案件上传】数据解析完成，文件：{}，总共{}条数据", fileName, toSave.size());
                // 3. 数据保存
                List<Cases> cases = caseRepository.saveAll(toSave);
                log.info("【案件上传】数据保存完成，文件：{}，总共{}条数据", fileName, cases.size());
                // 4. 上传成功时，异步调用案件转换成题目的服务接口
                caseToQuestionService.parseQuestion();

                result = new ResultVO(0, "上传成功，共保存{}条数据", cases.size());
            } else {
                result = new ResultVO(1, "上传失败，数据为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【案件上传】报错，文件：{}", file, e);
            result = new ResultVO(1, "上传失败，错误原因:" + e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
