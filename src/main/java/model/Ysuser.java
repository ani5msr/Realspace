package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the ysuser database table.
 *
 */
@Entity
@NamedQuery(name="Ysuser.findAll", query="SELECT y FROM Ysuser y")
public class Ysuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int ysuserid;

	@Temporal(TemporalType.DATE)
	private Date joindate;

	private String motto;

	private String useremail;

	private String username;

	private String userpassword;

	//bi-directional many-to-one association to Yspost
	@OneToMany(mappedBy="ysuser")
	private List<Yspost> ysposts;

	public Ysuser() {
	}

	public int getYsuserid() {
		return this.ysuserid;
	}

	public void setYsuserid(int ysuserid) {
		this.ysuserid = ysuserid;
	}

	public Date getJoindate() {
		return this.joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public String getMotto() {
		return this.motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getUseremail() {
		return this.useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return this.userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public List<Yspost> getYsposts() {
		return this.ysposts;
	}

	public void setYsposts(List<Yspost> ysposts) {
		this.ysposts = ysposts;
	}

	public Yspost addYspost(Yspost yspost) {
		getYsposts().add(yspost);
		yspost.setYsuser(this);

		return yspost;
	}

	public Yspost removeYspost(Yspost yspost) {
		getYsposts().remove(yspost);
		yspost.setYsuser(null);

		return yspost;
	}

}