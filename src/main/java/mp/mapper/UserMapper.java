package mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import mp.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 测试根据id列表查询用户
     * Mybatis 在UserMapper.xml书写SQL语句
     *
     * @param ids
     * @return
     */
    List<User> queryUserByIds(@Param("ids") List<Long> ids);

    /**
     * 根据id更新用户余额
     *
     * @param wrapper
     * @param amount
     */
    void updateBalanceByIds(@Param(Constants.WRAPPER) QueryWrapper<User> wrapper, @Param("amount") int amount);

    /**
     * 扣减用户余额
     *
     * @param id
     * @param money
     */
    @Update("update tb_user set balance = balance - #{money} where id = #{id}")
    void deductBalance(
            @Param("id") Long id,
            @Param("money") Integer money);
}

