package job;

import job.entity.CompanyInfo;
import job.entity.CompanyPosition;
import job.mapper.CompanyPositionMapper;
import job.vo.CompanyPositionVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyPositionTest {
    @Resource
    private CompanyPositionMapper companyPositionMapper;

    @Test
    public void findByCompanyInfoId(){
        List<CompanyPosition> companyPosition = companyPositionMapper.findByCompanyInfoId(1);
        System.out.println(companyPosition);
    }

    @Test
    public void selectCompanyPositionByCondition() {
        CompanyPositionVO companyPositionVO = new CompanyPositionVO();

        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setArea("广州");
//        companyInfo.setIndustry("互联网");
//        companyInfo.setStage("天使轮");
//        companyInfo.setScale("50-150人");

        CompanyPosition companyPosition = new CompanyPosition();
//        companyPosition.setCompanyInfoId(1);

        companyPositionVO.setCompanyPosition(companyPosition);
        companyPositionVO.setCompanyInfo(companyInfo);

        List<CompanyPosition> companyPositionList = companyPositionMapper.selectCompanyPositionByCondition(companyPositionVO,0,10);
        System.out.println(companyPositionList);
        int num = companyPositionMapper.selectCount(companyPositionVO);
        System.out.println(num);
    }

}
