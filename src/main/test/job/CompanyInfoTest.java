package job;

import job.entity.CompanyInfo;
import job.mapper.CompanyInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyInfoTest {
    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Test
    public void findByUserId() {
        CompanyInfo companyInfo = companyInfoMapper.findByUserId(1);
        System.out.println(companyInfo);
    }

}
