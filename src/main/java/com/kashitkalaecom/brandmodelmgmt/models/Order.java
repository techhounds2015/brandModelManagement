package com.kashitkalaecom.brandmodelmgmt.models;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Entity
@Table(name = "customerorder")
public class Order {

	@Id 
	@Column(name = "id")
	protected String id = UUID.randomUUID().toString();
	
	@Column(name = "createdby")
	protected String createdBy;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Column(name = "createdon")
	protected Timestamp createdOn = CustomClock.timestamp();

	@Column(name = "modifiedby")
	protected String modifiedBy;

	@Column(name = "modifiedon")
	protected Timestamp modifiedOn;
	
	@Column(name = "orderstatus")
	private String status;

	@Column(name = "subtotal")
	private int subtotal;

	@Column(name = "tax")
	private int tax;

	@Column(name = "total")
	private int total;

	/*
	 * @Column(name = "orderdate") private Timestamp created =
	 * CustomClock.timestamp();
	 */
	@Column(name = "shippingcharge")
	private int shippingCharge;

	@Column(name = "customerid")
	private int customerId;

	@Column(name = "outletid")
	private String outletId;

	/*
	 * @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "order_id", referencedColumnName = "id") private
	 * List<OrderItem> orderItem;
	 */

	/*
	 * @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "id", referencedColumnName = "orderid") private Billing
	 * billing;
	 */

	public Order() {
	}

	public Order(String status, int subtotal, int tax, int total, int shippingCharge, int customerId2,
			List<OrderItem> orderItem, Billing billing) {
		super();
		this.status = status;
		this.subtotal = subtotal;
		this.tax = tax;
		this.total = total;
		this.shippingCharge = shippingCharge;
		this.customerId = customerId2;
		//this.orderItem = orderItem;
		//this.billing = billing;
	}
 
	
	/*
	 * public Timestamp getCreated() { return created; }
	 * 
	 * public void setCreated(Timestamp created) { this.created = created; }
	 */


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	

	public int getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(int shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getOutletId() {
		return outletId;
	}

	public void setOutletId(String outletId) {
		this.outletId = outletId;
	}


	/*
	 * public Billing getBilling() { return billing; }
	 * 
	 * public void setBilling(Billing billing) { this.billing = billing; }
	 */
	/*
	 * @Override public String toString() { return "Order [id=" + id + ", status=" +
	 * status + ", subtotal=" + subtotal + ", tax=" + tax + ", total=" + total +
	 * ", created=" + created + ", shippingCharge=" + shippingCharge +
	 * ", customerId=" + customerId + ", orderItem=" + orderItem + "]"; }
	 */
}
