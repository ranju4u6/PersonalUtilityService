package com.personal.utility.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Parameter;


/**
 * JPA Entity representing TBL_USER_TYPE
 * @author renjith
 *
 */

@Entity
@Table(name="TBL_USER_TYPE")
@NamedQueries({@NamedQuery(name="User_Type_Row_Count", query="select count(*) from UserType")})
public class UserType implements Serializable {

	private static final long serialVersionUID = -7215824073554517331L;
	private static final String COUNT_QUERY_KEY = "User_Type_Row_Count";
	
	@Id
	@GenericGenerator(name="sequence_user_type_id", strategy="com.personal.utility.idgenerator.IdGenerator",
			parameters= {@Parameter(name="PREFIX", value="UTID_"),
					@Parameter(name="QUERY_KEY", value=COUNT_QUERY_KEY)})
	@GeneratedValue(generator="sequence_user_type_id")
	@Column(name="user_type_id")
	private String id;
	
	@Column(name="user_type")
	private String userType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

}
