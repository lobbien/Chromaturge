package TheChromaturge.actions;
import TheChromaturge.orbs.AbstractWisp;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import java.util.Iterator;

public class CallWispAction extends AbstractGameAction {
    private AbstractWisp wisp;

    public CallWispAction(AbstractWisp wisp) {
        this.wisp = wisp;
    }

    public void update() {
        if (AbstractDungeon.player.orbs.size() < 15) {
            AbstractDungeon.player.increaseMaxOrbSlots(1, true);
            AbstractDungeon.player.channelOrb(this.wisp);
            this.wisp.onCall();
            Iterator var1 = AbstractDungeon.player.orbs.iterator();

            while(var1.hasNext()) {
                AbstractOrb otherorb = (AbstractOrb)var1.next();
                if (otherorb instanceof AbstractWisp) {
                    ((AbstractWisp)otherorb).onCallOther();
                }
            }

            this.isDone = true;
        } else {
            this.isDone = true;
        }
    }
}
