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
 * JPA Entity representing TB_TODO
 * @author renjith
 *
 */

@Entity
@Table(name = "TBL_TODO")
@NamedQueries({ @NamedQuery(name = "Todo_Row_Count", query = "select count(*) from ToDo") })
public class ToDo implements Serializable {

	private static final long serialVersionUID = 2480023613057517526L;
	private static final String COUNT_QUERY_KEY = "Todo_Row_Count";

	@Id
	@GenericGenerator(name = "sequence_todo_id", strategy = "com.personal.utility.idgenerator.IdGenerator", parameters = {
			@Parameter(name = "PREFIX", value = "TID_"), @Parameter(name = "QUERY_KEY", value = COUNT_QUERY_KEY) })
	@GeneratedValue(generator = "sequence_todo_id")
	@Column(name = "task_id")
	private String id;

	@Column(name = "task")
	private String task;

	@Column(name = "target_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date targetDate;

	@Column(name = "active")
	@Type(type = "yes_no")
	private boolean active;

	@JoinColumn(name = "user_id")
	@OneToOne(cascade = CascadeType.ALL)
	private User userId;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
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



}
