package my_recipe_board.model.vo;

public class MrContent implements java.io.Serializable {
	public static final long serialVersionUID = 4890L;

	private int mrBoardNo;
	private int mrCount;
	private String mrBoardContent;
	private String mrOriginFile;
	private String mrRenamefile;

	public MrContent() {
	}

	public MrContent(int mrBoardNo, int mrCount, String mrBoardContent, String mrOriginFile, String mrRenamefile) {
		super();
		this.mrBoardNo = mrBoardNo;
		this.mrCount = mrCount;
		this.mrBoardContent = mrBoardContent;
		this.mrOriginFile = mrOriginFile;
		this.mrRenamefile = mrRenamefile;
	}

	public int getMrBoardNo() {
		return mrBoardNo;
	}

	public void setMrBoardNo(int mrBoardNo) {
		this.mrBoardNo = mrBoardNo;
	}

	public int getMrCount() {
		return mrCount;
	}

	public void setMrCount(int mrCount) {
		this.mrCount = mrCount;
	}

	public String getMrBoardContent() {
		return mrBoardContent;
	}

	public void setMrBoardContent(String mrBoardContent) {
		this.mrBoardContent = mrBoardContent;
	}

	public String getMrOriginFile() {
		return mrOriginFile;
	}

	public void setMrOriginFile(String mrOriginFile) {
		this.mrOriginFile = mrOriginFile;
	}

	public String getMrRenamefile() {
		return mrRenamefile;
	}

	public void setMrRenamefile(String mrRenamefile) {
		this.mrRenamefile = mrRenamefile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MrContent [mrBoardNo=" + mrBoardNo + ", mrCount=" + mrCount + ", mrBoardContent=" + mrBoardContent
				+ ", mrOriginFile=" + mrOriginFile + ", mrRenamefile=" + mrRenamefile + "]";
	}

	
	
}
