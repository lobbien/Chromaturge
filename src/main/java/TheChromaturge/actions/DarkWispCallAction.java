package TheChromaturge.actions;

import TheChromaturge.orbs.AbstractWisp;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class DarkWispCallAction extends AbstractGameAction {
    private AbstractWisp wisp;

    public DarkWispCallAction(AbstractWisp wisp) {
        this.wisp = wisp;
    }

    public void update() {
        int i = 0;
        for (AbstractOrb O : AbstractDungeon.player.orbs) {
            if(O instanceof AbstractWisp && O != wisp)
            {
                i++;
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificWispAction((AbstractWisp)O));
            }
        }
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, i)));
        this.isDone = true;
    }
}

