

/**
 * @author José
 * @version 1.0
 * @created 14-mai-2014 18:13:44
 */
public abstract class Creature extends GameObject {

	private int attackDamage;
	private int hp;
	private int resistance;

	public Creature(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
}//end Creature