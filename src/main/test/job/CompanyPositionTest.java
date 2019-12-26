package job;

import job.entity.CompanyPosition;
import job.mapper.CompanyPositionMapper;
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
        List<CompanyPosition> companyPositionList = companyPositionMapper.selectCompanyPositionByCondition(null,0,1);
        System.out.println(companyPositionList);
    }

}
