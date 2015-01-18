package wcr;

public class PointPath {
	//子弹路径的坐标
	private int x;
	private int y;
	
	
	public PointPath(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//定义子弹的移动1
	public int pointRun() {
		if(this.y < 0) {
			return 0;
		}
		this.y = this.y - 10;
		return 1;
	}
	//定义子弹的移动2
	public int bulletRun(){
		if(this.y > 600) {
			return 0;
		}
		this.y = this.y + 5;
		return 1;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
