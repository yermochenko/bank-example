package domain;

import java.util.Date;

public class Transfer extends Entity {
	private Client source;
	private Client destination;
	private Date date;
	private Long sum;

	public Client getSource() {
		return source;
	}

	public void setSource(Client source) {
		this.source = source;
	}

	public Client getDestination() {
		return destination;
	}

	public void setDestination(Client destination) {
		this.destination = destination;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}
}
