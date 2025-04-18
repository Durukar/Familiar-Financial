package br.com.ff.models;

import br.com.ff.abstracts.AbstractModel;
import jakarta.persistence.*;

@Entity
@Table(name = "exempenses")
public class Exmpense extends AbstractModel {

	@Column(name = "requested_by")
	@ManyToOne
	@JoinColumn(name = "requested_by_id")
	private UserModel requestedBy;

}
