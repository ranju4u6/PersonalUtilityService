package com.personal.utility.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * JPA Entity representing TBL_USER
 * @author renjith
 *
 */

@Entity
@Table(name="TBL_USER")
@NamedQueries({@NamedQuery(name="User_Row_Count", query="select count(*) from User")})
public class User implements Serializable {

	private static final long serialVersionUID = -8533962925214143224L;
	private static final String COUNT_QUERY_KEY = "User_Row_Count";
	
	@Id
	@GenericGenerator(name="sequence_user_id", strategy="com.personal.utility.idgenerator.IdGenerator",
			parameters= {@Parameter(name="PREFIX", value="UID_"),
					@Parameter(name="QUERY_KEY", value=COUNT_QUERY_KEY)})
	@GeneratedValue(generator="sequence_user_id")
	@Column(name="user_id")
	private String id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="active")
	@Type(type="yes_no")
	private boolean active;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	@JoinColumn(name="user_type_id")
	@OneToOne(cascade=CascadeType.ALL)
	private UserType userType;
	
	@Column(name="session_id")
	private String sessionId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
