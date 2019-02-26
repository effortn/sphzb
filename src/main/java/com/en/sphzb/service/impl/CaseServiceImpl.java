package com.en.sphzb.service.impl;

import com.en.sphzb.VO.ResultVO;
import com.en.sphzb.entity.Cases;
import com.en.sphzb.repository.CaseRepository;
import com.en.sphzb.service.CaseService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.en.sphzb.dto.CommonUtils;
import com.en.sphzb.dto.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 案件Service实现类
 * create by en
 * at 2019/2/25 9:35
 **/
@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseRepository caseRepository;

    @Override
    public ResultVO uploadCases(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Workbook workbook;
            if (fileName.endsWith("xls")){
                workbook = new HSSFWorkbook(file.getInputStream());
            }else if(fileName.endsWith("xlsx")){
                workbook = new XSSFWorkbook(file.getInputStream());
            }else{
                ResultVO resultVO = new ResultVO(1,"文件格式错误");
                return resultVO;
            }
            if (null!=workbook.getSheetAt(0)){
                List<Cases> casesLst = new ArrayList<Cases>();
                for (int ruwNumOfSheet =1;ruwNumOfSheet<=workbook.getSheetAt(0).getLastRowNum();ruwNumOfSheet++){
                    if(null!=workbook.getSheetAt(0).getRow(ruwNumOfSheet)){
                        Row row = workbook.getSheetAt(0).getRow(ruwNumOfSheet);
                        Cases acase = new Cases();
                        for (int cellNumOfRow=0;cellNumOfRow<row.getLastCellNum();cellNumOfRow++){
                            Cell cell =row.getCell(cellNumOfRow);
                            String cellValue = "";
                            if (null != cell) {
                                // 以下是判断数据的类型
                                switch (cell.getCellType()) {
                                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                                        cellValue = cell.getNumericCellValue() + "";
                                        break;

                                    case HSSFCell.CELL_TYPE_STRING: // 字符
                                        cellValue = cell.getStringCellValue();
                                        break;

                                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                                        cellValue = cell.getBooleanCellValue() + "";
                                        break;
                                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
                                        cellValue = cell.getCellFormula() + "";
                                        break;
                                    case HSSFCell.CELL_TYPE_BLANK: // 空
                                        cellValue = "";
                                        break;
                                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                                        cellValue = "非法字符";
                                        break;
                                    default:
                                        cellValue = "未知类型";
                                        break;
                                }
                            }

                            if(cellNumOfRow==0){
                                if(cell!=null&&!cell.toString().trim().isEmpty()){
                                    cellValue=cell.getStringCellValue();
                                    if(cellValue!=null){
                                        acase.setCaseCode(cellValue);
                                    }
                                }
                            }
                            if(cellNumOfRow==1){
                                if(cell!=null&&!cell.toString().trim().isEmpty()){
                                    cellValue=cell.getStringCellValue();
                                    if(cellValue!=null){
                                        acase.setCaseDescription(cellValue);
                                    }
                                }
                            }
                            //acase.setCaseId(Long.parseLong(CommonUtils.getUUID()));
                            //String dateStr = DateUtils.getCurDate().
                            acase.setCreateTime(DateUtils.getCurDate());
                        }

                        caseRepository.save(acase);
                    }
                }
                ResultVO resultVO = new ResultVO(0,"保存成功");
                return resultVO;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
