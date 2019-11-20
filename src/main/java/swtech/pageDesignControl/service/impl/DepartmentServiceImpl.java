package swtech.pageDesignControl.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.mapper.DepartmentMapper;
import swtech.pageDesignControl.mapper.FlowMapper;
import swtech.pageDesignControl.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-19
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Transactional
    @Override
    public boolean save(Department department) {
        int num = departmentMapper.insert(department);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable did) {
        int num = departmentMapper.deleteById(did);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateById(Department department) {
        int num = departmentMapper.updateById(department);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Department selectById(int did) {
        return departmentMapper.selectById(did);
    }

    @Transactional
    @Override
    public List<Department> selectByPageAndCondition(Department department, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        return departmentMapper.selectByPageAndCondition(department, pageStart, pageSize);
    }

    @Transactional
    @Override
    public int selectCount() {
        return departmentMapper.selectCount();
    }
}
