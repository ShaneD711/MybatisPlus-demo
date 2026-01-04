package mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mp.domain.po.User;

/**
 *  接口： 继承 IService<T> （T 是实体类）
 */
public interface IUserService extends IService<User> {

    /**
     * 扣减用户余额
     * @param id
     * @param money
     */
    void deductBalance(Long id, Integer money);
}
