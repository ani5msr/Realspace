package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the yspost database table.
 * 
 */
@Entity
@NamedQuery(name="Yspost.findAll", query="SELECT y FROM Yspost y")
public class Yspost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int postid;

	@Temporal(TemporalType.DATE)
	private Date postdate;

	private String posttext;

	//bi-directional many-to-one association to Ysuser
	@ManyToOne
	@JoinColumn(name="YSUSERID")
	private Ysuser ysuser;

	public Yspost() {
	}

	public int getPostid() {
		return this.postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public Date getPostdate() {
		return this.postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public String getPosttext() {
		return this.posttext;
	}

	public void setPosttext(String posttext) {
		this.posttext = posttext;
	}

	public Ysuser getYsuser() {
		return this.ysuser;
	}

	public void setYsuser(Ysuser ysuser) {
		this.ysuser = ysuser;
	}

}