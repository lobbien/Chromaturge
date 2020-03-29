package TheChromaturge.actions;


import TheChromaturge.orbs.AbstractWisp;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import java.util.Collections;
import java.util.Iterator;

public class RemoveSpecificWispAction extends AbstractGameAction {
    private AbstractWisp wisp;

    public RemoveSpecificWispAction(AbstractWisp wisp) {
        this.wisp = wisp;
    }

    public void update() {
        if (AbstractDungeon.player.orbs.contains(this.wisp)) {
            int i = AbstractDungeon.player.orbs.indexOf(this.wisp);
            AbstractOrb orbSlot = new EmptyOrbSlot(((AbstractOrb)AbstractDungeon.player.orbs.get(AbstractDungeon.player.orbs.indexOf(this.wisp))).cX, ((AbstractOrb)AbstractDungeon.player.orbs.get(AbstractDungeon.player.orbs.indexOf(this.wisp))).cY);

            for(int j = i + 1; j < AbstractDungeon.player.orbs.size(); ++j) {
                Collections.swap(AbstractDungeon.player.orbs, j, j - 1);
            }

            AbstractDungeon.player.decreaseMaxOrbSlots(1);
            for(i = 0; i < AbstractDungeon.player.orbs.size(); ++i) {
                ((AbstractOrb)AbstractDungeon.player.orbs.get(i)).setSlot(i, AbstractDungeon.player.maxOrbs);
            }

            Iterator var5 = AbstractDungeon.player.orbs.iterator();

            while(var5.hasNext()) {
                AbstractOrb o = (AbstractOrb)var5.next();
                if (o instanceof AbstractWisp) {
                    ((AbstractWisp)o).onRemoveOther();
                }
            }
        }

        this.isDone = true;
    }
}
