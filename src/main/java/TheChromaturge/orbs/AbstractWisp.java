package TheChromaturge.orbs;

import TheChromaturge.actions.RemoveSpecificWispAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public abstract class AbstractWisp extends AbstractOrb {
    public AbstractWisp(int duration) {
        this.baseEvokeAmount = duration;
    }

    public void onCall() {
    }

    public void onCallOther() {
    }

    public void onRemoveOther() {
    }

    public void onEndOfTurn() {
        super.onEndOfTurn();
        --this.baseEvokeAmount;
        this.evokeAmount = this.baseEvokeAmount;
        if (this.baseEvokeAmount <= 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificWispAction(this));
        }

    }

    public void update() {
        super.update();
        this.showEvokeValue = true;
    }
}
