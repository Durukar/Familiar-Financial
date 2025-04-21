package br.com.ff.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "expenses")
public class ExpenseModel {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "requested_by_id")
	@JsonIgnoreProperties({"password", "requestedExpenses", "approvedExpenses"})
	private UserModel requestedBy;

	@ManyToOne
	@JoinColumn(name = "approved_by_id")
	@JsonIgnoreProperties({"password", "requestedExpenses", "approvedExpenses"})
	private UserModel approvedBy;

	@Column(name = "requested_at", updatable = false, nullable = false)
	private LocalDateTime requestedAt;

	@Column(name = "approved_at", updatable = false, nullable = true)
	private LocalDateTime approvedAt;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	private ExpenseStatus status;


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UserModel getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(UserModel requestedBy) {
		this.requestedBy = requestedBy;
	}

	public UserModel getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(UserModel approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LocalDateTime getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(LocalDateTime requestedAt) {
		this.requestedAt = requestedAt;
	}

	public LocalDateTime getApprovedAt() {
		return approvedAt;
	}

	public void setApprovedAt(LocalDateTime approvedAt) {
		this.approvedAt = approvedAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ExpenseStatus getStatus() {
		return status;
	}

	public void setStatus(ExpenseStatus status) {
		this.status = status;
	}
}
