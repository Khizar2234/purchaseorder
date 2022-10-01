package com.ros.inventory.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class PurchaseOrder implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="purchase_id", length = 8)
	private UUID purchasedId;

	/*@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "purchase_number")
	private long purchaseNumber;*/
	
	@Column(name ="purchase_order_date")
	private LocalDate purchaseOrderDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="purchase_order_status")
	private OrderStatus purchaseOrderStatus;
	
	@Column(name = "purchase_value")
	private double totalAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="purchase_type")
	private TransferType transferType;
	
	@Column(name ="purchaseRejection_Date")
	private LocalDate purchaseRejectedDate;

	@OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
	@JoinColumn(name ="supplier_id")
	private Supplier  supplier;
	
	
	@OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
	@JoinColumn(name ="attachement_id")
	 private Attachments  attachements;

}
