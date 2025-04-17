package br.com.ff.abstracts;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class AbstractModel implements Serializable {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "creted_at", updatable = false, nullable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false, updatable = false)
	private Date updatedAt;

	public AbstractModel() {}

	public UUID getId() {
		return id;
	}

	public Date getCreatedAt() {
		createdAt = new Date();
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
