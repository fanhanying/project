package com.tapestryTest.domain.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

/** 
 * @author hanying.fan
 * @date 2017年7月24日 上午11:41:16 
 */
@MappedSuperclass
public abstract class AbstractObject implements Serializable{
	
	private static final long serialVersionUID = 8078550144051485413L;
	
	@Id
	@GeneratedValue(generator = "UUIDGen")
	@GenericGenerator(name = "UUIDGen", strategy = "uuid")
	@Column(name = "id", unique = true, length = 32, nullable = false)
	private String id = null;
	
	protected AbstractObject() {
		super();
	}
	
	protected AbstractObject(String id) {
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
	public boolean isNew() {
		return StringUtils.isEmpty(getId());
	}
	
	public DateTime toDateTime(final Date date) {
		return date == null ? null : new DateTime(date);
	}
	
	public Date toDate(final DateTime datetime) {
        return null == datetime ? null : datetime.toDate();
    }
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractObject)) {
			return false;
		}
		final AbstractObject ab = (AbstractObject) obj;
		return null == this.getId() ? false : this.getId().equals(ab.getId());
	}
	
	@Override
	public String toString() {
		return this.toStringHelper().toString();
	}
	
	protected ToStringHelper toStringHelper() {
		return Objects.toStringHelper(this.getClass()).add("id", this.getId());
	}
	
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == this.getId() ? 0 : this.getId().hashCode() * 31;
		return hashCode;
	}
	
	protected void copy(final AbstractObject obj) {
		this.id = obj.id;
	}
}
