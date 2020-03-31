package TheChromaturge.patches.wisps;

import TheChromaturge.orbs.AbstractWisp;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

@SpirePatch(clz = AbstractCard.class, method = SpirePatch.CLASS)
public class PostDamageTriggerPatch {
    
    @SpirePatch(clz = AbstractMonster.class, method = "die", paramtypez = {boolean.class})
    public static class MakeStatEquivalentCopy {
        public static void Prefix(AbstractMonster target, boolean b) {
            if(!target.isDying) {
                for (AbstractOrb O : AbstractDungeon.player.orbs) {
                    if (O instanceof AbstractWisp) {
                        ((AbstractWisp) O).onMonsterDeath();
                    }
                }
            }
        }
    }

}