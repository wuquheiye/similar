package job;

import job.entity.CompanyInfo;
import job.mapper.CompanyInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyInfoTest {
    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Test
    public void findByUserId() {
        CompanyInfo companyInfo = companyInfoMapper.findByUserId(2);
        System.out.println(companyInfo);
    }

    @Test
    public void selectCompanyPositionByCondition() {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setArea("广州");
        companyInfo.setIndustry("互联网");
        companyInfo.setStage("天使轮");
        companyInfo.setScale("50-150人");
        List<CompanyInfo> companyInfoList = companyInfoMapper.selectCompanyInfoByCondition(companyInfo, 0, 5);
        System.out.println(companyInfoList);
        int num = companyInfoMapper.selectCount(companyInfo);
        System.out.println(num);
    }

}
