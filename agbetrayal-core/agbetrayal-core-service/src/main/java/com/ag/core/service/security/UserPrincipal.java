package com.ag.core.service.security;

import com.ag.core.commons.util.ByteConstants;
import com.ag.core.commons.util.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 当前登陆的用户，所有获取当前登陆用户信息必须继承此类
 *
 * @author zhengaiguo
 * @date 2017年9月28日上午9:45:55
 */
public class UserPrincipal implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 当前用户id
     */

    private Long userId;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 用户类型
     */
    private Byte userType;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户性别
     */
    private Byte sex;

    /**
     * 用户头像
     */
    private String iconPath;

    /**
     * 获取 app 信息，对于 单点登陆，或 oauth2 登陆时有效
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClientAppInfo appInfo;

    /**
     * 用户所在机构id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long orgId;

    /**
     * 用户所在机构名称
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String orgName;

    /**
     * 用户所在部门Id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long deptId;

    /**
     * 用户所在部门名称
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String deptName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> thirdOpenId;

    /**
     * 用户角色
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<String> roles;

    /**
     * 用户权限
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<String> permissions;

    public UserPrincipal(Long userId, String account, Byte userType) {
        this.userId = userId;
        this.account = account;
        this.userType = userType;
    }

    public UserPrincipal(Long userId, String account, String realName,
                         Byte userType, String phone, String email,
                         Byte sex, String iconPath, Set<String> roles, Set<String> permissions) {
        this.userId = userId;
        this.account = account;
        this.realName = realName;
        this.userType = userType;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.iconPath = iconPath;
        this.roles = roles;
        this.permissions = permissions;

    }

    /**
     * 判断用户是否有权限
     *
     * @param permissionName 权限名
     */
    public final boolean hasPermission(String permissionName) {
        return isAdministrator() || CollectionUtils.contains(permissions, permissionName);
    }

    /**
     * 判断用户是否有角色
     *
     * @param roleName 角色名
     */
    public final boolean hasRole(String roleName) {
        return isAdministrator() || CollectionUtils.contains(permissions, roleName);
    }

    /**
     * 是否为管理员
     *
     * @return true if is an admin.
     */
    @JsonIgnore
    public final boolean isAdministrator() {
        return userType == ByteConstants.ONE;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public ClientAppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(ClientAppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Map<String, String> getThirdOpenId() {
        return thirdOpenId;
    }

    public void setThirdOpenId(Map<String, String> thirdOpenId) {
        this.thirdOpenId = thirdOpenId;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public UserPrincipal() {
    }
}
