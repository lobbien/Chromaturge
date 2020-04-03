package TheChromaturge.patches.wisps;

import TheChromaturge.orbs.PurpleWisp;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;

@SpirePatch(clz = AbstractCard.class, method = SpirePatch.CLASS)
public class EnemyAttackPatch {

    @SpirePatch(clz = AbstractMonster.class, method = "calculateDamage")
    public static class Patch {
        @SpireInsertPatch(locator = Locator.class, localvars = {"tmp"})
        public static void Insert(AbstractMonster __instance, int dmg, @ByRef int[] tmp) {
            for (AbstractOrb o: AbstractDungeon.player.orbs) {
                if(o instanceof PurpleWisp)
                {
                    tmp[0] -= o.passiveAmount;
                }
            }
        }
    }
    @SpirePatch(clz = DamageInfo.class, method = "applyPowers")
    public static class Patch2 {
        @SpireInsertPatch(locator = Locator.class, localvars = {"tmp"})
        public static void Insert(DamageInfo __instance, AbstractCreature owner, AbstractCreature target, @ByRef int[] tmp) {
            for (AbstractOrb o: AbstractDungeon.player.orbs) {
                if(o instanceof PurpleWisp)
                {
                    tmp[0] -= o.passiveAmount;
                }
            }
        }
    }
    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(Settings.class, "isEndless");
            int[] found = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return found;
        }
    }
}