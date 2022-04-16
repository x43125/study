package com.wx.springsourcestudy.guigu.bean;

/**
 * @Author: x43125
 * @Date: 22/04/06
 */
public class Role {
    /*
    <bean id="role1" class="com.wbg.springxmlbean.entity.Role">
       <!-- property元素是定义类的属性，name属性定义的是属性名称 value是值
        相当于：
        Role role=new Role();
        role.setId(1);
        role.setRoleName("高级工程师");
        role.setNote("重要人员");-->
        <property name="id" value="1"/>
        <property name="roleName" value="高级工程师"/>
        <property name="note" value="重要人员"/>
    </bean>
     */

    private int id;
    private String roleName;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
