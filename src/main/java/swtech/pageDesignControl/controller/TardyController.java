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
import swtech.pageDesignControl.entity.Tardy;
import swtech.pageDesignControl.service.ITardyService;

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
 * 前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2019-12-06
 */
@RestController
@Slf4j
public class TardyController {

    @Autowired
    private ITardyService iTardyService;

    @ResponseBody
    @GetMapping("/manage/tardy/save")
    public ReturnMsg save(Tardy tardy) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iTardyService.save(tardy);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建迟到成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建迟到失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建迟到异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/tardy/removebyid")
    public ReturnMsg removeById(@RequestParam("tid") int tid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iTardyService.removeById(tid);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除迟到成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除迟到失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除迟到异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/tardy/updatebyid")
    public ReturnMsg updateById(Tardy tardy) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iTardyService.updateById(tardy);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改迟到成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改迟到失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改迟到异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/tardy/getbyid")
    public ReturnMsg getById(@RequestParam("tid") int tid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            Tardy tardy = iTardyService.getById(tid);
            if (tardy != null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询单个迟到成功");
                msg.setMsg(tardy);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询单个迟到失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询单个迟到异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/tardy/selectbypageandcondition")
    public ReturnMsgPage selectByPageAndCondition(Tardy tardy, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            List<Tardy> departmentList = iTardyService.selectByPageAndCondition(tardy, page, pageSize);
            int totalSize = iTardyService.selectCount();
            int totalPage = (int) Math.ceil(1.0 * totalSize / pageSize);
            if (departmentList != null) {
                msg.setStatus("200");
                msg.setStatusMsg("获取迟到条件分页成功");
                msg.setTotalPage(totalPage);
                msg.setMsg(departmentList);
                msg.setPageSize(pageSize);
                msg.setCurrentPage(page);
                msg.setPageStart((page - 1) * pageSize);
                msg.setTotalSize(totalSize);
                msg.setPageEnd(page * pageSize);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取迟到条件分页失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取迟到条件分页异常");
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
    @RequestMapping(value = "/manage/tardy/fileUpload", method = RequestMethod.POST)
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
        Tardy tardy = null;
        List<Tardy> list = new ArrayList<>();
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
                    tardy = new Tardy();
                    Cell tname = hssfRow.getCell(0);
                    Cell tdate = hssfRow.getCell(1);
                    Cell date = hssfRow.getCell(2);
                    // 处理具体的业务数据，把业务数据装到List中
//					tbClass.setcId(Integer.parseInt(getStringValueFromCell(cId)));
                    tardy.setTname(tname.toString());
                    tardy.setTdate(tdate.toString());
                    tardy.setDate(date.toString());
                    list.add(tardy);
                }
            }
        }
        // List中的数据就是在Excel中读取的内容
        //存入数据库
        iTardyService.insertList(list);
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

    @RequestMapping("/manage/tardy/outputexcel")
    public void outPutExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置编码方式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        // 获取参数
        String tname = request.getParameter("tname");
        String date = request.getParameter("date");

        Tardy tardy = new Tardy();
        if(tname == null){
            tname = "";
        }
        if(date == null){
            date = "";
        }
        tardy.setDate(date);
        tardy.setTname(tname);
        // 每次写100行数据，就刷新数据出缓存
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory,
        Sheet sh = wb.createSheet();
        // 这个是业务数据
        List<Tardy> tmps = iTardyService.selectByPageAndCondition(tardy, 1, 10000);
        String[] titles = { "姓名", "日期", "迟到年月" };
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
            Tardy tmp = tmps.get(rowNum);
            // 第一列
            Cell cellDataA = rowData.createCell(0);
            cellDataA.setCellValue(tmp.getTname());
            // 第二列
            Cell cellDataB = rowData.createCell(1);
            cellDataB.setCellValue(tmp.getTdate());
            // 第三列
            Cell cellDataC = rowData.createCell(2);
            cellDataC.setCellValue(tmp.getDate());
        }
        String fileName = "迟到.xlsx";
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        wb.write(response.getOutputStream());
        wb.close();
    }
}

