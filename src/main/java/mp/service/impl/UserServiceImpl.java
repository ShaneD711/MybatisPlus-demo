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

}
