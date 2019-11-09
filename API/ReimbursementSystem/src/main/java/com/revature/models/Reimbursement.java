package com.revature.models;

import java.io.Serializable;
import java.sql.Timestamp;

import com.revature.Status;
import com.revature.Type;
import com.revature.util.SafeParser;

public class Reimbursement implements Serializable {

	private static final long serialVersionUID = 3427905030434729866L;
	private double 		amount;
			String		description,
						author,
						resolver;
			Timestamp	submitted,
						resolved;
			Type		type;
			Status		status;
			
	@SuppressWarnings("unused")
	private Reimbursement() {super();}

	public Reimbursement(double amount, String description, String author, String resolver, Timestamp submitted,
			Timestamp resolved, Type type, Status status) {
		
		super();
		
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.submitted = submitted;
		this.resolved = resolved;
		this.type = type;
		this.status = status;
	
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public void setType(String type) {
		this.type = SafeParser.parseType(type);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setStatus(String status) {
		this.status = SafeParser.parseStatus(status);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Reimbursement [amount=" + amount + ", description=" + description + ", author=" + author + ", resolver="
				+ resolver + ", submitted=" + submitted + ", resolved=" + resolved + ", type=" + type + ", status="
				+ status + "]";
	}
	
	
	
}
