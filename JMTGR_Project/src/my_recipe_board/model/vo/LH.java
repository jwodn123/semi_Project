package my_recipe_board.model.vo;

public class LH implements java.io.Serializable{
	private static final long serialVersionUID = 8612L;

	private int mrBoardNo;  
	private String userId;  
	private String likes;  
	private String Hates;  
	
	public LH() {}

	public LH(int mrBoardNo, String userId, String likes, String hates) {
		super();
		this.mrBoardNo = mrBoardNo;
		this.userId = userId;
		this.likes = likes;
		Hates = hates;
	}

	
	
	public int getMrBoardNo() {
		return mrBoardNo;
	}

	public void setMrBoardNo(int mrBoardNo) {
		this.mrBoardNo = mrBoardNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getHates() {
		return Hates;
	}

	public void setHates(String hates) {
		Hates = hates;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "LH [mrBoardNo=" + mrBoardNo + ", userId=" + userId + ", likes=" + likes + ", Hates=" + Hates + "]";
	}
	
	
	
}
