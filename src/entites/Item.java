package entites;

public class Item extends AbstractItem {
	
	boolean picked;
	
	public Item(TempItem type, int x, int y, boolean picked) {
		super(type, x, y);
		this.picked = picked;
	}

	public void tick() {
		
	}
	
	public boolean isPicked() {
		return picked;
	}
	
	public void setPicked(boolean picked) {
		this.picked = picked;
	}

}
