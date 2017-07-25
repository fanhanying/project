package com.tapestryTest.domain.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.joda.time.DateTime;

import com.google.common.base.Objects.ToStringHelper;

/** 
 * @author hanying.fan
 * @date 2017年7月24日 下午2:13:15 
 */
@MappedSuperclass
public class AuditableObject extends AbstractObject implements AuditorAware {

	private static final long serialVersionUID = 1L;
	
	@Version
	@Column(name = "version")
	private long version = 1;
	
	@Column(length = 50, name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(length = 50, name = "last_modified_by")
	private String lastModifiedBy;
	
	@Column(name = "last_modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	protected AuditableObject() {
		super();
	}
	
	protected AuditableObject(String id) {
		super(id);
	}
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	public DateTime getCreatedDate() {
		return toDateTime(createdDate);
	}

	public void setCreatedDate(final DateTime createdDate) {
		this.createdDate = toDate(createdDate);
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(final String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public DateTime getLastModifiedDate() {
		return toDateTime(lastModifiedDate);
	}

	public void setLastModifiedDate(final DateTime lastModifiedDate) {
		this.lastModifiedDate = toDate(lastModifiedDate);
	}

	public void audit(String auditor) {
		if (null == auditor) {
			auditor = AuditorAware.DEFAULT_AUDITOR;
		}
		if (this.isNew()) {
			this.setCreatedBy(auditor);
			this.setCreatedDate(DateTime.now());
			this.setLastModifiedBy(auditor);
			this.setLastModifiedDate(DateTime.now());
		} else {
			this.setLastModifiedBy(auditor);
			this.setLastModifiedDate(DateTime.now());
		}
	}
	
	@Override
    protected ToStringHelper toStringHelper() {
        return super.toStringHelper()
                .add("version", this.version)
                .add("createdBy", this.createdBy)
                .add("createdDate", this.createdDate)
                .add("lastModifedBy", this.lastModifiedBy)
                .add("lastModifiedDate", this.lastModifiedDate);

    }
	
	protected void copy(final AbstractObject source) {
        super.copy(source);
        if (source instanceof AuditableObject) {
            AuditableObject ae = (AuditableObject) source;
            this.version = ae.version;
            this.createdBy = ae.createdBy;
            this.createdDate = ae.createdDate;
            this.lastModifiedBy = ae.lastModifiedBy;
            this.lastModifiedDate = ae.lastModifiedDate;
        }

    }
}
