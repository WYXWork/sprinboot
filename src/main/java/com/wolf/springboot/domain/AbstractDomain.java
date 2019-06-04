package com.wolf.springboot.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.wolf.springboot.utils.UuidUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = { AbstractDomain.AbstractEntityListener.class})
//消除自引用JSON解析死循环，同时保留外键值
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public abstract class AbstractDomain implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uuid", unique = true, nullable = false, updatable = false, length = 36)
	private String uuid;

	@Column(name = "is_deleted", nullable = false)
	private Boolean deleted = false;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = true, updatable = false)
	private Date createTime;

	@Column(name = "creator_id")
	private Long creatorId;

	@Column(name = "creator_name", length = 32)
	private String creatorName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	private Date modifyTime;

	@Column(name = "mender_id")
	private Long menderId;

	@Column(name = "mender_name", length = 32)
	private String menderName;

	@Override
	public boolean equals(Object o) {
		return (o == this || (o instanceof AbstractDomain && getId().equals(((AbstractDomain) o).getId())));
	}

	@Override
	public int hashCode() {
		return getId() == null ? 0 : getId().hashCode();
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public static class AbstractEntityListener {

		@PrePersist
		public void onPrePersist(AbstractDomain abstractEntity) {
			if (StringUtils.isBlank(abstractEntity.getUuid()))
				abstractEntity.setUuid(UuidUtil.getUuid());
			abstractEntity.setCreateTime(new Date());
		}

		@PreUpdate
		public void onPreUpdate(AbstractDomain abstractEntity) {
			abstractEntity.setModifyTime(new Date());
		}
	}

}
