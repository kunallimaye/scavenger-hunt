package com.kunal.demo.scavengerhunt.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import com.kunal.demo.scavengerhunt.entity.User;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4391214625987931014L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version = 0;

	@Column
	private String taskId;

	@ManyToOne
	private User owner;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String hint;

	@Column
	private String status;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		if (id != null) {
			return id.equals(((Task) that).id);
		}
		return super.equals(that);
	}

	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		}
		return super.hashCode();
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(final String taskId) {
		this.taskId = taskId;
	}

	public User getOwner() {
		return this.owner;
	}

	public void setOwner(final User owner) {
		this.owner = owner;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(final Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String Description) {
		this.description = Description;
	}

	public String getHint() {
		return this.hint;
	}

	public void setHint(final String hint) {
		this.hint = hint;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (taskId != null && !taskId.trim().isEmpty())
			result += "taskId: " + taskId;
		if (name != null && !name.trim().isEmpty())
			result += ", name: " + name;
		if (description != null && !description.trim().isEmpty())
			result += ", description: " + description;
		if (hint != null && !hint.trim().isEmpty())
			result += ", hint: " + hint;
		if (status != null && !status.trim().isEmpty())
			result += ", status: " + status;
		return result;
	}
}