package job;

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
    public void selectCompanyPositionByCondition() {
        CompanyPositionVO companyPositionVO = new CompanyPositionVO();
        CompanyPosition companyPosition = new CompanyPosition();
        companyPosition.setCompanyInfoId(1);
        companyPositionVO.setCompanyPosition(companyPosition);
        List<CompanyPosition> companyPositionList = companyPositionMapper.selectCompanyPositionByCondition(companyPositionVO,0,1);
        System.out.println(companyPositionList);
    }

    @Test
    public void selectCount() {
        CompanyPositionVO companyPositionVO = new CompanyPositionVO();
        CompanyPosition companyPosition = new CompanyPosition();
        companyPosition.setCompanyInfoId(1);
        companyPositionVO.setCompanyPosition(companyPosition);
        int num;
        num = companyPositionMapper.selectCount(companyPositionVO);
        System.out.println(num);
    }

}
