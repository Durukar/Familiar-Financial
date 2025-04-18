package br.com.ff.abstracts;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class AbstractModel implements Serializable {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDate createdAt;

	@Column(name = "updated_at", updatable = false)
	private Date updatedAt;

	public AbstractModel() {}

	public UUID getId() {
		return id;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
