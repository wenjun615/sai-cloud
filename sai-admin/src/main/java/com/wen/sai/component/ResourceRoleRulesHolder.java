package com.wen.sai.component;

import com.wen.sai.service.RoleResourceRelationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>
 * 资源与角色关系操作组件
 * </p>
 *
 * @author wenjun
 * @since 2021/2/24
 */
@Component
@AllArgsConstructor
public class ResourceRoleRulesHolder {

    private final RoleResourceRelationService roleResourceRelationService;

    /**
     * 初始化资源与角色关系
     */
    @PostConstruct
    public void initResourceRoles() {
        roleResourceRelationService.initResourceRoles();
    }
}
