package mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mp.domain.po.User;
import mp.mapper.UserMapper;
import mp.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 实现类： 继承 ServiceImpl<Mapper, T> 并实现接口
 */
@Service
public class UserServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements IUserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 扣减用户余额
     *
     * @param id
     * @param money
     */
    @Override
    public void deductBalance(Long id, Integer money) {
        // 查询用户
        User user = getById(id);
        // 校验用户状态
        if (user == null || user.getStatus() == 2) {
            throw new RuntimeException("用户状态异常");
        }
        // 校验余额是否充足
        if (user.getBalance() < money) {
            throw new RuntimeException("用户余额不足");
        }
        // 扣减余额 update tb_user set balance = balance - ?
        baseMapper.deductBalance(id, money);
    }
}
