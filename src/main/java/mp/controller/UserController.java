package mp.controller;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import mp.domain.dto.UserFormDTO;
import mp.domain.po.User;
import mp.domain.vo.UserVO;
import mp.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理接口")
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    /**
     * 新增用户
     *
     * @param userDTO
     */
    @ApiOperation("新增用户接口")
    @PostMapping
    public void saveUser(
            @RequestBody
            UserFormDTO userDTO) {
        // 把DTO拷贝到PO
        User user = BeanUtil.copyProperties(userDTO, User.class);
        // 新增
        userService.save(user);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @ApiOperation("删除用户接口")
    @DeleteMapping("{id}")
    public void deleteUserById(
            @ApiParam("用户id")
            @PathVariable("id")
            Long id) {
        userService.removeById(id);
    }

    /**
     * 根据id查询用户
     *
     * @param id
     */
    @ApiOperation("根据id查询用户接口")
    @GetMapping("{id}")
    public UserVO queryUserById(
            @ApiParam("用户id")
            @PathVariable("id")
            Long id) {
        // 查询用户PO
        User userPO = userService.getById(id);
        // 把PO拷贝到VO
        return BeanUtil.copyProperties(userPO, UserVO.class);
    }

    /**
     * 根据id批量查询用户
     *
     * @param ids
     */
    @ApiOperation("根据id批量查询用户接口")
    @GetMapping
    public List<UserVO> queryUserByIds(
            @ApiParam("用户id集合")
            @RequestParam("ids")
            List<Long> ids) {
        // 查询用户PO
        List<User> users = userService.listByIds(ids);
        // 把PO拷贝到VO
        return BeanUtil.copyToList(users, UserVO.class);
    }

    /**
     * 扣减用户余额
     * <p>
     * 对于简单的增删改查：Controller 直接调用 Service 现成方法
     * 对于复杂的业务：在 Service 实现类里里面写逻辑
     *
     * @param id
     */
    @ApiOperation("扣减用户余额接口")
    @PutMapping("{id}/deduction/{money}")
    public void deductMoneyById(
            @ApiParam("用户id") @PathVariable("id") Long id,
            @ApiParam("扣减的金额") @PathVariable("money") Integer money) {
        userService.deductBalance(id, money);
    }
}
