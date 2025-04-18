package br.com.ff.models;

import br.com.ff.abstracts.AbstractModel;
import br.com.ff.enums.ExpenseStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
public class Expense extends AbstractModel {

	@ManyToOne
	@JoinColumn(name = "requested_by_id")
	private UserModel requestedBy;

	@ManyToOne
	@JoinColumn(name = "aproved_by_id")
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


}
