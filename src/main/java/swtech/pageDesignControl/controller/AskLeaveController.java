package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.vo.ReturnMsgPage;
import swtech.pageDesignControl.entity.AskLeave;
import swtech.pageDesignControl.service.IAskLeaveService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2019-12-06
 */
@Slf4j
@RestController
public class AskLeaveController {

    @Autowired
    private IAskLeaveService iAskLeaveService;

    @ResponseBody
    @GetMapping("/manage/askleave/save")
    public ReturnMsg save(AskLeave askLeave) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iAskLeaveService.save(askLeave);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建请假成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建请假失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建请假异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/askleave/removebyid")
    public ReturnMsg removeById(@RequestParam("lid") int lid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iAskLeaveService.removeById(lid);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除请假成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除请假失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除请假异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/askleave/updatebyid")
    public ReturnMsg updateById(AskLeave askLeave) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iAskLeaveService.updateById(askLeave);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改请假成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改请假失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改请假异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/askleave/getbyid")
    public ReturnMsg getById(@RequestParam("lid") int lid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            AskLeave askLeave = iAskLeaveService.getById(lid);
            if (askLeave != null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询单个请假成功");
                msg.setMsg(askLeave);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询单个请假失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询单个请假异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/askleave/selectbypageandcondition")
    public ReturnMsgPage selectByPageAndCondition(AskLeave askLeave, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            List<AskLeave> departmentList = iAskLeaveService.selectByPageAndCondition(askLeave, page, pageSize);
            int totalSize = iAskLeaveService.selectCount();
            int totalPage = (int) Math.ceil(1.0 * totalSize / pageSize);
            if (departmentList != null ) {
                msg.setStatus("200");
                msg.setStatusMsg("获取请假条件分页成功");
                msg.setTotalPage(totalPage);
                msg.setMsg(departmentList);
                msg.setPageSize(pageSize);
                msg.setCurrentPage(page);
                msg.setTotalSize(totalSize);
                msg.setPageStart((page - 1) * pageSize);
                msg.setPageEnd(page * pageSize);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取请假条件分页失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取请假条件分页异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    /**
     * 导入excel表格
     *
     * @param file
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/manage/askleave/fileUpload", method = RequestMethod.POST)
    public ReturnMsg fileUpload2(@RequestParam("file") MultipartFile file) throws Exception {
        ReturnMsg msg = new ReturnMsg();
        String path = "C:/Users/Administrator/Desktop/" + new Date().getTime() + file.getOriginalFilename();
        File newFile = new File(path);
        // 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        readExcel(path);
        if (newFile.exists()) {
            newFile.delete();
        }
        msg.setStatus("200");
        msg.setStatusMsg("上传成功");
        return msg;
    }

    public void readExcel(String fileName) throws Exception {
        InputStream is = new FileInputStream(new File(fileName));
        Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")) {
            hssfWorkbook = new XSSFWorkbook(is);// Excel 2007
        } else if (fileName.endsWith("xls")) {
            hssfWorkbook = new HSSFWorkbook(is);// Excel 2003
        }
        AskLeave askLeave = null;
        List<AskLeave> list = new ArrayList<>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    askLeave = new AskLeave();
                    Cell lname = hssfRow.getCell(0);
                    Cell ltype = hssfRow.getCell(1);
                    Cell ldate = hssfRow.getCell(2);
                    Cell ltime = hssfRow.getCell(3);
                    Cell ltotal = hssfRow.getCell(4);
                    Cell date = hssfRow.getCell(5);
                    // 处理具体的业务数据，把业务数据装到List中
                    // tbClass.setcId(Integer.parseInt(getStringValueFromCell(cId)));
                    askLeave.setLname(lname.toString());
                    askLeave.setLtype(ltype.toString());
                    askLeave.setLdate(ldate.toString());
                    askLeave.setLtime(ltime.toString());
                    askLeave.setLtotal(ltotal.toString());
                    askLeave.setDate(date.toString());
                    list.add(askLeave);
                }
            }
        }
        // List中的数据就是在Excel中读取的内容
        //存入数据库
        iAskLeaveService.insertList(list);
    }

    /**
     * 将年月转换，将浮点型转换为整数
     *
     * @param cell
     * @return
     */
    public String getStringValueFromCell(Cell cell) {
        SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        } else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                double d = cell.getNumericCellValue();
                Date date = HSSFDateUtil.getJavaDate(d);
                cellValue = sFormat.format(date);
            } else {
                cellValue = decimalFormat.format((cell.getNumericCellValue()));
            }
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            cellValue = "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            cellValue = "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            cellValue = cell.getCellFormula().toString();
        }
        return cellValue;
    }

    @RequestMapping("/manage/askleave/outputexcel")
    public void outPutExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置编码方式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        // 获取参数
        String lname = request.getParameter("lname");
        String date = request.getParameter("date");

        AskLeave askLeave = new AskLeave();
        if(lname == null){
            lname = "";
        }
        if(date == null){
            date = "";
        }
        askLeave.setDate(date);
        askLeave.setLname(lname);
        // 每次写100行数据，就刷新数据出缓存
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory,
        Sheet sh = wb.createSheet();
        // 这个是业务数据
        List<AskLeave> tmps = iAskLeaveService.selectByPageAndCondition(askLeave, 1, 10000);
        String[] titles = { "姓名", "休假类型", "日期" ,"时间", "总计(天/时)", "请假年月"};
        Row row = sh.createRow(0);
        // 第一行设置标题
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            Cell cell1 = row.createCell(i);
            cell1.setCellValue(title);
        }
        // 导出数据
        for (int rowNum = 0; rowNum < tmps.size(); rowNum++) {
            Row rowData = sh.createRow(rowNum + 1);
            // TbClass 这个是我的业务类，这个是根据业务来进行填写数据
            AskLeave tmp = tmps.get(rowNum);
            // 第一列
            Cell cellDataA = rowData.createCell(0);
            cellDataA.setCellValue(tmp.getLname());
            // 第二列
            Cell cellDataB = rowData.createCell(1);
            cellDataB.setCellValue(tmp.getLtype());
            // 第三列
            Cell cellDataC = rowData.createCell(2);
            cellDataC.setCellValue(tmp.getLdate());
            // 第四列
            Cell cellDataD = rowData.createCell(3);
            cellDataD.setCellValue(tmp.getLtime());
            // 第五列
            Cell cellDataE = rowData.createCell(4);
            cellDataE.setCellValue(tmp.getLtotal());
            // 第六列
            Cell cellDataF = rowData.createCell(5);
            cellDataF.setCellValue(tmp.getDate());
        }
        String fileName = "请假.xlsx";
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        wb.write(response.getOutputStream());
        wb.close();
    }
}


