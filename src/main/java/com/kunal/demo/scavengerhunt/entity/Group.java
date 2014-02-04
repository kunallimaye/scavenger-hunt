package com.kunal.demo.scavengerhunt.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4425305235297488414L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Version
	@Column(name = "version")
	private int version = 0;

	@Column
	private String name;

	@Column
	private String displayName;

	@Column
	private String description;

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
			return id.equals(((Group) that).id);
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

	public String getGroupName() {
		return this.name;
	}

	public void setGroupName(final String groupName) {
		this.name = groupName;
	}

	public String getGroupDisplayName() {
		return this.displayName;
	}

	public void setGroupDisplayName(final String groupDisplayName) {
		this.displayName = groupDisplayName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (name != null && !name.trim().isEmpty())
			result += "groupName: " + name;
		if (displayName != null && !displayName.trim().isEmpty())
			result += ", groupDisplayName: " + displayName;
		if (description != null && !description.trim().isEmpty())
			result += ", description: " + description;
		return result;
	}
}