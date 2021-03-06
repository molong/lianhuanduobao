package com.lhdb.game.entity;

public class ModulePermission extends ModulePermissionKey {
	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ModulePermission.ModuleID
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    private Integer moduleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ModulePermission.PermissionValue
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    private Long permissionvalue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ModulePermission.ModuleID
     *
     * @return the value of ModulePermission.ModuleID
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public Integer getModuleid() {
        return moduleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ModulePermission.ModuleID
     *
     * @param moduleid the value for ModulePermission.ModuleID
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ModulePermission.PermissionValue
     *
     * @return the value of ModulePermission.PermissionValue
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public Long getPermissionvalue() {
        return permissionvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ModulePermission.PermissionValue
     *
     * @param permissionvalue the value for ModulePermission.PermissionValue
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public void setPermissionvalue(Long permissionvalue) {
        this.permissionvalue = permissionvalue;
    }
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ModulePermission.PermissionTitle
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    private String permissiontitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ModulePermission.Nullity
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    private Byte nullity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ModulePermission.StateFlag
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    private Integer stateflag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ModulePermission.ParentID
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    private Integer parentid;
    
    private String uri;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ModulePermission.PermissionTitle
     *
     * @return the value of ModulePermission.PermissionTitle
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public String getPermissiontitle() {
        return permissiontitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ModulePermission.PermissionTitle
     *
     * @param permissiontitle the value for ModulePermission.PermissionTitle
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public void setPermissiontitle(String permissiontitle) {
        this.permissiontitle = permissiontitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ModulePermission.Nullity
     *
     * @return the value of ModulePermission.Nullity
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public Byte getNullity() {
        return nullity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ModulePermission.Nullity
     *
     * @param nullity the value for ModulePermission.Nullity
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public void setNullity(Byte nullity) {
        this.nullity = nullity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ModulePermission.StateFlag
     *
     * @return the value of ModulePermission.StateFlag
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public Integer getStateflag() {
        return stateflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ModulePermission.StateFlag
     *
     * @param stateflag the value for ModulePermission.StateFlag
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public void setStateflag(Integer stateflag) {
        this.stateflag = stateflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ModulePermission.ParentID
     *
     * @return the value of ModulePermission.ParentID
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ModulePermission.ParentID
     *
     * @param parentid the value for ModulePermission.ParentID
     *
     * @mbggenerated Fri Oct 31 15:08:52 CST 2014
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
    
}